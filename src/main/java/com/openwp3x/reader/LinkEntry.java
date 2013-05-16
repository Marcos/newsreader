package com.openwp3x.reader;

public class LinkEntry {
	
	private String text;
			
	private TextPreProcessor textPreProcessor = new TextPreProcessor();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFormattedText() {
		return textPreProcessor.normalizeText(this.getText());
	}

}
