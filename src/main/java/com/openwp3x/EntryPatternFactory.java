package com.openwp3x;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

public class EntryPatternFactory {
	
	static Collection<EntryPattern> entryPatternList;
	
	public static Collection<EntryPattern> getEntryPatternList(){
		if(entryPatternList==null){
			entryPatternList = new ArrayList<EntryPattern>();
			try {
				entryPatternList.add(EntryPatternFactory.getPrefeituraPattern());
				entryPatternList.add(EntryPatternFactory.getSociescPattern());
				entryPatternList.add(EntryPatternFactory.getUdescPattern());
				entryPatternList.add(EntryPatternFactory.getUnivillePattern());
				entryPatternList.add(EntryPatternFactory.getAcijPattern());
				entryPatternList.add(EntryPatternFactory.getAjorpemePattern());
				entryPatternList.add(EntryPatternFactory.getCDLPattern());
				entryPatternList.add(EntryPatternFactory.getDefesaCivilPattern());
				entryPatternList.add(EntryPatternFactory.getCVJoinvillePattern());
				//entryPatternList.add(EntryPatternFactory.getAnoticiaPattern());
				entryPatternList.add(EntryPatternFactory.getNDJoinvillePattern());
				entryPatternList.add(EntryPatternFactory.getPortalJoinvillePattern());
				entryPatternList.add(EntryPatternFactory.getJecPattern());
				entryPatternList.add(EntryPatternFactory.getKronaPattern());
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
		return entryPatternList;
	}

	public static EntryPattern getSociescPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
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
		return entryPattern;
	}

	public static EntryPattern getUdescPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
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
		entryPattern.setLinkTextXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[1]");

		entryPattern.setLinkTextPrefixPattern("\\n+.+\\n+.+\\n+");
		entryPattern.setLinkTextSufixPattern("Compartilhar: ");
		
		return entryPattern;
	}

	public static EntryPattern getUnivillePattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
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
		return entryPattern;
	}

	public static EntryPattern getPrefeituraPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
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
	public static EntryPattern getAjorpemePattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
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
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static EntryPattern getAcijPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
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
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static EntryPattern getCDLPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
		entryPattern.setSourceURL(new URL("http://cdljoinville.com.br/index.php?cat=noticias"));
		entryPattern.setCharset("ISO-8859-1");
		entryPattern.setDateXPath("/html/body/center/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/p[{_counter}]/i");
		entryPattern.setTitleXPath("/html/body/center/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/p[{_counter}]/b");
		entryPattern.setUrlXPath("/html/body/center/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/p[{_counter}]/nobr/a/@href");
		entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
		entryPattern.setMinResult(2);
		entryPattern.setMaxResult(9);
		entryPattern.setSource("cdl_joinville");
		entryPattern.setUrlResource("http://cdljoinville.com.br/");
		entryPattern.setSourceLabel("CDL Joinville");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.negocios}));
		return entryPattern;
	}

	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	public static EntryPattern getDefesaCivilPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
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

	public static EntryPattern getNDJoinvillePattern() throws MalformedURLException {
		final EntryPattern entryPattern = getRSSEntryPattern();
		entryPattern.setSourceURL(new URL("http://feeds.feedburner.com/ndjoinville_noticias"));
		entryPattern.setSourceLabel("Notícias do dia Joinville");
		entryPattern.setSource("nd_joinville");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.geral}));
		return entryPattern;
	}
	
	public static EntryPattern getAnoticiaPattern() throws MalformedURLException {
		final EntryPattern entryPattern = getRSSEntryPattern();
		entryPattern.setSourceURL(new URL("http://anoticia.clicrbs.com.br/sc/geral/ultimas-noticias-rss/"));
		entryPattern.setSourceLabel("ANotícia");
		entryPattern.setSource("anoticia");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.geral}));
		return entryPattern;
	}
	
	public static EntryPattern getPortalJoinvillePattern() throws MalformedURLException {
		final EntryPattern entryPattern = getRSSEntryPattern();
		entryPattern.setSourceURL(new URL("http://www.portaljoinville.com.br/v4/rss/noticias"));
		entryPattern.setSourceLabel("Portal Joinville");
		entryPattern.setSource("portal_joinville");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.geral}));
		return entryPattern;
	}
	
	public static EntryPattern getOsnyMartinsPattern() throws MalformedURLException {
		final EntryPattern entryPattern = getRSSEntryPattern();
		entryPattern.setSourceURL(new URL("http://osnymartins.com.br/feed/"));
		entryPattern.setSourceLabel("Osny Martins");
		entryPattern.setSource("osny_martins");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.geral}));
		return entryPattern;
	}

	private static EntryPattern getRSSEntryPattern() {
		EntryPattern entryPattern = new EntryPattern();
		entryPattern.setCharset("UTF-8");
		entryPattern.setTitleXPath("/rss/channel/item[{_counter}]/title");	
		entryPattern.setDateXPath("/rss/channel/item[{_counter}]/pubDate");
		entryPattern.setUrlXPath("/rss/channel/item[{_counter}]/link");
		entryPattern.setDateTextPattern("\\d{2}\\s\\w{3}\\s\\d{4}");
		entryPattern.setDateFormat("dd MMM yyyy");
		entryPattern.setSourceType(SourceType.RSS);
		entryPattern.setLocale(Locale.US);
		return entryPattern;
	}

	public static EntryPattern getCVJoinvillePattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
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

	public static EntryPattern getJecPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
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

	public static EntryPattern getKronaPattern() throws MalformedURLException {
		final EntryPattern entryPattern = new EntryPattern();
		entryPattern.setSourceURL(new URL("http://www.krona.com.br/kronafutsal/?area=noticias&lng=pt"));
		entryPattern.setCharset("ISO-8859-1");
		entryPattern.setDateXPath("//*[@id=\"div_list_noticias\"]/ul/li[{_counter}]/p[1]");
		entryPattern.setTitleXPath("//*[@id=\"div_list_noticias\"]/ul/li[{_counter}]/h6/a");
		entryPattern.setUrlXPath("//*[@id=\"div_list_noticias\"]/ul/li[{_counter}]/a[2]/@href");
		entryPattern.setDateTextPattern("\\d{2}\\s\\w{3}\\s\\d{2}");
		entryPattern.setMaxResult(10);
		entryPattern.setSource("krona");
		entryPattern.setUrlResource("http://www.krona.com.br/kronafutsal/");
		entryPattern.setDateFormat("dd MMM yy");
		entryPattern.setSourceLabel("Krona Futsal");
		entryPattern.setTags(Arrays.asList(new Tag[]{Tag.esportes}));
		return entryPattern;
	}

}
