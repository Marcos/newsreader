package com.openwp3x;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Random;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.openwp3x.db.DatabaseManager;
import com.openwp3x.db.EntityManagerUtil;
import com.openwp3x.jobs.LinkShortener;
import com.openwp3x.jobs.StatusEntry;
import com.openwp3x.model.Entry;

public class ImportNews {
	
	Logger logger = Logger.getLogger(this.getClass());

	public void importNews(Collection<SourceEntry> entries) {
		for (SourceEntry entry : entries) {
			if (isValidEntry(entry) && notExist(entry)) {
				EntityManager entityManager = EntityManagerUtil.getEntityManager();
				entityManager.getTransaction().begin();
				
				importEntry(entry, entityManager);
				
				entityManager.getTransaction().commit();
			}
		}
	}

	private boolean isValidEntry(SourceEntry entry) {
		return entry!=null && entry.getTitle()!=null && !entry.getTitle().trim().equals("")
				&& entry.getUrl()!=null && !entry.getUrl().trim().equals("");
	}

	private boolean notExist(SourceEntry entry) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("select * from entry where title_entry=? and source=? ");
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

	private synchronized void importEntry(SourceEntry sourceEntry, EntityManager entityManager) {
		Entry entry = new Entry();
		
		Calendar calendar = Calendar.getInstance();
		Timestamp timeStampImport = new Timestamp(calendar.getTimeInMillis());
		
		Date dateImport = new Date(calendar.getTimeInMillis());
		entry.setStatus(StatusEntry.NOT_VERIFIED.ordinal());
		entry.setDateInsert(timeStampImport);
		entry.setDateSource(sourceEntry.getDate());
		entry.setUrlSource(sourceEntry.getUrl());
		entry.setTitleSource(sourceEntry.getTitle());
		entry.setSource(sourceEntry.getSource());
		entry.setTitle(sourceEntry.getFormattedTitle());
		entry.setLink(sourceEntry.getFormattedURL());
		entry.setDatePublished(getDatePublished(sourceEntry, dateImport));
		entry.setSourceLabel(sourceEntry.getSourceLabel());
		entry.setRandomFactor(new Random().nextLong());
		
		entityManager.persist(entry);
		
	}

	private void importTags(Long entryId, Collection<Tag> tags) {
		if(tags!=null){			
			for(Tag tag : tags){
				importTag(entryId, tag);
			}
		}
		
	}

	private void importTag(Long entryId, Tag tag) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("insert into tag_entry (entry_id, tag_id) values (?,?)");
			preparedStatement.setLong(1, entryId);
			preparedStatement.setString(2, tag.name());
			preparedStatement.execute();
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

	private Date getDatePublished(SourceEntry entry, Date dateImport) {
		if (entry.getDateAsLong() != null) {
			return new java.sql.Date(entry.getDateAsLong());
		}
		return dateImport;
	}

}
