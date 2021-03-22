/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paloit.application.tasks;

import com.paloit.application.attributes.Order;
import com.paloit.application.mongodb.OrderRepository;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author alakshmanan
 */
@Component
@Scope(value = "prototype")
public class AddOrderTask implements Callable<Boolean>
{

    @Autowired
    private OrderRepository repository;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    Order order;

    public AddOrderTask(Order order)
    {
        this.order = order;
    }

    @Override
    public Boolean call() throws Exception
    {

        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Order>> violations = validator.validate(order);

        boolean isValid = (violations.size() <= 0);

        boolean isSaved = false;

        if (isValid)
        {
            setNRIC();

            if (repository.save(order) != null)
            {
                isSaved = true;
            }
        }
        return isSaved;
    }

    private void setNRIC()
    {
        order.setNric(getFirstAlphabet() + getNumbers() + getAnyAlphabet());
    }

    private String getFirstAlphabet()
    {

        char[] allowedChars =
        {
            'S', 'T', 'G', 'F'
        };
        String ranStr = RandomStringUtils.random(1, allowedChars);
        return ranStr.toUpperCase();
    }

    private String getNumbers()
    {
        int length = 7;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }

    private String getAnyAlphabet()
    {

        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(1, useLetters, useNumbers);

        return generatedString.toUpperCase();
    }
}
