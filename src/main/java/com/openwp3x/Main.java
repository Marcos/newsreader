package com.openwp3x;

import com.openwp3x.jobs.ImportsEntries;


public class Main {

	public static void main(String[] args) {			
		new SchedulerJobs().start();
		new ImportsEntries().start();
	}

}
