package com.wp3x;

import org.apache.log4j.Logger;

import com.wp3x.db.EntityManagerUtil;
import com.wp3x.jobs.ImportsEntries;


public class Main {

	static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		EntityManagerUtil.getEntityManager();
		logger.info("Starting app!");
		new SchedulerJobs().start();
		//new ImportsEntries().start();
	}

}
