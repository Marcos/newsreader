/**
 * 
 */
package com.openwp3x.reader;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.openwp3x.SourceEntry;
import com.openwp3x.SourcePattern;
import com.openwp3x.SourcePatternFactory;

/**
 * @author marcos.ferreira
 * 
 */
public class TestGazetaEntries {

    final URL resource = this.getClass().getClassLoader().getResource("gazeta-jlle-list.htm");
    SourceReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	SourcePattern entryPattern = SourcePatternFactory.getNDJoinvillePattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new SourceReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<SourceEntry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Empresário de Joinville e mais 3 réus são condenados por homicídio", firstEntry.getTitle());
        Assert.assertEquals("22 Apr 2013", firstEntry.getDate());
        Assert.assertEquals("http://www.gazetadejoinville.com.br/site/arquivos/26893", firstEntry.getUrl());
        Assert.assertEquals("http://www.gazetadejoinville.com.br/site/arquivos/26893", firstEntry.getFormattedURL());
        Assert.assertEquals(1366599600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
}
