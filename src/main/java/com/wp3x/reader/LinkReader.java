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
		this.reader = new Reader(linkUrl, entryPattern.getSource(), SourceType.HTML, entryPattern.getCharset());
		
	}

	public LinkEntry getLinkEntry() {
		LinkEntry linkEntry = new LinkEntry();
		try {
			logger.debug("Parsing text from " + linkUrl);
			String linkText = this.reader.getTextContent(entryPattern.getLinkTextXPath());
			linkEntry.setText(linkText);
		} catch (ReaderException e) {
			logger.warn("Error getting text from " + entryPattern.getSource(), e);
		}
		
		try{
			String imgPath = this.reader.getTextContent(entryPattern.getImgXPath());
			linkEntry.setImgPath(imgPath);
		}catch (Exception e) {
			logger.warn("Error getting image from " + entryPattern.getSource(), e);
		}
		return linkEntry;
	}

}
