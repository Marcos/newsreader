package com.wp3x.jobs;

import java.net.URL;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.wp3x.NuveoJob;
import com.wp3x.SourcePattern;
import com.wp3x.SourcePatternFactory;
import com.wp3x.db.EntityManagerUtil;
import com.wp3x.model.News;
import com.wp3x.reader.LinkEntry;
import com.wp3x.reader.LinkReader;

public class TextImport implements NuveoJob{

	Logger logger = Logger.getLogger(this.getClass());

	public void execute() {
		logger.info("Starting text importing");
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		Query query = entityManager.createQuery("select entry from News entry where status=0");
		Collection<News> entries = query.getResultList();
		
		if(entries!=null && entries.size()>0){
			for(News entry : entries){
				entityManager.getTransaction().begin();
				
				try{					
					SourcePattern sourcePattern = SourcePatternFactory.getSourcePattern(entry.getSource());	
					LinkReader linkReader = new LinkReader(sourcePattern, new URL(entry.getLink()));
					LinkEntry linkEntry = linkReader.getLinkEntry();
					String text = linkEntry.getText();
					String formattedText = linkEntry.getFormattedText();
					String shortText = linkEntry.getShortText();
					entry.setTextSource(text);
					entry.setText(formattedText);
					entry.setShortText(shortText);
					entry.setStatus(1);
				}catch(Exception e) {
					logger.error("Error getting text from " + entry.getLink(), e);
				}
				
				entityManager.getTransaction().commit();
			}
		}
		logger.info("Finishing text importing");
	}

}
