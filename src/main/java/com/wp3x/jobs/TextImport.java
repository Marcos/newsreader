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
import com.wp3x.model.Picture;
import com.wp3x.reader.LinkEntry;
import com.wp3x.reader.LinkReader;
import com.wp3x.reader.PictureRepository;

public class TextImport implements NuveoJob{

	Logger logger = Logger.getLogger(this.getClass());
	
	PictureRepository pictureRepository = new PictureRepository();

	public void execute() {
		logger.debug("Starting text importing");
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		Query query = entityManager.createQuery("select entry from News entry where status=0");
		Collection<News> entries = query.getResultList();
		
		if(entries!=null && entries.size()>0){
			for(News entry : entries){
				entityManager.getTransaction().begin();
				try{					
					logger.debug("Reading link");
					SourcePattern sourcePattern = SourcePatternFactory.getSourcePattern(entry.getSource());	
					LinkReader linkReader = new LinkReader(sourcePattern, new URL(entry.getLink()));
					LinkEntry linkEntry = linkReader.getLinkEntry();
					
					if(entry.getText()==null){
						logger.debug("Text is emty. Start importing text: " + entry.getLink());
						String text = linkEntry.getText();
						String formattedText = linkEntry.getFormattedText();
						String shortText = linkEntry.getShortText();
						
						entry.setTextSource(text);
						entry.setText(formattedText);
						entry.setShortText(shortText);
					}
					logger.debug("Importing picture: " + linkEntry.getImgPath());
					Picture picture = getPicture(linkEntry.getImgPath(), entry.getSource(), entry.getShortLink());
					
					entry.setPicture(picture);
					entry.setStatus(1);
					
					logger.debug("Imported text and picture from url : " + entry.getTitle());
					entityManager.getTransaction().commit();
				}catch(Exception e) {
					logger.error("Error getting text from " + entry.getLink(), e);
				}
				
			}
		}
		logger.info("Finishing text importing");
	}

	private Picture getPicture(String imgPath, String source, String shortLink) {
		if(imgPath!=null){						
			try {
				Picture picture = pictureRepository.getImportedImg(imgPath, source, shortLink);
				return picture;
			} catch (Exception e) {
				logger.warn("Can't get image from " + imgPath);
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		new TextImport().execute();
	}


}
