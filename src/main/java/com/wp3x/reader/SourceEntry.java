package com.wp3x.reader;

import static java.lang.Boolean.TRUE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.wp3x.Source;

@NoArgsConstructor
@Data
public class SourceEntry {

	private static final int DEFAULT_SHORT_TEXT = 200;

	private String title;

	private String date;

	private String url;

	private String text;

	private Source entryPattern;

	private TextProcessor textPreProcessor = new TextProcessor();

	public SourceEntry(final String date, final String title, final String url,
			String text, final Source entryPattern) {
		this.date = date;
		this.title = title;
		this.url = url;
		this.text = text;
		this.entryPattern = entryPattern;
	}

	public String getFormattedTitle() {
		String title = this.getTitle();
		title = textPreProcessor.treatPrefixPattern(title,
				this.entryPattern.getTitlePrefixPattern());
		title = textPreProcessor.treatSufixPattern(title,
				this.entryPattern.getTitleSufixPattern());
		if (TRUE == this.entryPattern.getTitleNormalize()) {
			title = textPreProcessor.normalize(title);
		}
		return title.trim();
	}

	public String getSource() {
		return this.getEntryPattern().getSource();
	}

	public String getFormattedURL() {
		String url = this.getUrl();

		if (this.entryPattern.getUrlPattern() != null) {
			url = textPreProcessor.getRegexToken(url,
					this.entryPattern.getUrlPattern());
		}

		final String urlResource = this.getEntryPattern().getUrlResource();
		if (urlResource != null) {
			url = urlResource + url;
		}

		return url.trim();
	}

	public Long getDateAsLong() {
		return getDateAsLong(this.date, this.entryPattern.getLocale(),
				this.entryPattern.getDateFormat());
	}

	public Long getDateAsLong(final String date, final Locale locale,
			final String dateFormat) {
		Locale.setDefault(locale);
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				dateFormat);
		if (date != null) {
			try {
				return simpleDateFormat.parse(date).getTime();
			} catch (final ParseException e) {
			}
		}
		return null;
	}

	public String getSourceLabel() {
		return this.getEntryPattern().getSourceLabel();
	}

	public String getFormattedText() {
		return textPreProcessor.normalizeText(this.getText());
	}

	public String getShortText() {
		return textPreProcessor.getShortText(text, DEFAULT_SHORT_TEXT);
	}

}
