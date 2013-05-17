/**
 * 
 */
package com.openwp3x.reader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * @author marcos.ferreira
 * 
 */
public class Reader {
	private static final String DEFAULT_CHARSET = "UTF-8";
	private org.w3c.dom.Document doc;
	Logger log = Logger.getLogger(this.getClass());

	public Reader(URL sourceURL, String sourceName, SourceType sourceType, String charset)  {
		try {
			final InputStream inputStream = getStream(sourceURL);
			this.doc = getContent(inputStream, sourceType, charset);
	
			if (log.isDebugEnabled()) {
				writeFile(sourceName + ".xml");
			}
		} catch (Exception e) {
			String message = "Error parsing content from " + sourceName;
			log.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	public void writeFile(String fileDest) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(this.doc);
			File file = new File("target"+File.separator+fileDest);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
			log.info("Created debug file at " + file.getAbsolutePath());
		} catch (Exception e) {
			log.error(e);
		}
	}

	public Document getContent(InputStream inputStream, SourceType sourceType, String charset) throws IOException, ParserConfigurationException, SAXException {
		if (sourceType == null || sourceType.equals(SourceType.HTML)) {
			return getXMLContent(inputStream, charset);
		}
		return getXMLContent(inputStream);
	}

	private Document getXMLContent(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		org.w3c.dom.Document doc = dBuilder.parse(inputStream);
		doc.getDocumentElement().normalize();
		return doc;
	}

	private Document getXMLContent(InputStream inputStream, String charset) throws IOException, ParserConfigurationException {
		final TagNode tagNode = new HtmlCleaner().clean(inputStream, charset);
		CleanerProperties cleanerProperties = new CleanerProperties();
		org.w3c.dom.Document doc = new DomSerializer(cleanerProperties).createDOM(tagNode);
		return doc;
	}

	public String getFormattedTitle(final String xpathPattern) throws ReaderException {
		return this.getTextContent(xpathPattern).trim();
	}

	public String getTextContent(final String xpathPattern) throws ReaderException{
		try {
			final XPath xpath = XPathFactory.newInstance().newXPath();
			final String str = (String) xpath.evaluate(xpathPattern, this.doc, XPathConstants.STRING);
			
			final String standart = this.getStringAsStandart(str, Reader.DEFAULT_CHARSET);
			return standart;
		} catch (Exception e) {
			throw new ReaderException(e);
		}
	}
	
	public String getHTMLContent(final String xpathPattern) throws ReaderException{
		try {			
			final XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xpath.evaluate(xpathPattern, this.doc, XPathConstants.NODESET);
			
			String content = getNodeListAsString(nodeList);
			return content;
		} catch (Exception e) {
			throw new ReaderException(e);
		}
	}

	private String getNodeListAsString(NodeList nodeList) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		for(int i=0; i<nodeList.getLength(); i++){
			Node node = doc.importNode(nodeList.item(i), true);
			doc.appendChild(node);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(byteArrayOutputStream);
		transformer.transform(source, result);
		byte[] bytes = byteArrayOutputStream.toByteArray();
		final String content = new String(bytes, Reader.DEFAULT_CHARSET);
		return content;
	}

	private String getStringAsStandart(final String str, final String charset) throws UnsupportedEncodingException {
		return StringEscapeUtils.unescapeHtml4(str);
	}

	public InputStream getStream(final URL url) throws IOException {
		URLConnection connection = url.openConnection();
		if (connection instanceof HttpURLConnection) {
			((HttpURLConnection) connection).setInstanceFollowRedirects(false);
			((HttpURLConnection) connection).setRequestMethod("GET");
		}
		connection.setDoOutput(true);
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
		connection.connect();
		return connection.getInputStream();
	}

	public String getDateContent(final String xpathPattern, final String datePatern) throws ReaderException{
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

	public String clearText(String dirtText, String startPattern, String endPattern) {
		if(StringUtils.isNotBlank(dirtText)){
			Integer start = 0;
			Integer end  = dirtText.length();
			
			if(startPattern!=null){
				Pattern patternStart = Pattern.compile(startPattern);
				Matcher matcherStart = patternStart.matcher(dirtText);
				if(matcherStart.find()){					
					start = matcherStart.end();
				}
			}
			
			if(endPattern!=null){			
				Pattern patternEnd = Pattern.compile(endPattern);
				Matcher matcherEnd = patternEnd.matcher(dirtText);
				if(matcherEnd.find()){
					end = matcherEnd.start();
				}
			}
			
			return dirtText.substring(start, end);	
		}
		return null;
	}
}
