package com.openwp3x;

import java.net.MalformedURLException;
import java.net.URL;

public class EntryPatternFactory {

	public static EntryPattern getSociescPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
		entryPattern.setSourceURL(new URL("http://www.sociesc.org.br/pt/noticias/"));
		entryPattern.setSourceCharset("ISO-8859-1");
		entryPattern.setDateXPath("//*[@id=\"listBusca\"]/ul/li[{_counter}]/a/span[1]");
		entryPattern.setTitleXPath("//*[@id=\"listBusca\"]/ul/li[{_counter}]/a/span[2]");
		entryPattern.setUrlXPath("//*[@id=\"listBusca\"]/ul/li[{_counter}]/a/@onclick");
		entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
		entryPattern.setSource("sociesc");
		entryPattern.setUrlResource("http://www.sociesc.org.br/pt/noticias/ajax_news_view.php?id=");
		entryPattern.setUrlPattern("\\d+");
		entryPattern.setSourceLabel("SOCIESC");
		return entryPattern;
	}

	public static EntryPattern getUdescPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
		entryPattern.setSourceURL(new URL("http://www.joinville.udesc.br/portal/noticias/index.php"));
		entryPattern.setSourceCharset("ISO-8859-1");
		entryPattern.setDateXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[2]/table/tbody/tr[{_counter}]/td[1]");
		entryPattern.setTitleXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[2]/table/tbody/tr[{_counter}]/td[2]/a");
		entryPattern.setUrlXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[2]/table/tbody/tr[{_counter}]/td[2]/a//@href");
		entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
		entryPattern.setSource("udesc");
		entryPattern.setUrlResource("http://www.joinville.udesc.br/portal/noticias/");
		entryPattern.setMaxResult(20);
		entryPattern.setSourceLabel("UDESC");
		return entryPattern;
	}

	public static EntryPattern getUnivillePattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
		entryPattern.setSourceURL(new URL("http://www.univille.edu.br/noticias/index/33793?all=1"));
		entryPattern.setSourceCharset("ISO-8859-1");
		entryPattern.setDateXPath("//*[@id=\"columnCenter\"]/div[6]/ul/li[{_counter}]/p");
		entryPattern.setTitleXPath("//*[@id=\"columnCenter\"]/div[6]/ul/li[{_counter}]/a");
		entryPattern.setUrlXPath("//*[@id=\"columnCenter\"]/div[6]/ul/li[{_counter}]/a/@href");
		entryPattern.setDateTextPattern("\\d{2}\\.\\d{2}\\.\\d{4}");
		entryPattern.setSource("univille");
		entryPattern.setDateFormat("dd.MM.yyyy");
		entryPattern.setSourceLabel("Univille");
		return entryPattern;
	}

	public static EntryPattern getPrefeituraPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
		entryPattern.setSourceURL(new URL("http://www.joinville.sc.gov.br/noticia/index"));
		entryPattern.setSourceCharset("UTF-8");
		entryPattern.setDateXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/span[1]");
		entryPattern.setTitleXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]//h3");
		entryPattern.setUrlXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/a/@href");
		entryPattern.setDateTextPattern("\\d{2}\\s\\|\\s\\w{3}\\s\\|\\s\\d{4}");
		entryPattern.setSource("prefeitura");
		entryPattern.setUrlResource("http://www.joinville.sc.gov.br");
		entryPattern.setDateFormat("dd | MMM | yyyy");
		entryPattern.setSourceLabel("Prefeitura de Joinville");
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static EntryPattern getAjorpemePattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
		entryPattern.setSourceURL(new URL("http://ajorpeme.com.br/site/noticias"));
		entryPattern.setSourceCharset("UTF-8");
		entryPattern.setDateXPath("//*[@id=\"content\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table[{_counter}]/tbody/tr/td/a");
		entryPattern.setTitleXPath("//*[@id=\"content\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table[{_counter}]/tbody/tr/td/a");
		entryPattern.setUrlXPath("//*[@id=\"content\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table[{_counter}]/tbody/tr/td/a/@href");
		entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{2}");
		entryPattern.setSource("ajorpeme");
		entryPattern.setUrlResource("http://ajorpeme.com.br");
		entryPattern.setMinResult(1);
		entryPattern.setInterval(2);
		entryPattern.setMaxResult(20);
		entryPattern.setDateFormat("dd/MM/yy");
		entryPattern.setSourceLabel("Ajorpeme");
		entryPattern.setTitlePrefixPattern("\\.*\\s-\\s");
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static EntryPattern getAcijPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
		entryPattern.setSourceURL(new URL("http://acij.com.br/noticias"));
		entryPattern.setSourceCharset("UTF-8");
		entryPattern.setDateXPath("/html/body/div/div[2]/div[1]/div[2]/ul/li[{_counter}]/span/a");
		entryPattern.setTitleXPath("/html/body/div/div[2]/div[1]/div[2]/ul/li[{_counter}]/span/a");
		entryPattern.setUrlXPath("/html/body/div/div[2]/div[1]/div[2]/ul/li[{_counter}]/span/a/@href");
		entryPattern.setMaxResult(4);
		entryPattern.setSource("acij");
		entryPattern.setUrlResource("www.acij.com.br");
		entryPattern.setSourceLabel("ACIJ");
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static EntryPattern getCDLPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
		entryPattern.setSourceURL(new URL("http://cdljoinville.com.br/index.php?cat=noticias"));
		entryPattern.setSourceCharset("ISO-8859-1");
		entryPattern.setDateXPath("/html/body/center/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/p[{_counter}]/i");
		entryPattern.setTitleXPath("/html/body/center/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/p[{_counter}]/b");
		entryPattern.setUrlXPath("/html/body/center/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/p[{_counter}]/nobr/a/@href");
		entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
		entryPattern.setMinResult(2);
		entryPattern.setMaxResult(9);
		entryPattern.setSource("cdl_joinville");
		entryPattern.setUrlResource("http://cdljoinville.com.br/");
		entryPattern.setSourceLabel("CDL Joinville");
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static EntryPattern getDefesaCivilPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
		entryPattern.setSourceURL(new URL("http://defesacivil.joinville.sc.gov.br/noticia"));
		entryPattern.setSourceCharset("UTF-8");
		entryPattern.setDateXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/span[1]");
		entryPattern.setTitleXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]//h3");
		entryPattern.setUrlXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/a/@href");
		entryPattern.setUrlResource("http://defesacivil.joinville.sc.gov.br/");
		entryPattern.setDateTextPattern("\\d{2}\\s\\|\\s\\w{3}\\s\\|\\s\\d{4}");
		entryPattern.setSource("defesa_civil");
		entryPattern.setSourceLabel("Prefeitura de Joinville");
		return entryPattern;
	}

}
