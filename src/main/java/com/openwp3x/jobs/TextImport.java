package com.openwp3x.jobs;

import java.net.URL;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.openwp3x.NuveoJob;
import com.openwp3x.SourcePattern;
import com.openwp3x.SourcePatternFactory;
import com.openwp3x.db.EntityManagerUtil;
import com.openwp3x.model.Entry;
import com.openwp3x.reader.LinkEntry;
import com.openwp3x.reader.LinkReader;

public class TextImport implements NuveoJob{

	Logger logger = Logger.getLogger(this.getClass());

	public void execute() {
		logger.info("Starting text importing");
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		Query query = entityManager.createQuery("select entry from Entry entry where status=0");
		Collection<Entry> entries = query.getResultList();
		
		if(entries!=null && entries.size()>0){
			for(Entry entry : entries){
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
