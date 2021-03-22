package com.paloit.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration(exclude =
{
    DataSourceAutoConfiguration.class
})
@PropertySource("classpath:config/orderdata.properties")
public class OrderDataMain extends SpringBootServletInitializer
{
	public static final String ERRORTEMPLATE = "errorMailTemplate.ftl";
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
            return application.sources(OrderDataMain.class);
	}
	
	public static void main(String[] args) throws Exception
	{
            SpringApplication.run(OrderDataMain.class, args);
	}
}
