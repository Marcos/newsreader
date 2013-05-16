package com.openwp3x.reader;

import java.net.URL;

import com.openwp3x.SourcePattern;

public class LinkReader {

	private SourcePattern entryPattern;
	private Reader reader;
	
	public LinkReader(SourcePattern entryPattern, URL linkUrl) {	
		this.entryPattern = entryPattern;
		this.reader = new Reader(linkUrl, entryPattern.getSource(), entryPattern.getSourceType(), entryPattern.getCharset());
		
	}

	public LinkEntry getLinkEntry() throws LinkException {
		LinkEntry linkEntry = new LinkEntry();
		try {
			String linkText = this.reader.getTextContent(entryPattern.getLinkTextXPath());			
			linkEntry.setText(linkText);
			return linkEntry;
		} catch (ReaderException e) {
			throw new LinkException("Error getting link from " + entryPattern.getSource(), e);
		}
	}

}
