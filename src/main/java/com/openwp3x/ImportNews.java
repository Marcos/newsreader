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
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.openwp3x.db.DatabaseManager;
import com.openwp3x.db.EntityManagerUtil;
import com.openwp3x.jobs.StatusEntry;
import com.openwp3x.model.Entry;

public class ImportNews {
	
	Logger logger = Logger.getLogger(this.getClass());

	public void importNews(Collection<SourceEntry> entries) {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		for (SourceEntry entry : entries) {
			if (isValidEntry(entry) && notExist(entry, entityManager)) {
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

	private boolean notExist(SourceEntry entry, EntityManager entityManager) {
		Query query = getUniqueQuery(entityManager, entry);
		Long result = (Long) query.getSingleResult();
		return result<1;
	}

	private Query getUniqueQuery(EntityManager entityManager, SourceEntry sourceEntry) {
		StringBuilder ql = new StringBuilder();
		ql.append("select count(*) from Entry where "); 
		ql.append("(urlSource=:urlSource and source=:source) "); 
		ql.append("or "); 
		ql.append("(titleSource=:titleSource and source=:source) ");
		Query query = entityManager.createQuery(ql.toString());
		query.setParameter("urlSource", sourceEntry.getUrl());
		query.setParameter("source", sourceEntry.getSource());
		query.setParameter("titleSource", sourceEntry.getTitle());
		query.setParameter("source", sourceEntry.getSource());
		return query;
	}

	private void importEntry(SourceEntry sourceEntry, EntityManager entityManager) {
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
		
		synchronized (entityManager) {
			entityManager.persist(entry);
		}
		
		
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
