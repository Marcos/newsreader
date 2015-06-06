package com.wp3x.content;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.wp3x.pattern.EntryPattern;

@NoArgsConstructor
@Data
public class Entry {

	private static final int DEFAULT_SHORT_TEXT = 200;

	private String title;

	private String date;

	private String url;

	private String text;

	private EntryPattern entryPattern;

	private TextProcessor textPreProcessor = new TextProcessor();

	public Entry(final String date, final String title, final String url,
			String text, final EntryPattern entryPattern) {
		this.date = date;
		this.title = title;
		this.url = url;
		this.text = text;
		this.entryPattern = entryPattern;
	}

	public String getFormattedTitle() {
		String title = getTitle();
		title = textPreProcessor.treatPrefixPattern( title, entryPattern.getTitlePrefixPattern() );

		return title.trim();
	}

	public String getFormattedURL() {
		String url = this.getUrl();

		if (this.entryPattern.getUrlPattern() != null) {
			url = textPreProcessor.getRegexToken( url,
					this.entryPattern.getUrlPattern() );
		}

		final String urlResource = this.getEntryPattern().getUrlResource();
		if (urlResource != null) {
			url = urlResource + url;
		}

		return url.trim();
	}

	public String getFormattedText() {
		return textPreProcessor.normalizeText( this.getText() );
	}

	public String getShortText() {
		return textPreProcessor.getShortText( text, DEFAULT_SHORT_TEXT );
	}

}
