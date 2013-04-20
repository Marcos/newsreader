/**
 * 
 */
package com.openwp3x;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringEscapeUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.xml.sax.SAXException;

/**
 * @author marcos.ferreira
 * 
 */
public class EntryReader {
    private static final String DEFAULT_CHARSET = "UTF-8";
    final org.w3c.dom.Document doc;
    final EntryPattern entryPattern;

    public EntryReader(final EntryPattern entryPattern) throws IOException, ParserConfigurationException {
        final InputStream inputStream = this.getContent(entryPattern.getSourceURL());

        final TagNode tagNode = new HtmlCleaner().clean(inputStream, entryPattern.getSourceCharset());
        this.doc = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
        this.entryPattern = entryPattern;
    }

    public String getTitle(final String xpathPattern) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        return this.getTextContent(xpathPattern).trim();
    }

    public String getTextContent(final String xpathPattern) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        final XPath xpath = XPathFactory.newInstance().newXPath();
        final String str = (String) xpath.evaluate(xpathPattern, this.doc, XPathConstants.STRING);

        final String standart = this.getStringAsStandart(str, EntryReader.DEFAULT_CHARSET);
        return standart;
    }

    /**
     * @param str
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    private String getStringAsStandart(final String str, final String charset) throws UnsupportedEncodingException {
        return StringEscapeUtils.unescapeHtml4(str);
    }

    /**
     * @param url
     * @return
     * @throws IOException
     */
    private InputStream getContent(final URL url) throws IOException {
        return url.openStream();
    }

    /**
     * @param resource
     * @param charset
     * @param string
     * @param string2
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws XPathExpressionException
     */
    public String getDateContent(final String xpathPattern, final String datePatern) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
        if (xpathPattern != null && datePatern != null) {
            final String content = this.getTextContent(xpathPattern);
            return this.getDateFromText(content, datePatern);
        }
        return null;
    }

    /**
     * @param text
     * @param datePatern
     */
    public String getDateFromText(final String text, final String datePatern) {
        final Pattern pattern = Pattern.compile(datePatern);
        final Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            final String dateText = matcher.group();
            return dateText;
        }
        return null;

    }

    /**
     * @param resource
     * @param string
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws XPathExpressionException
     * @throws SAXException
     */
    public Collection<Entry> getEntries() throws IOException, ParserConfigurationException, XPathExpressionException, SAXException {
        final Collection<Entry> entries = new ArrayList<Entry>();

        Integer counter = this.entryPattern.getMinResult();
        while (counter <= this.entryPattern.getMaxResult()) {
            final String date = this.getDateContent(this.getCurrentEntry(this.entryPattern.getDateXPath(), counter), this.entryPattern.getDateTextPattern());
            final String title = this.getTextContent(this.getCurrentEntry(this.entryPattern.getTitleXPath(), counter));
            final String link = this.getTextContent(this.getCurrentEntry(this.entryPattern.getUrlXPath(), counter));
            entries.add(new Entry(date, title, link, this.entryPattern));
            counter += this.entryPattern.getInterval();
        }

        return entries;
    }

    /**
     * @param dateXPath
     * @param i
     * @return
     */
    private String getCurrentEntry(final String xPath, final Integer i) {
        return xPath.replace("{_counter}", i.toString());
    }
}
