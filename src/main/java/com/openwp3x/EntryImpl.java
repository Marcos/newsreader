/**
 * 
 */
package com.openwp3x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author marcos.ferreira
 * 
 */
public class EntryImpl implements Entry {

    String title;

    String date;

    String url;

    EntryPattern entryPattern;

    public EntryImpl() {
    	
    }
    
    /**
     * @param source
     * @param link
     * @param date2
     * @param title2
     */
    public EntryImpl(final String date, final String title, final String url, final EntryPattern entryPattern) {
        this.date = date;
        this.title = title;
        this.url = url;
        this.entryPattern = entryPattern;
    }


	/* (non-Javadoc)
	 * @see com.openwp3x.Entry#getFormattedTitle()
	 */
    @Override
	public String getFormattedTitle() {
    	String title = this.getTitle();
    	title = treatPrefixPattern(title, this.entryPattern.getTitlePrefixPattern());
    	title = treatSufixPattern(title, this.entryPattern.getTitleSufixPattern());
    	return title.trim();
    	
    }
    
    public String treatSufixPattern(String text, String titleSufixPattern) {
    	if(titleSufixPattern!=null){
    		Pattern pattern = Pattern.compile(titleSufixPattern);
    		Matcher matcher = pattern.matcher(text);
    		if(matcher.find()){    			
    			Integer endPosition = matcher.start();
				text = text.substring(0, endPosition);
    		}
    	}
    	return text;
	}
    
    public String treatPrefixPattern(String text, String titlePrefixPattern) {
    	if(titlePrefixPattern!=null){
    		Pattern pattern = Pattern.compile(titlePrefixPattern);
    		Matcher matcher = pattern.matcher(text);
    		if(matcher.find()){    			
    			Integer startPosition = matcher.end();
				text = text.substring(startPosition);
    		}
    	}
    	return text;
	}

	public String getTitle(){
    	return this.title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Entry [title=" + this.title + ", date=" + this.date + ", url=" + this.url + "]";
    }

    public String getSource() {
        return this.getEntryPattern().getSource();
    }

    /* (non-Javadoc)
	 * @see com.openwp3x.Entry#getFormattedURL()
	 */
    @Override
	public String getFormattedURL() {
        String url = this.getUrl();

        if (this.entryPattern.getUrlPattern() != null) {
            final Pattern pattern = Pattern.compile(this.entryPattern.getUrlPattern());
            final Matcher matcher = pattern.matcher(url);
            matcher.find();
            url = matcher.group();
        }

        final String urlResource = this.getEntryPattern().getUrlResource();
        if (urlResource != null) {
            url = urlResource + url;
        }

        return url.trim();
    }

    public EntryPattern getEntryPattern() {
        return this.entryPattern;
    }

    public void setEntryPattern(final EntryPattern entryPattern) {
        this.entryPattern = entryPattern;
    }

    public Long getDateAsLong() {
        return getDateAsLong(this.date, this.entryPattern.getLocale(), this.entryPattern.getDateFormat());
    }

    public Long getDateAsLong(final String date, final Locale locale, final String dateFormat) {
        Locale.setDefault(locale);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
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
    
    

}
