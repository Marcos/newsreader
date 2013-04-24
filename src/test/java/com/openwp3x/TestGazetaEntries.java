/**
 * 
 */
package com.openwp3x;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author marcos.ferreira
 * 
 */
public class TestGazetaEntries {

    final URL resource = this.getClass().getClassLoader().getResource("gazeta-jlle-list.htm");
    EntryReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getNDJoinvillePattern();
    	entryPattern.setSourceURL(resource);
        this.newsReader = new EntryReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<Entry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final Entry firstEntry = entries.iterator().next();
        Assert.assertEquals("Empresário de Joinville e mais 3 réus são condenados por homicídio", firstEntry.getTitle());
        Assert.assertEquals("22 Apr 2013", firstEntry.getDate());
        Assert.assertEquals("http://www.gazetadejoinville.com.br/site/arquivos/26893", firstEntry.getUrl());
        Assert.assertEquals("http://www.gazetadejoinville.com.br/site/arquivos/26893", firstEntry.getFormattedURL());
        Assert.assertEquals(1366599600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
}
