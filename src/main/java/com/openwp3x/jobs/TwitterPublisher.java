package com.openwp3x.jobs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.openwp3x.NuveoJob;
import com.openwp3x.db.DatabaseManager;

public class TwitterPublisher implements NuveoJob{

	private static final String NUVEO_LINK_VIEW = "http://nuveo.me/v";
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public void execute() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		logger.info("Publishing on twitter");
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("select id, title, short_link from entry where status = 1 and twitter_status is null");
			ResultSet resultSet = preparedStatement.executeQuery();

			Integer countLinks = 0;
			while (resultSet.next()) {
				Long entryId = resultSet.getLong("id");
				String title = resultSet.getString("title");
				String shortLink = resultSet.getString("short_link");
				
				publishOnTwitter(entryId, title, shortLink);
				countLinks++;
			}
			logger.info("Published finished. Total: " + countLinks);
		} catch (Exception e) {
			logger.error("Error getting url to publish on twitter", e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
	}

	private void publishOnTwitter(Long entryId, String title, String shortLink) {
		try {
			Status status = TwitterFactory.getSingleton().updateStatus(getMessage(title, shortLink));
			setAsPublished(entryId);
		} catch (TwitterException e) {
			logger.warn("Can't publish on twitter : " + shortLink, e);
		}
		
	}

	private void setAsPublished(Long entryId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("update entry set twitter_status=1 where id = ?");
			preparedStatement.setLong(1, entryId);
			preparedStatement.executeUpdate();
		} catch (Exception e) {			
			logger.error(e, e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e, e);
			}
		}
	
		
	}

	public static String getMessage(String title, String shortLink) {
		String link = NUVEO_LINK_VIEW + shortLink;
		String message = title + " " + link;
		if(message.length()>140){
			Integer limit = 140 -(link.length()+4);
			message = title.substring(0, limit) + "... " + link; 
		}
		
		return message;
	}

}
