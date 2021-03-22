package com.paloit.application.service;

import com.paloit.application.configuration.Configs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ThreadPool
{
	private static final Logger		log	= LoggerFactory.getLogger(ThreadPool.class);
	private ThreadPoolTaskExecutor	executor;

    @Autowired
    public ThreadPool(Configs properties)
	{
		executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
            executor.setMaxPoolSize(Integer.parseInt(properties.getNoOfThreads()));
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.initialize();
	}
	
	public ThreadPoolTaskExecutor getThreadPoolExecutor()
	{
		return executor;
	}
	
	public void shutdownTheExecutorService()
	{
		log.error("Shutting down the Executor Service....!!!");
		executor.shutdown();
	}
}
