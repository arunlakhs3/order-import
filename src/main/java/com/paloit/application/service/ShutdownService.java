package com.paloit.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShutdownService implements DisposableBean
{

    private static final Logger log = LoggerFactory.getLogger(ShutdownService.class);

    @Autowired
    ThreadPool threadPool;

    @Override
    public void destroy() throws Exception
    {
        log.info("Shutdown initiated, Ibx Asset builder service is cleaning up !!!");

        threadPool.shutdownTheExecutorService();
    }

}
