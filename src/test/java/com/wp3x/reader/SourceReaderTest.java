package com.wp3x.reader;

import static com.wp3x.reader.Tests.getURL;
import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.wp3x.Source;

public class SourceReaderTest {

	final URL resource = getURL( "prefeitura-list.htm" );
	Source entryPattern;

	@Before
	public void before() throws MalformedURLException {
		entryPattern = new Source();
		entryPattern.setSourceURL( new URL( "http://www.joinville.sc.gov.br/noticia/index" ) );
		entryPattern.setCharset( "UTF-8" );
		entryPattern.setDate( "//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/span[1]" );
		entryPattern.setTitle( "//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]//h3" );
		entryPattern.setUrl( "//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/a/@href" );
		entryPattern.setDateTextPattern( "\\d{2}\\s\\|\\s\\w{3}\\s\\|\\s\\d{4}" );
		entryPattern.setSource( "prefeitura" );
		entryPattern.setUrlResource( "http://www.joinville.sc.gov.br" );
		entryPattern.setDateFormat( "dd | MMM | yyyy" );
		entryPattern.setSourceLabel( "Prefeitura de Joinville" );

		entryPattern.setLinkText( "//*[@id=\"main\"]/div/div/div[1]/p[1]" );
		entryPattern.setImg( "//*/meta[starts-with(@property, 'og:image')]/@content" );
		entryPattern.setSourceURL( resource );
	}

	@Test
	public void testGetLinks() throws Exception {
		SourceReader newsReader = new SourceReader( entryPattern );
		final Collection<SourceEntry> entries = newsReader.getEntries();
		final SourceEntry firstEntry = entries.iterator().next();
		assertEquals(
				firstEntry.getTitle(),
				"Fundação 25 de Julho e Secretaria de Desenvolvimento Econômico visitam produtores rurais" );
		assertEquals( firstEntry.getDate(), "17 | ABR | 2013" );
		assertEquals(
				firstEntry.getUrl(),
				"/noticia/4202-Funda%C3%A7%C3%A3o+25+de+Julho+e+Secretaria+de+Desenvolvimento+Econ%C3%B4mico+visitam+produtores+rurais.html" );
		assertEquals(
				firstEntry.getFormattedURL(),
				"http://www.joinville.sc.gov.br/noticia/4202-Funda%C3%A7%C3%A3o+25+de+Julho+e+Secretaria+de+Desenvolvimento+Econ%C3%B4mico+visitam+produtores+rurais.html" );
		assertEquals( firstEntry.getDateAsLong().longValue(), 1366167600000L );
		assertEquals( 10, entries.size() );
	}

}
