package com.openwp3x;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Random;

import org.apache.log4j.Logger;

public class ImportNews {
	
	Logger logger = Logger.getLogger(this.getClass());

	public static void main(String[] args) {
		new ImportNews().start();
	}

	private void start() {
		try {
			importNews(new EntryReader(EntryPatternFactory.getPrefeituraPattern()).getEntries());
			importNews(new EntryReader(EntryPatternFactory.getSociescPattern()).getEntries());
			importNews(new EntryReader(EntryPatternFactory.getUdescPattern()).getEntries());
			importNews(new EntryReader(EntryPatternFactory.getUnivillePattern()).getEntries());
			importNews(new EntryReader(EntryPatternFactory.getAcijPattern()).getEntries());
			importNews(new EntryReader(EntryPatternFactory.getAjorpemePattern()).getEntries());
			importNews(new EntryReader(EntryPatternFactory.getCDLPattern()).getEntries());
			importNews(new EntryReader(EntryPatternFactory.getDefesaCivilPattern()).getEntries());
		} catch (Exception e) {
			logger.error(e);
		}

	}

	private void importNews(Collection<Entry> entries) {
		for (Entry entry : entries) {
			if (notExist(entry)) {
				importEntry(entry);
			}
		}
	}

	private boolean notExist(Entry entry) {
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
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return false;
	}

	private void importEntry(Entry entry) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(getInsertQuery());
			Date dateImport = new Date(System.currentTimeMillis());
			preparedStatement.setDate(1, dateImport);
			preparedStatement.setString(2, entry.getDate());
			preparedStatement.setString(3, entry.getFormattedTitle());
			preparedStatement.setString(4, entry.getUrl());
			preparedStatement.setString(5, entry.getSource());
			preparedStatement.setInt(6, StatusEntry.NOT_VERIFIED.ordinal());
			preparedStatement.setString(7, entry.getFormattedURL());
			preparedStatement.setDate(8, getDatePublished(entry, dateImport));
			preparedStatement.setString(9, entry.getSourceLabel());
			preparedStatement.setLong(10, new Random().nextLong());
			preparedStatement.setString(11, entry.getFormattedTitle());
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

	private String getInsertQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into entry (");
		sb.append("date_insert, ");
		sb.append("date_entry, ");
		sb.append("title_entry, ");
		sb.append("url_entry, source, ");
		sb.append("status, link, ");
		sb.append("date_published, ");
		sb.append("source_label, ");
		sb.append("random_factor, ");
		sb.append("title) ");
		sb.append("values(?,?,?,?,?,?,?,?,?,?,?)");
		return sb.toString();
	}

	private Date getDatePublished(Entry entry, Date dateImport) {
		if (entry.getDateAsLong() != null) {
			return new java.sql.Date(entry.getDateAsLong());
		}
		return dateImport;
	}

}
