/*
 * To change this license header, choose License Headers in Project Configs.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paloit.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@PropertySource("classpath:config/otherprops.properties")
@ConfigurationProperties
@Validated
public class Configs
{

    @Autowired
    private WebApplicationContext context;

    private String noOfThreads;

    public String getNoOfThreads()
    {
        return noOfThreads;
    }

    public void setNoOfThreads(String noOfThreads)
    {
        this.noOfThreads = noOfThreads;
    }
}
