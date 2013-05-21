package com.wp3x;

import org.apache.log4j.Logger;

import com.wp3x.jobs.LinkShortener;
import com.wp3x.jobs.TextImport;
import com.wp3x.jobs.TwitterPublisher;

public class SchedulerJobs extends Thread{
	
	static Logger logger = Logger.getLogger(SchedulerJobs.class);

	public void run() {
		while(true){			
			logger.info("Executing at " + new java.util.Date());
			new LinkShortener().execute();
			new TextImport().execute();
			new TwitterPublisher().execute();			
			waitNextExecution();
		}
	}

	private void waitNextExecution() {
		try {
			Long next = (120L)*1000;
			Thread.sleep(next);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		
	}
}
