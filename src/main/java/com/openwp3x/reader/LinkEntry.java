package com.openwp3x.reader;

public class LinkEntry {
	
	private String text;
				
	private TextProcessor textPreProcessor = new TextProcessor();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFormattedText() {
		return textPreProcessor.normalizeText(this.getText());
	}

	public String getShortText() {
		return textPreProcessor.getShortText(this.getText(), 200);
	}

}
