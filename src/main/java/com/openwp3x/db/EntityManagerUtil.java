package com.openwp3x.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerUtil {

	private static EntityManagerFactory factory;
	 
    private EntityManagerUtil() {};

    public static EntityManager getEntityManager() {
         if(factory == null) {
              factory = Persistence.createEntityManagerFactory("default");
         }
         return factory.createEntityManager();
    }
	
}
