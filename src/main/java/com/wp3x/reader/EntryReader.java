/**
 * 
 */
package com.wp3x.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.wp3x.content.Entry;
import com.wp3x.exceptions.ReaderException;
import com.wp3x.pattern.EntryPattern;

public class EntryReader {

	private final EntryPattern entryPattern;
	private Reader reader;

	public EntryReader(final EntryPattern entryPattern, Reader reader)
			throws ReaderException, IOException {
		this.entryPattern = entryPattern;
		this.reader = reader;
	}

	public Collection<Entry> getEntries() {
		final Collection<Entry> entries = new ArrayList<Entry>();
		Integer counter = entryPattern.getMinResult();
		while (counter <= entryPattern.getMaxResult()) {
			Entry sourceEntry = getSourceEntry( counter );
			entries.add( sourceEntry );
			counter += entryPattern.getInterval();
		}
		return entries;
	}

	private Entry getSourceEntry(Integer counter) {
		try {
			final String date = readDate( counter );
			final String title = readTitle( counter );
			final String link = readLink( counter );
			final String text = getText( counter );
			Entry sourceEntry = new Entry( date, title, link, text,
					entryPattern );
			return sourceEntry;
		} catch (Exception e) {
			return new Entry();
		}
	}

	private String getText(Integer counter) throws ReaderException {
		String text = entryPattern.getText();
		if (text != null) {
			return reader.getTextContent( getCurrentEntry( text, counter ) );
		}
		return null;
	}

	private String readLink(Integer counter) throws ReaderException {
		return reader.getTextContent( getCurrentEntry(
				entryPattern.getUrl(), counter ) );
	}

	private String readTitle(Integer counter) throws ReaderException {
		return reader.getTextContent( getCurrentEntry(
				entryPattern.getTitle(), counter ) );
	}

	private String readDate(Integer counter) throws ReaderException {
		return reader.getDateContent(
				getCurrentEntry( entryPattern.getDate(), counter ),
				entryPattern.getDateTextPattern() );
	}

	private String getCurrentEntry(final String xpath, final Integer i) {
		if (xpath != null) {
			return xpath.replace( "{_counter}", i.toString() );
		}
		return null;
	}

}
