package com.openwp3x;

import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public interface Reader {

	public abstract String getTitle(final String xpathPattern) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException;

	public abstract String getTextContent(final String xpathPattern) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException;

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
	public abstract String getDateContent(final String xpathPattern, final String datePatern) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException;

	/**
	 * @param text
	 * @param datePatern
	 */
	public abstract String getDateFromText(final String text, final String datePatern);

	/**
	 * @param resource
	 * @param string
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws SAXException
	 */
	public abstract Collection<EntryImpl> getEntries() throws IOException, ParserConfigurationException, XPathExpressionException, SAXException;

	public abstract String clearText(String dirtText, String startPattern, String endPattern);

}