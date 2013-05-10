/**
 * 
 */
package com.openwp3x;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author marcos.ferreira
 * 
 */
public class EntryReader {
	

	private final EntryPattern entryPattern;
	private Reader reader;
	private Logger log = Logger.getLogger(this.getClass());

	public EntryReader(final EntryPattern entryPattern) {
		try {
			this.entryPattern = entryPattern;
			this.reader = new Reader(entryPattern.getSourceURL(), entryPattern.getSource(), entryPattern.getSourceType(), entryPattern.getCharset());
		} catch (Throwable e) {
			String message = "Error parsing content from " + entryPattern.getSourceURL();
			log.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	public Collection<EntryImpl> getEntries() throws IOException, ParserConfigurationException, XPathExpressionException, SAXException {
		this.log.info("Getting entries from " + entryPattern.getSource());
		final Collection<EntryImpl> entries = new ArrayList<EntryImpl>();

		Integer counter = this.entryPattern.getMinResult();
		while (counter <= this.entryPattern.getMaxResult()) {
			final String date = this.reader.getDateContent(this.getCurrentEntry(this.entryPattern.getDateXPath(), counter), this.entryPattern.getDateTextPattern());
			final String title = this.reader.getTextContent(this.getCurrentEntry(this.entryPattern.getTitleXPath(), counter));
			final String link = this.reader.getTextContent(this.getCurrentEntry(this.entryPattern.getUrlXPath(), counter));
			entries.add(new EntryImpl(date, title, link, this.entryPattern));
			counter += this.entryPattern.getInterval();
		}
		this.log.info("Finished from " + entryPattern.getSource());
		return entries;
	}

	/**
	 * @param dateXPath
	 * @param i
	 * @return
	 */
	private String getCurrentEntry(final String xPath, final Integer i) {
		if(xPath!=null){
			return xPath.replace("{_counter}", i.toString());
		}
		return null;
	}

}
