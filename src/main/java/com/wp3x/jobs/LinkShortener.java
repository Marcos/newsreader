package com.wp3x.jobs;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.wp3x.NuveoJob;
import com.wp3x.db.EntityManagerUtil;
import com.wp3x.model.News;

public class LinkShortener implements NuveoJob{

	Logger logger = Logger.getLogger(this.getClass());

	private static final String baseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public void execute() {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		Query query = entityManager.createQuery("select entry from Entry entry where shortLink is null");
		Collection<News> entries = query.getResultList();
		
		if(entries!=null && entries.size()>0){
			for(News entry : entries){
				entityManager.getTransaction().begin();
				
				String shortLink = converter(entry.getId());
				entry.setShortLink(shortLink);
				entityManager.persist(entry);
				
				entityManager.getTransaction().commit();
			}
		}
	}

	public static String converter(Long decimalNumber) {
		int base = 62;
		String tempVal = decimalNumber == 0 ? "0" : "";
		long mod = 0;

		while (decimalNumber != 0) {
			mod = decimalNumber % base;
			tempVal = baseDigits.substring((int) mod, (int) mod + 1) + tempVal;
			decimalNumber = decimalNumber / base;
		}
		return tempVal;
	}

}
