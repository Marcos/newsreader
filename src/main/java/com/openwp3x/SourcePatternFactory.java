package com.openwp3x;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import com.openwp3x.reader.SourceType;

public class SourcePatternFactory {
	
	static Collection<SourcePattern> entryPatternList;
	
	public static Collection<SourcePattern> getEntryPatternList(){
		if(entryPatternList==null){
			entryPatternList = new ArrayList<SourcePattern>();
			try {
				entryPatternList.add(SourcePatternFactory.getPrefeituraPattern());
				entryPatternList.add(SourcePatternFactory.getSociescPattern());
				entryPatternList.add(SourcePatternFactory.getUdescPattern());
				entryPatternList.add(SourcePatternFactory.getUnivillePattern());
				entryPatternList.add(SourcePatternFactory.getAcijPattern());
				entryPatternList.add(SourcePatternFactory.getAjorpemePattern());
				entryPatternList.add(SourcePatternFactory.getCDLPattern());
				entryPatternList.add(SourcePatternFactory.getDefesaCivilPattern());
				entryPatternList.add(SourcePatternFactory.getCVJoinvillePattern());
				//entryPatternList.add(EntryPatternFactory.getAnoticiaPattern());
				entryPatternList.add(SourcePatternFactory.getNDJoinvillePattern());
				entryPatternList.add(SourcePatternFactory.getPortalJoinvillePattern());
				entryPatternList.add(SourcePatternFactory.getJecPattern());
				entryPatternList.add(SourcePatternFactory.getKronaPattern());
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
		return entryPatternList;
	}

	public static SourcePattern getSociescPattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://www.sociesc.org.br/pt/noticias/"));
		entryPattern.setCharset("ISO-8859-1");
		entryPattern.setDateXPath("//*[@id=\"listBusca\"]/ul/li[{_counter}]/a/span[1]");
		entryPattern.setTitleXPath("//*[@id=\"listBusca\"]/ul/li[{_counter}]/a/span[2]");
		entryPattern.setUrlXPath("//*[@id=\"listBusca\"]/ul/li[{_counter}]/a/@onclick");
		entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
		entryPattern.setSource("sociesc");
		entryPattern.setUrlResource("http://www.sociesc.org.br/pt/noticias/ajax_news_view.php?id=");
		entryPattern.setUrlPattern("\\d+");
		entryPattern.setSourceLabel("SOCIESC");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.educacao}));
		
		entryPattern.setLinkTextXPath("//*[@id=\"dinamicTxt\"]");
		
		
		
		return entryPattern;
	}

	public static SourcePattern getUdescPattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://www.joinville.udesc.br/portal/noticias/index.php"));
		entryPattern.setCharset("ISO-8859-1");
		entryPattern.setDateXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[2]/table/tbody/tr[{_counter}]/td[1]");
		entryPattern.setTitleXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[2]/table/tbody/tr[{_counter}]/td[2]/a");
		entryPattern.setUrlXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[2]/table/tbody/tr[{_counter}]/td[2]/a//@href");
		entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
		entryPattern.setSource("udesc");
		entryPattern.setUrlResource("http://www.joinville.udesc.br/portal/noticias/");
		entryPattern.setMaxResult(20);
		entryPattern.setSourceLabel("UDESC");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.educacao}));
		
		entryPattern.setLinkTextXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[1]/p[4]");

		return entryPattern;
	}

	public static SourcePattern getUnivillePattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://www.univille.edu.br/noticias/index/33793?all=1"));
		entryPattern.setCharset("ISO-8859-1");
		entryPattern.setDateXPath("//*[@id=\"columnCenter\"]/div[6]/ul/li[{_counter}]/p");
		entryPattern.setTitleXPath("//*[@id=\"columnCenter\"]/div[6]/ul/li[{_counter}]/a");
		entryPattern.setUrlXPath("//*[@id=\"columnCenter\"]/div[6]/ul/li[{_counter}]/a/@href");
		entryPattern.setDateTextPattern("\\d{2}\\.\\d{2}\\.\\d{4}");
		entryPattern.setSource("univille");
		entryPattern.setDateFormat("dd.MM.yyyy");
		entryPattern.setSourceLabel("Univille");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.educacao}));
		
		entryPattern.setLinkTextXPath("//*[@id=\"pageContent\"]");
		return entryPattern;
	}

	public static SourcePattern getPrefeituraPattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://www.joinville.sc.gov.br/noticia/index"));
		entryPattern.setCharset("UTF-8");
		entryPattern.setDateXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/span[1]");
		entryPattern.setTitleXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]//h3");
		entryPattern.setUrlXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/a/@href");
		entryPattern.setDateTextPattern("\\d{2}\\s\\|\\s\\w{3}\\s\\|\\s\\d{4}");
		entryPattern.setSource("prefeitura");
		entryPattern.setUrlResource("http://www.joinville.sc.gov.br");
		entryPattern.setDateFormat("dd | MMM | yyyy");
		entryPattern.setSourceLabel("Prefeitura de Joinville");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.comunidade}));
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static SourcePattern getAjorpemePattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://ajorpeme.com.br/site/noticias"));
		entryPattern.setCharset("UTF-8");
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
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.negocios}));
		
		entryPattern.setLinkTextXPath("//*[@id=\"content\"]/table[2]/tbody/tr/td");
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static SourcePattern getAcijPattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://acij.com.br/noticias"));
		entryPattern.setCharset("UTF-8");
		entryPattern.setDateXPath("/html/body/div/div[2]/div[1]/div[2]/ul/li[{_counter}]/span/a");
		entryPattern.setTitleXPath("/html/body/div/div[2]/div[1]/div[2]/ul/li[{_counter}]/span/a");
		entryPattern.setUrlXPath("/html/body/div/div[2]/div[1]/div[2]/ul/li[{_counter}]/span/a/@href");
		entryPattern.setMaxResult(4);
		entryPattern.setSource("acij");
		entryPattern.setUrlResource("http://www.acij.com.br");
		entryPattern.setSourceLabel("ACIJ");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.negocios}));
		
		entryPattern.setLinkTextXPath("/html/body/div/div[2]/div[1]/div[1]");
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static SourcePattern getCDLPattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://www.cdljoinville.com.br/noticias"));
		entryPattern.setDateXPath("/html/body/div[6]/div[1]/div/div[2]/div[{_counter}]/div[1]");
		entryPattern.setTitleXPath("/html/body/div[6]/div[1]/div/div[2]/div[{_counter}]/div[2]");
		entryPattern.setUrlXPath("/html/body/div[6]/div[1]/div/div[2]/div[{_counter}]/a/@href");
		//entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
		entryPattern.setMinResult(2);
		entryPattern.setMaxResult(5);
		entryPattern.setSource("cdl_joinville");
		entryPattern.setUrlResource("http://www.cdljoinville.com.br/");
		entryPattern.setSourceLabel("CDL Joinville");
		entryPattern.setTitlePrefixPattern("\\n*");
		entryPattern.setTitleSufixPattern("\\n");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.negocios}));
		
		entryPattern.setLinkTextXPath("/html/body/div[6]/div[1]/div/div[2]/div");
		
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static SourcePattern getDefesaCivilPattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://defesacivil.joinville.sc.gov.br/noticia"));
		entryPattern.setCharset("UTF-8");
		entryPattern.setDateXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/span[1]");
		entryPattern.setTitleXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]//h3");
		entryPattern.setUrlXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/a/@href");
		entryPattern.setUrlResource("http://defesacivil.joinville.sc.gov.br");
		entryPattern.setDateTextPattern("\\d{2}\\s\\|\\s\\w{3}\\s\\|\\s\\d{4}");
		entryPattern.setSource("defesa_civil");
		entryPattern.setDateFormat("dd | MMM | yyyy");
		entryPattern.setSourceLabel("Defesa Civil de Joinville");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.comunidade}));
		return entryPattern;
	}

	public static SourcePattern getNDJoinvillePattern() throws MalformedURLException {
		final SourcePattern entryPattern = getRSSEntryPattern();
		entryPattern.setSourceURL(new URL("http://feeds.feedburner.com/ndjoinville_noticias"));
		entryPattern.setSourceLabel("Notícias do dia Joinville");
		entryPattern.setSource("nd_joinville");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.geral}));
		return entryPattern;
	}
	
	public static SourcePattern getAnoticiaPattern() throws MalformedURLException {
		final SourcePattern entryPattern = getRSSEntryPattern();
		entryPattern.setSourceURL(new URL("http://anoticia.clicrbs.com.br/sc/geral/ultimas-noticias-rss/"));
		entryPattern.setSourceLabel("ANotícia");
		entryPattern.setSource("anoticia");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.geral}));
		return entryPattern;
	}
	
	public static SourcePattern getPortalJoinvillePattern() throws MalformedURLException {
		final SourcePattern entryPattern = getRSSEntryPattern();
		entryPattern.setSourceURL(new URL("http://www.portaljoinville.com.br/v4/rss/noticias"));
		entryPattern.setSourceLabel("Portal Joinville");
		entryPattern.setSource("portal_joinville");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.geral}));
		return entryPattern;
	}
	
	public static SourcePattern getOsnyMartinsPattern() throws MalformedURLException {
		final SourcePattern entryPattern = getRSSEntryPattern();
		entryPattern.setSourceURL(new URL("http://osnymartins.com.br/feed/"));
		entryPattern.setSourceLabel("Osny Martins");
		entryPattern.setSource("osny_martins");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.geral}));
		return entryPattern;
	}

	private static SourcePattern getRSSEntryPattern() {
		SourcePattern entryPattern = new SourcePattern();
		entryPattern.setCharset("UTF-8");
		entryPattern.setTitleXPath("/rss/channel/item[{_counter}]/title");	
		entryPattern.setDateXPath("/rss/channel/item[{_counter}]/pubDate");
		entryPattern.setUrlXPath("/rss/channel/item[{_counter}]/link");
		entryPattern.setTextXPath("/rss/channel/item[{_counter}]/description");
		entryPattern.setDateTextPattern("\\d{2}\\s\\w{3}\\s\\d{4}");
		entryPattern.setDateFormat("dd MMM yyyy");
		entryPattern.setSourceType(SourceType.RSS);
		entryPattern.setLocale(Locale.US);
		return entryPattern;
	}

	public static SourcePattern getCVJoinvillePattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://www.cvj.sc.gov.br"));
		entryPattern.setCharset("UTF-8");
		entryPattern.setDateXPath("//*[@id=\"system\"]/div[1]/div[1]/article/header/time/@datetime");
		entryPattern.setTitleXPath("//*[@id=\"system\"]/div[1]/div[1]/article/header/h1/a");
		entryPattern.setUrlXPath("//*[@id=\"system\"]/div[1]/div[1]/article/header/h1/a/@href");
		entryPattern.setDateTextPattern("\\d{4}\\-\\d{2}\\-\\d{2}");
		entryPattern.setMaxResult(9);
		entryPattern.setSource("cvj");
		entryPattern.setUrlResource("http://www.cvj.sc.gov.br");
		entryPattern.setDateFormat("yyyy-MM-dd");
		entryPattern.setSourceLabel("Câmara de Vereadores de Joinville");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.comunidade}));
		return entryPattern;
	}

	public static SourcePattern getJecPattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://jec.com.br/noticias/"));
		entryPattern.setCharset("UTF-8");
		entryPattern.setTitleXPath("//*[@id=\"noticias\"]/div[{_counter}]/b/a");
		entryPattern.setUrlXPath("//*[@id=\"noticias\"]/div[{_counter}]/b/a/@href");
		entryPattern.setDateTextPattern("\\d{4}\\-\\d{2}\\-\\d{2}");
		entryPattern.setMaxResult(10);
		entryPattern.setSource("jec");
		//entryPattern.setUrlResource("http://jec.com.br");
		entryPattern.setDateFormat("yyyy-MM-dd");
		entryPattern.setSourceLabel("JEC");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.esportes}));
		return entryPattern;
	}

	public static SourcePattern getKronaPattern() throws MalformedURLException {
		final SourcePattern entryPattern = new SourcePattern();
		entryPattern.setSourceURL(new URL("http://www.krona.com.br/kronafutsal/?area=noticias&lng=pt"));
		entryPattern.setCharset("ISO-8859-1");
		entryPattern.setDateXPath("//*[@id=\"div_list_noticias\"]/ul/li[{_counter}]/p[1]");
		entryPattern.setTitleXPath("//*[@id=\"div_list_noticias\"]/ul/li[{_counter}]/h6/a");
		entryPattern.setUrlXPath("//*[@id=\"div_list_noticias\"]/ul/li[{_counter}]/a[2]/@href");
		entryPattern.setDateTextPattern("\\d{2}\\s\\w{3}\\s\\d{2}");
		entryPattern.setMaxResult(10);
		entryPattern.setSource("krona");
		entryPattern.setUrlResource("http://www.krona.com.br/kronafutsal/");
		entryPattern.setDateFormat("d MMM yy");
		entryPattern.setSourceLabel("Krona Futsal");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.esportes}));
		return entryPattern;
	}

}
