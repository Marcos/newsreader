package com.openwp3x;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.apache.log4j.Logger;

public class ImportNewsThread extends Thread{
	
	static final ImportNews importNews = new ImportNews();
	
	static Collection<EntryPattern> entryPatternList;
	
	static Logger logger = Logger.getLogger(ImportNewsThread.class);

	public static Collection<EntryPattern> getEntryPatternList(){
		if(entryPatternList==null){
			entryPatternList = new ArrayList<EntryPattern>();
			try {
				entryPatternList.add(EntryPatternFactory.getPrefeituraPattern());
				entryPatternList.add(EntryPatternFactory.getSociescPattern());
				entryPatternList.add(EntryPatternFactory.getUdescPattern());
				entryPatternList.add(EntryPatternFactory.getUnivillePattern());
				entryPatternList.add(EntryPatternFactory.getAcijPattern());
				entryPatternList.add(EntryPatternFactory.getAjorpemePattern());
				entryPatternList.add(EntryPatternFactory.getCDLPattern());
				entryPatternList.add(EntryPatternFactory.getDefesaCivilPattern());
				entryPatternList.add(EntryPatternFactory.getCVJoinvillePattern());
				//entryPatternList.add(EntryPatternFactory.getAnoticiaPattern());
				//entryPatternList.add(EntryPatternFactory.getNDJoinvillePattern());
				//entryPatternList.add(EntryPatternFactory.getPortalJoinvillePattern());
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
		return entryPatternList;
	}
	
	public void runImport() {
		
	}
	
	@Override
	public void run() {
		while(true){
			logger.info("Executing at " + new java.util.Date());
			Collection<EntryPattern> entryPatterns = getEntryPatternList();
			for(final EntryPattern entryPattern: entryPatterns){		
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							logger.info("Importing from " + entryPattern.getSource() + " " + entryPattern.getSourceURL());
							importNews.importNews(new ReaderImpl(entryPattern).getEntries());
							logger.info("Finished " + entryPattern.getSource());
							//new LinkShortener().updateLinks();
						} catch (Exception e) {
							logger.error("Error importing " + entryPattern.getSource(), e);
						} 
						
					}
				}).start();
			}
			Random random = new Random();
			try {
				Long next = (900L+random.nextInt(900))*1000;
				Thread.sleep(next);
			} catch (InterruptedException e) {
				logger.error(e);
			}
		}
	}
}
