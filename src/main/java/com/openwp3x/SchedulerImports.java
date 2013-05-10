package com.openwp3x;

import java.util.Collection;
import java.util.Random;

import org.apache.log4j.Logger;

public class SchedulerImports extends Thread{
	
	static Logger logger = Logger.getLogger(SchedulerImports.class);

	public void run() {
		while(true){			
			logger.info("Executing at " + new java.util.Date());
			Collection<EntryPattern> entryPatterns = EntryPatternFactory.getEntryPatternList();
			for(final EntryPattern entryPattern: entryPatterns){		
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							new ImportNews().importNews(new ReaderImpl(entryPattern).getEntries());
						} catch (Exception e) {
							logger.error("Error executing scheduler with source " + entryPattern.getSource(), e);
						} 
						
					}
				}).start();
			}
			waitNextExecution();
		}
	}

	private void waitNextExecution() {
		Random random = new Random();
		try {
			Long next = (450L+random.nextInt(450))*1000;
			Thread.sleep(next);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		
	}
}
