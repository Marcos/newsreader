/**
 * 
 */
package com.openwp3x;

import java.net.URL;
import java.util.Locale;

/**
 * @author marcos.ferreira
 * 
 */
public class EntryPattern {
	
	private URL sourceURL;
	
	private SourceType sourceType = SourceType.HTML;
	
	private String sourceCharset;

	private String dateTextPattern;

	private String dateFormat = "dd/MM/yyyy";

	private String titleXPath;

	private String dateXPath;

	private String urlXPath;
	
	private String textXPath;

	private String source;

	private String sourceLabel;

	private String urlResource;

	private String titlePrefixPattern;

	private String urlPattern;

	private Integer maxResult = 10;

	private Integer minResult = 1;

	private Integer interval = 1;

	private Locale locale = new Locale("pt", "BR");

    /**
     * @return the title
     */
    public String getTitleXPath() {
        return this.titleXPath;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitleXPath(final String title) {
        this.titleXPath = title;
    }

    /**
     * @return the date
     */
    public String getDateXPath() {
        return this.dateXPath;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDateXPath(final String date) {
        this.dateXPath = date;
    }

    /**
     * @return the url
     */
    public String getUrlXPath() {
        return this.urlXPath;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrlXPath(final String url) {
        this.urlXPath = url;
    }

    /**
     * @return the datePattern
     */
    public String getDateTextPattern() {
        return this.dateTextPattern;
    }

    /**
     * @param datePattern
     *            the datePattern to set
     */
    public void setDateTextPattern(final String datePattern) {
        this.dateTextPattern = datePattern;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public String getUrlResource() {
        return this.urlResource;
    }

    public void setUrlResource(final String urlResource) {
        this.urlResource = urlResource;
    }

    public Integer getMaxResult() {
        return this.maxResult;
    }

    public void setMaxResult(final Integer maxResult) {
        this.maxResult = maxResult;
    }

    public String getTitlePrefixPattern() {
        return this.titlePrefixPattern;
    }

    public void setTitlePrefixPattern(final String urlPrefix) {
        this.titlePrefixPattern = urlPrefix;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    public String getDateFormat() {
        return this.dateFormat;
    }

    public void setDateFormat(final String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getSourceLabel() {
        return this.sourceLabel;
    }

    public void setSourceLabel(final String sourceLabel) {
        this.sourceLabel = sourceLabel;
    }

    /**
     * @return the minResult
     */
    public Integer getMinResult() {
        return this.minResult;
    }

    /**
     * @param minResult
     *            the minResult to set
     */
    public void setMinResult(final Integer minResult) {
        this.minResult = minResult;
    }

    /**
     * @return the interval
     */
    public Integer getInterval() {
        return this.interval;
    }

    /**
     * @param interval
     *            the interval to set
     */
    public void setInterval(final Integer interval) {
        this.interval = interval;
    }

    /**
     * @return the urlPattern
     */
    public String getUrlPattern() {
        return this.urlPattern;
    }

    /**
     * @param urlPattern
     *            the urlPattern to set
     */
    public void setUrlPattern(final String urlPattern) {
        this.urlPattern = urlPattern;
    }

	public URL getSourceURL() {
		return sourceURL;
	}

	public void setSourceURL(URL sourceURL) {
		this.sourceURL = sourceURL;
	}

	public String getSourceCharset() {
		return sourceCharset;
	}

	public void setSourceCharset(String sourceCharset) {
		this.sourceCharset = sourceCharset;
	}

	public SourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}

	public String getTextXPath() {
		return textXPath;
	}

	public void setTextXPath(String textXPath) {
		this.textXPath = textXPath;
	}
}
