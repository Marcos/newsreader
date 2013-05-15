package com.openwp3x.jobs;

import java.util.Collection;
import java.util.Random;

import org.apache.log4j.Logger;

import com.openwp3x.ImportNews;
import com.openwp3x.SourcePattern;
import com.openwp3x.SourcePatternFactory;
import com.openwp3x.reader.SourceReader;

public class ImportsEntries extends Thread{
	
	static Logger logger = Logger.getLogger(ImportsEntries.class);

	public void run() {
		while(true){			
			logger.info("Executing at " + new java.util.Date());
			Collection<SourcePattern> entryPatterns = SourcePatternFactory.getEntryPatternList();
			for(final SourcePattern entryPattern: entryPatterns){		
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							new ImportNews().importNews(new SourceReader(entryPattern).getEntries());
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
