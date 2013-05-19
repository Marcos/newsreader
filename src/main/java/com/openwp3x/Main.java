package com.openwp3x;

import org.apache.log4j.Logger;

import com.openwp3x.db.EntityManagerUtil;
import com.openwp3x.jobs.ImportsEntries;


public class Main {

	static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		EntityManagerUtil.getEntityManager();
		logger.info("Starting app!");
		new SchedulerJobs().start();
		new ImportsEntries().start();
	}

}
