package com.openwp3x.reader;

import java.net.URL;

import com.openwp3x.EntryPattern;
import com.openwp3x.Reader;
import com.openwp3x.ReaderException;

public class LinkReader {

	private EntryPattern entryPattern;
	private Reader reader;
	
	public LinkReader(EntryPattern entryPattern, URL linkUrl) {	
		this.entryPattern = entryPattern;
		this.reader = new Reader(linkUrl, entryPattern.getSource(), entryPattern.getSourceType(), entryPattern.getCharset());
		
	}

	public LinkEntry getLinkEntry() throws LinkException {
		LinkEntry linkEntry = new LinkEntry();
		try {
			String linkText = this.reader.getTextContent(entryPattern.getLinkTextXPath());			
			String formattedLinkText = this.reader.clearText(linkText, entryPattern.getLinkTextPrefixPattern(), entryPattern.getLinkTextSufixPattern());
			
			linkEntry.setLinkText(linkText);
			linkEntry.setFormattedLinkText(formattedLinkText);
			return linkEntry;
		} catch (ReaderException e) {
			throw new LinkException("Error getting link from " + entryPattern.getSource(), e);
		}
	}

}
