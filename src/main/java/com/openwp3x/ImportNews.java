package com.openwp3x;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Random;

import org.apache.log4j.Logger;

public class ImportNews {
	
	Logger logger = Logger.getLogger(this.getClass());

	public void importNews(Collection<EntryImpl> entries) {
		for (EntryImpl entry : entries) {
			if (isValidEntry(entry) && notExist(entry)) {
				importEntry(entry);
			}
		}
	}

	private boolean isValidEntry(EntryImpl entry) {
		return entry!=null && entry.getTitle()!=null && !entry.getTitle().trim().equals("")
				&& entry.getUrl()!=null && !entry.getUrl().trim().equals("");
	}

	private boolean notExist(EntryImpl entry) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("select * from entry where title_entry=? and source=?");
			preparedStatement.setString(1, entry.getFormattedTitle());
			preparedStatement.setString(2, entry.getSource());
			ResultSet resultSet = preparedStatement.executeQuery();
			return !resultSet.next();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return false;
	}

	private synchronized void importEntry(EntryImpl entry) {
		Long nextId = getNextId();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(getInsertQuery());
			Timestamp timeStampImport = new Timestamp(System.currentTimeMillis());
			Date dateImport = new Date(System.currentTimeMillis());
			preparedStatement.setLong(1, nextId);
			preparedStatement.setTimestamp(2, timeStampImport);
			preparedStatement.setString(3, entry.getDate());
			preparedStatement.setString(4, entry.getFormattedTitle());
			preparedStatement.setString(5, entry.getUrl());
			preparedStatement.setString(6, entry.getSource());
			preparedStatement.setInt(7, StatusEntry.NOT_VERIFIED.ordinal());
			preparedStatement.setString(8, entry.getFormattedURL());
			preparedStatement.setDate(9, getDatePublished(entry, dateImport));
			preparedStatement.setString(10, entry.getSourceLabel());
			preparedStatement.setLong(11, new Random().nextLong());
			preparedStatement.setString(12, entry.getFormattedTitle());
			preparedStatement.setString(13, LinkShortener.converter(nextId));
			preparedStatement.execute();
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

	private Long getNextId() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("select max(id)+1 next_id from entry");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getLong("next_id");
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return 1L;
	}

	private String getInsertQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into entry (");
		sb.append("id, ");
		sb.append("date_insert, ");
		sb.append("date_entry, ");
		sb.append("title_entry, ");
		sb.append("url_entry, source, ");
		sb.append("status, link, ");
		sb.append("date_published, ");
		sb.append("source_label, ");
		sb.append("random_factor, ");
		sb.append("title, ");
		sb.append("short_link) ");
		sb.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		return sb.toString();
	}

	private Date getDatePublished(EntryImpl entry, Date dateImport) {
		if (entry.getDateAsLong() != null) {
			return new java.sql.Date(entry.getDateAsLong());
		}
		return dateImport;
	}

}
