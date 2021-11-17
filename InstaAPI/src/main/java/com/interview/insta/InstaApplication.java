package com.interview.insta;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.interview.insta.util.CacheCleaner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InstaApplication {

	public static void main(String[] args) {
		CacheCleaner cacheCleaner = new CacheCleaner();
		//Thread cacheCleanerThread = new Thread(cacheCleaner);
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(cacheCleaner,0,20,TimeUnit.SECONDS);
		SpringApplication.run(InstaApplication.class, args);
	}
}
