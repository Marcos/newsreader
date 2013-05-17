package com.openwp3x.jobs;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Assert;

import org.apache.log4j.Logger;

import com.openwp3x.NuveoJob;
import com.openwp3x.SourcePattern;
import com.openwp3x.SourcePatternFactory;
import com.openwp3x.db.DatabaseManager;
import com.openwp3x.reader.LinkEntry;
import com.openwp3x.reader.LinkReader;

public class TextImport implements NuveoJob{

	Logger logger = Logger.getLogger(this.getClass());

	public void execute() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("select id, source, link from entry where status=0");
			ResultSet resultSet = preparedStatement.executeQuery();

			Integer countText = 0;
			while (resultSet.next()) {
				Long entryId = resultSet.getLong("id");
				String link = resultSet.getString("link");
				String source = resultSet.getString("source");
				SourcePattern sourcePattern = SourcePatternFactory.getSourcePattern(source);
				
				LinkReader linkReader = new LinkReader(sourcePattern, new URL(link));
		    	LinkEntry linkEntry = linkReader.getLinkEntry();
		    	String text = linkEntry.getText();
		    	String formattedText = linkEntry.getFormattedText();
				
				updateText(entryId, text, formattedText);
				countText++;
			}
			logger.info("Text updated: " + countText);
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateText(Long entryId, String text, String formattedText) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("update entry set text_entry=?, text=?, status=1 where id=?");
			preparedStatement.setString(1, text);
			preparedStatement.setString(2, formattedText);
			preparedStatement.setLong(3, entryId);
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

}
