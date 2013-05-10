package com.openwp3x.social;


import org.junit.Ignore;
import org.junit.Test;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Ignore
public class TestTwitterPublisher {
	
	@Test
	public void testTwitterPublish() throws TwitterException{
		Twitter twitter = TwitterFactory.getSingleton();
	    Status status = twitter.updateStatus("teste");
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}

}
