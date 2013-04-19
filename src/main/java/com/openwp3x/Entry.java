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
public class Entry {

    String title;

    String date;

    String url;

    EntryPattern entryPattern;

    /**
     * @param source
     * @param link
     * @param date2
     * @param title2
     */
    public Entry(final String date, final String title, final String url, final EntryPattern entryPattern) {
        this.date = date;
        this.title = title;
        this.url = url;
        this.entryPattern = entryPattern;
    }

    /**
     * @return the title
     */
    public String getTitle() {
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

        return url;
    }

    public EntryPattern getEntryPattern() {
        return this.entryPattern;
    }

    public void setEntryPattern(final EntryPattern entryPattern) {
        this.entryPattern = entryPattern;
    }

    public Long getDateAsLong() {
        return Entry.getDateAsLong(this.date, this.entryPattern.getLocale(), this.entryPattern.getDateFormat());
    }

    public static Long getDateAsLong(final String date, final Locale locale, final String dateFormat) {
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
