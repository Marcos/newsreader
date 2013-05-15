package com.openwp3x;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.openwp3x.db.DatabaseManager;
import com.openwp3x.db.EntityManagerUtil;
import com.openwp3x.jobs.StatusEntry;
import com.openwp3x.model.Entry;
import com.openwp3x.model.Tag;
import com.openwp3x.model.TagType;

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
		
		Collection<Tag> tags = getTags(entityManager, sourceEntry.getTags());
		entry.setTags(tags);
		
		entityManager.persist(entry);
	}

	private Collection<Tag> getTags(EntityManager entityManager, Collection<TagType> tagTypes) {
		Collection<Tag> tags = new HashSet<Tag>();
		for(TagType tagType : tagTypes){
			Tag tag = entityManager.find(Tag.class, tagType.toString());
			tags.add(tag);
		}
		return tags;
	}
//
//	private void importTags(Long entryId, Collection<TagType> tags) {
//		if(tags!=null){			
//			for(TagType tag : tags){
//				importTag(entryId, tag);
//			}
//		}
//		
//	}
//
//	private void importTag(Long entryId, TagType tag) {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		try {
//			connection = DatabaseManager.getConnection();
//			preparedStatement = connection.prepareStatement("insert into tag_entry (entry_id, tag_id) values (?,?)");
//			preparedStatement.setLong(1, entryId);
//			preparedStatement.setString(2, tag.name());
//			preparedStatement.execute();
//		} catch (Exception e) {			
//			logger.error(e, e);
//		} finally {
//			try {
//				preparedStatement.close();
//			} catch (SQLException e) {
//				logger.error(e, e);
//			}
//		}
//		
//	}

	private Date getDatePublished(SourceEntry entry, Date dateImport) {
		if (entry.getDateAsLong() != null) {
			return new java.sql.Date(entry.getDateAsLong());
		}
		return dateImport;
	}

}
