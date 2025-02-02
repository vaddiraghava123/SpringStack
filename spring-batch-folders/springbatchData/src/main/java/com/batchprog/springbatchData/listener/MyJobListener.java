package com.batchprog.springbatchData.listener;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobListener implements JobExecutionListener{

	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Started Date and Time : ->" + new Date());
		System.out.println("Status at Starting: ->" + jobExecution.getStatus());
	}

	public void afterJob(JobExecution jobExecution) {
		System.out.println("End Date and Time : ->" + new Date());
		System.out.println("Status at Ending: ->" + jobExecution.getStatus());
	}

	
}
