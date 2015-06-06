package com.wp3x.content;

import lombok.Data;

@Data
public class Link {
	
	private String text;
	
	private String imgPath;
				
	private TextProcessor textPreProcessor = new TextProcessor();

	
	public String getFormattedText() {
		return textPreProcessor.normalizeText(this.getText());
	}

	public String getShortText() {
		return textPreProcessor.getShortText(this.getText(), 200);
	}
}
