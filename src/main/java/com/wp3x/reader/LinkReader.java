package com.wp3x.reader;

import java.net.URL;

import org.apache.log4j.Logger;

import com.wp3x.SourcePattern;

public class LinkReader {

	private SourcePattern entryPattern;
	private Reader reader;
	private URL linkUrl;
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public LinkReader(SourcePattern entryPattern, URL linkUrl) {
		logger.debug("Getting text from " + linkUrl);
		this.entryPattern = entryPattern;
		this.linkUrl = linkUrl;
		this.reader = new Reader(linkUrl, entryPattern.getSource(), entryPattern.getSourceType(), entryPattern.getCharset());
		
	}

	public LinkEntry getLinkEntry() throws LinkException {
		LinkEntry linkEntry = new LinkEntry();
		try {
			logger.debug("Parsing text from " + linkUrl);
			String linkText = this.reader.getTextContent(entryPattern.getLinkTextXPath());
			String imgPath = this.reader.getTextContent(entryPattern.getImgXPath());
			linkEntry.setText(linkText);
			linkEntry.setImgPath(imgPath);
			return linkEntry;
		} catch (ReaderException e) {
			throw new LinkException("Error getting link from " + entryPattern.getSource(), e);
		}
	}

}