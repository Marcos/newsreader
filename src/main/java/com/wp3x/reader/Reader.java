/**
 * 
 */
package com.wp3x.reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Reader {
	private static final String DEFAULT_CHARSET = "UTF-8";

	private org.w3c.dom.Document doc;

	public Reader(URL sourceURL, String sourceName, SourceType sourceType,
			String charset) throws ReaderException, IOException {
		final InputStream inputStream = getStream(sourceURL);
		this.doc = getContent(inputStream, sourceType, charset);

	}

	public void writeFile(String fileDest) throws ReaderException {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		DOMSource source = new DOMSource(this.doc);
		File file = new File("target" + File.separator + fileDest);
		StreamResult result = new StreamResult(file);
		transform(transformerFactory, source, result);

	}

	private void transform(TransformerFactory transformerFactory,
			DOMSource source, StreamResult result) throws ReaderException {
		try {
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw new ReaderException(e);
		}
	}

	public Document getContent(InputStream inputStream, SourceType sourceType,
			String charset) throws ReaderException {
		if (sourceType == null || sourceType.equals(SourceType.HTML)) {
			return getHTMLContent(inputStream, charset);
		} else {
			return getXMLContent(inputStream);
		}
	}

	private Document getXMLContent(InputStream inputStream)
			throws ReaderException {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(inputStream);
			doc.getDocumentElement().normalize();
			return doc;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new ReaderException(e);
		}
	}

	private Document getHTMLContent(InputStream inputStream, String charset)
			throws ReaderException {
		try {
			final TagNode tagNode = new HtmlCleaner().clean(inputStream,
					charset);
			CleanerProperties cleanerProperties = new CleanerProperties();
			return new DomSerializer(cleanerProperties).createDOM(tagNode);
		} catch (ParserConfigurationException | IOException e) {
			throw new ReaderException(e);
		}
	}

	public String getTextContent(final String xpathPattern)
			throws ReaderException {
		if (xpathPattern == null)
			return null;
		try {
			final XPath xpath = XPathFactory.newInstance().newXPath();
			final String str = (String) xpath.evaluate(xpathPattern, this.doc,
					XPathConstants.STRING);

			final String standart = this.getStringAsStandart(str,
					DEFAULT_CHARSET);
			return standart;
		} catch (Exception e) {
			throw new ReaderException(e);
		}
	}

	public String getHTML(final String xpathPattern) throws ReaderException {
		try {
			final XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xpath.evaluate(xpathPattern,
					this.doc, XPathConstants.NODESET);
			String content = getStringAsStandart(getNodeListAsString(nodeList),
					DEFAULT_CHARSET);

			return content;
		} catch (Exception e) {
			throw new ReaderException(e);
		}
	}

	private String getNodeListAsString(NodeList nodeList) throws Exception {
		StringWriter sw = new StringWriter();
		Transformer serializer = TransformerFactory.newInstance()
				.newTransformer();
		serializer.transform(new DOMSource(nodeList.item(0)), new StreamResult(
				sw));
		String result = sw.toString();
		return result;
	}

	private String getStringAsStandart(final String str, final String charset)
			throws UnsupportedEncodingException {
		return StringEscapeUtils.unescapeHtml4(str);
	}

	public InputStream getStream(final URL url) throws IOException {
		URLConnection connection = url.openConnection();
		if (connection instanceof HttpURLConnection) {
			((HttpURLConnection) connection).setInstanceFollowRedirects(false);
			((HttpURLConnection) connection).setRequestMethod("GET");
		}
		connection.setDoOutput(true);
		connection
				.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
		connection.connect();
		return connection.getInputStream();
	}

	public String getDateContent(final String xpathPattern,
			final String datePatern) throws ReaderException {
		if (xpathPattern != null && datePatern != null) {
			final String content = this.getTextContent(xpathPattern);
			return this.getDateFromText(content, datePatern);
		}
		return null;
	}

	public String getDateFromText(final String text, final String datePatern) {
		final Pattern pattern = Pattern.compile(datePatern);
		final Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			final String dateText = matcher.group();
			return dateText;
		}
		return null;
	}

	public String clearText(String dirtText, String startPattern,
			String endPattern) {
		if (StringUtils.isNotBlank(dirtText)) {
			Integer start = 0;
			Integer end = dirtText.length();

			if (startPattern != null) {
				Pattern patternStart = Pattern.compile(startPattern);
				Matcher matcherStart = patternStart.matcher(dirtText);
				if (matcherStart.find()) {
					start = matcherStart.end();
				}
			}

			if (endPattern != null) {
				Pattern patternEnd = Pattern.compile(endPattern);
				Matcher matcherEnd = patternEnd.matcher(dirtText);
				if (matcherEnd.find()) {
					end = matcherEnd.start();
				}
			}

			return dirtText.substring(start, end);
		}
		return null;
	}
}
