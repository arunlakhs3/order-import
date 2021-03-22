package com.paloit.application.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.paloit.application.attributes.Order;
import com.paloit.application.mongodb.OrderRepository;
import com.paloit.application.service.ThreadPool;
import com.paloit.application.tasks.AddOrderTask;
import com.paloit.requests.FirstResponse;
import com.paloit.requests.GetOrders;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class OrderController
{

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private OrderRepository repository;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);


    @Autowired
    ThreadPool pool;

    @RequestMapping(method = RequestMethod.POST, value = "/ImportFile")
    @ApiImplicitParams(
    {
                @ApiImplicitParam(dataType = "__file", name = "files", required = true, paramType = "form")
    })

    private FirstResponse importOrder(@RequestPart(required = true) MultipartFile files)
    {

        log.info("Start Time : " + Instant.now());

        repository.deleteAll();

        FirstResponse response = new FirstResponse();

        try
        {
            Reader reader = new InputStreamReader(files.getInputStream());
            processData(reader);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        Pageable paging = PageRequest.of(0, 200);
        Page<Order> pagedOrders = repository.findAll(paging);
        response.setTotalCount(repository.count());
        response.setOrders(pagedOrders.getContent());

        log.info("End Time : " + Instant.now());
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getOrders")
    private List<Order> getOrders(@RequestBody @Valid GetOrders request)
    {
        Pageable paging = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<Order> pagedOrders = repository.findAll(paging);

        return pagedOrders.getContent();
    }


    private void processData(Reader reader)
    {
        HeaderColumnNameMappingStrategy ms = new HeaderColumnNameMappingStrategy();
        ms.setType(Order.class);

        CsvToBean cb = new CsvToBeanBuilder(reader)
                .withType(Order.class)
                .withMappingStrategy(ms)
                .build();

        List<Order> orders = cb.parse();

        List<Order> persistedOrders = new ArrayList<>();

        ExecutorServiceAdapter adapter = new ExecutorServiceAdapter(pool.getThreadPoolExecutor());

        orders.forEach((o) ->
        {
            try
            {

                Future<Boolean> task = adapter.submit(context.getBean(AddOrderTask.class, o));
                if (task.get())
                {
                    persistedOrders.add(o);
                }
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
            catch (ExecutionException ex)
            {
                ex.printStackTrace();
            }
        });
    }

}
