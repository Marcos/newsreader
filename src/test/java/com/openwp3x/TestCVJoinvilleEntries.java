/**
 * 
 */
package com.openwp3x;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author marcos.ferreira
 * 
 */
public class TestCVJoinvilleEntries {

    final URL resource = this.getClass().getClassLoader().getResource("cv-joinville-list.htm");
    Reader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getCVJoinvillePattern();
    	entryPattern.setSourceURL(resource);
        this.newsReader = new ReaderImpl(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<EntryImpl> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("Legislativo na entrega de ordem de serviço", firstEntry.getTitle());
        Assert.assertEquals("2013-04-24", firstEntry.getDate());
        Assert.assertEquals("/component/content/article/47-noticias/1821-legislativo-na-entrega-de-ordem-de-servico", firstEntry.getUrl());
        Assert.assertEquals("http://www.cvj.sc.gov.br/component/content/article/47-noticias/1821-legislativo-na-entrega-de-ordem-de-servico", firstEntry.getFormattedURL());
        Assert.assertEquals(1366772400000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }

    @Test
    public void testDateFormat() {
        System.out.println(new EntryImpl().getDateAsLong("17 | ABR | 2013", new Locale("pt", "BR"), "dd | MMM | yyyy"));
    }
}
