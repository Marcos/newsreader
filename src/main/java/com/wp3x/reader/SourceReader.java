/**
 * 
 */
package com.wp3x.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.wp3x.Source;

public class SourceReader {

	private final Source entryPattern;
	private Reader reader;

	public SourceReader(final Source entryPattern)
			throws ReaderException, IOException {
		this.entryPattern = entryPattern;
		reader = new Reader( entryPattern.getSourceURL(),
				entryPattern.getSource(), entryPattern.getSourceType(),
				entryPattern.getCharset() );
	}

	public Collection<SourceEntry> getEntries() {
		final Collection<SourceEntry> entries = new ArrayList<SourceEntry>();
		Integer counter = entryPattern.getMinResult();
		while (counter <= entryPattern.getMaxResult()) {
			SourceEntry sourceEntry = getSourceEntry( counter );
			entries.add( sourceEntry );
			counter += entryPattern.getInterval();
		}
		return entries;
	}

	private SourceEntry getSourceEntry(Integer counter) {
		try {
			final String date = readDate( counter );
			final String title = readTitle( counter );
			final String link = readLink( counter );
			final String text = getText( counter );
			SourceEntry sourceEntry = new SourceEntry( date, title, link, text,
					entryPattern );
			return sourceEntry;
		} catch (Exception e) {
			return new SourceEntry();
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
