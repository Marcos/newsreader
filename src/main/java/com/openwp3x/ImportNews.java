package com.openwp3x;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Random;

public class ImportNews {

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
			e.printStackTrace();
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
				e.printStackTrace();
			}
		}
		return false;
	}

	private void importEntry(Entry entry) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("insert into entry (date_insert, date_entry, title_entry, url_entry, source, status, link, date_published, source_label, random_factor, title) values(?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
			preparedStatement.setString(2, entry.getDate());
			preparedStatement.setString(3, entry.getFormattedTitle());
			preparedStatement.setString(4, entry.getUrl());
			preparedStatement.setString(5, entry.getSource());
			preparedStatement.setInt(6, StatusEntry.NOT_VERIFIED.ordinal());
			preparedStatement.setString(7, entry.getFormattedURL());
			preparedStatement.setDate(8, getDate(entry));
			preparedStatement.setString(9, entry.getSourceLabel());
			preparedStatement.setLong(10, new Random().nextLong());
			preparedStatement.setString(11, entry.getFormattedTitle());
			System.out.println("importing " + entry);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private Date getDate(Entry entry) {
		if (entry.getDateAsLong() != null) {
			return new java.sql.Date(entry.getDateAsLong());
		}
		return null;
	}

}
