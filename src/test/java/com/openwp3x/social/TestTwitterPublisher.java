package com.openwp3x.social;


import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.openwp3x.jobs.TwitterPublisher;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TestTwitterPublisher {
	
	
	@Ignore
	public void testTwitterPublish() throws TwitterException{
		Twitter twitter = TwitterFactory.getSingleton();
	    Status status = twitter.updateStatus("teste");
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
	
	@Test
	public void testGetMessageLessThan140(){
		String title = "Este é um título com menos de 140 caracteres";
		String shortLink = "v3";
		String message = TwitterPublisher.getMessage(title, shortLink);
		System.out.println(message);
		Assert.assertTrue(message.length()<=140);
	}
	
	@Test
	public void testGetMessageMoreThan140(){
		String title = "Este é um título com mais de 140 caracteres. A partir daqui são palavras para aumentar a quantiade de caracter que o texto terá.";
		String shortLink = "v3";
		String message = TwitterPublisher.getMessage(title, shortLink);
		System.out.println(message);
		Assert.assertTrue(message.length()<=140);
	}
	
	@Test
	public void testGetMessageMoreThan140AndBigLink(){
		String title = "Este é um título com mais de 140 caracteres. A partir daqui são palavras para aumentar a quantiade de caracter que o texto terá.";
		String shortLink = "v3f3afsfd7098a70987420987avkl2j3;";
		String message = TwitterPublisher.getMessage(title, shortLink);
		System.out.println(message);
		Assert.assertTrue(message.length()<=140);
	}

}
