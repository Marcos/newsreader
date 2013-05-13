/**
 * 
 */
package com.openwp3x;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * @author marcos.ferreira
 * 
 */
public class EntryReader {
	

	private final EntryPattern entryPattern;
	private Reader reader;
	private Logger log = Logger.getLogger(this.getClass());

	public EntryReader(final EntryPattern entryPattern) {
		this.entryPattern = entryPattern;
		this.reader = new Reader(entryPattern.getSourceURL(), entryPattern.getSource(), entryPattern.getSourceType(), entryPattern.getCharset());
	}

	public Collection<EntryImpl> getEntries() {
		this.log.info("Getting entries from " + entryPattern.getSource());
		final Collection<EntryImpl> entries = new ArrayList<EntryImpl>();

		Integer counter = this.entryPattern.getMinResult();
		while (counter <= this.entryPattern.getMaxResult()) {
			try {				
				final String date = this.reader.getDateContent(this.getCurrentEntry(this.entryPattern.getDateXPath(), counter), this.entryPattern.getDateTextPattern());
				final String title = this.reader.getTextContent(this.getCurrentEntry(this.entryPattern.getTitleXPath(), counter));
				final String link = this.reader.getTextContent(this.getCurrentEntry(this.entryPattern.getUrlXPath(), counter));
				entries.add(new EntryImpl(date, title, link, this.entryPattern));
				counter += this.entryPattern.getInterval();
			} catch (Exception e) {
				log.error("Error getting entry from " + entryPattern.getSource(), e);
			}
		}
		this.log.info("Finished from " + entryPattern.getSource());
		return entries;
	}

	/**
	 * @param dateXPath
	 * @param i
	 * @return
	 */
	private String getCurrentEntry(final String xPath, final Integer i) {
		if(xPath!=null){
			return xPath.replace("{_counter}", i.toString());
		}
		return null;
	}

}
