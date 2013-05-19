package com.openwp3x;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.openwp3x.db.EntityManagerUtil;
import com.openwp3x.jobs.StatusEntry;
import com.openwp3x.model.Entry;
import com.openwp3x.model.Tag;
import com.openwp3x.model.TagType;
import com.openwp3x.reader.SourceEntry;

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
		Query query = entityManager.createQuery("select count(*) from Entry where (urlSource=:urlSource and source=:source) or (titleSource=:titleSource and source=:source) ");
		query.setParameter("urlSource", sourceEntry.getUrl());
		query.setParameter("source", sourceEntry.getSource());
		query.setParameter("titleSource", sourceEntry.getTitle());
		query.setParameter("source", sourceEntry.getSource());
		return query;
	}

	private void importEntry(SourceEntry sourceEntry, EntityManager entityManager) {
		Entry entry = new Entry();
		
		java.util.Date dateImport = new java.util.Date();
		DateTime dateTime = new DateTime();
		
		entry.setStatus(StatusEntry.NOT_VERIFIED.ordinal());
		entry.setDateInsert(dateTime);
		entry.setDateSource(sourceEntry.getDate());
		entry.setUrlSource(sourceEntry.getUrl());
		entry.setTitleSource(sourceEntry.getTitle());
		entry.setSource(sourceEntry.getSource());
		entry.setTitle(sourceEntry.getFormattedTitle());
		entry.setShortText(sourceEntry.getShortText());
		entry.setLink(sourceEntry.getFormattedURL());
		entry.setDatePublished(getDatePublished(sourceEntry, dateImport));
		entry.setSourceLabel(sourceEntry.getSourceLabel());
		if(sourceEntry.getText()!=null){
			entry.setStatus(1);
			entry.setTextSource(sourceEntry.getText());
			entry.setText(sourceEntry.getFormattedText());
		}
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

	private java.util.Date getDatePublished(SourceEntry entry, java.util.Date dateImport) {
		if (entry.getDateAsLong() != null) {
			return new java.sql.Date(entry.getDateAsLong());
		}
		return dateImport;
	}

}
