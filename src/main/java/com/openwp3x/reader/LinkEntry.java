package com.openwp3x.reader;

public class LinkEntry {
	
	private String linkText;
		
	private String shortText;
	
	private TextPreProcessor textPreProcessor = new TextPreProcessor();

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getFormattedLinkText() {
		return textPreProcessor.normalizeWordWrap(this.getLinkText());
	}

	public String getShortText() {
		return shortText;
	}

	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

}
