package com.openwp3x;

import org.apache.log4j.Logger;

import com.openwp3x.jobs.LinkShortener;
import com.openwp3x.jobs.TextImport;

public class SchedulerJobs extends Thread{
	
	static Logger logger = Logger.getLogger(SchedulerJobs.class);

	public void run() {
		logger.info("Executing at " + new java.util.Date());
		while(true){			
			new LinkShortener().execute();
			new TextImport().execute();
			//new TwitterPublisher().execute();			
			waitNextExecution();
		}
	}

	private void waitNextExecution() {
		try {
			Long next = (450L)*1000;
			Thread.sleep(next);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		
	}
}
