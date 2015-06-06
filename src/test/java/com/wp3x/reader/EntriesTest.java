package com.wp3x.reader;

import static com.wp3x.reader.Tests.getURL;
import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.wp3x.content.Entry;
import com.wp3x.pattern.EntryPattern;
import com.wp3x.pattern.SourcePattern;

public class EntriesTest {

	final URL resource = getURL( "prefeitura-list.htm" );
	EntryPattern entry;
	SourcePattern source;

	@Before
	public void before() throws MalformedURLException {
		source = SourcePattern.builder()
				.sourceURL( new URL( "http://www.joinville.sc.gov.br/noticia/index" ) )
				.charset( "UTF-8" )
				.sourceURL( resource )
				.source( "prefeitura" )
				.sourceLabel( "Prefeitura de Joinville" )
				.build();

		entry = new EntryPattern();
		entry.setDate( "//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/span[1]" );
		entry.setTitle( "//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]//h3" );
		entry.setUrl( "//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/a/@href" );
		entry.setDateTextPattern( "\\d{2}\\s\\|\\s\\w{3}\\s\\|\\s\\d{4}" );
		entry.setUrlResource( "http://www.joinville.sc.gov.br" );
		entry.setDateFormat( "dd | MMM | yyyy" );

		entry.setLinkText( "//*[@id=\"main\"]/div/div/div[1]/p[1]" );
		entry.setImg( "//*/meta[starts-with(@property, 'og:image')]/@content" );

	}

	@Test
	public void testGetLinks() throws Exception {
		Reader reader = new Reader( source.getSourceURL(),
				source.getSource(), source.getSourceType(),
				source.getCharset() );

		EntryReader newsReader = new EntryReader( entry, reader );
		final Collection<Entry> entries = newsReader.getEntries();
		final Entry firstEntry = entries.iterator().next();
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
		assertEquals( 10, entries.size() );
	}

}
