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
public class TestPortalJoinvilleEntries {

    final URL resource = this.getClass().getClassLoader().getResource("portal-jlle-list.htm");
    Reader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getNDJoinvillePattern();
    	entryPattern.setSourceURL(resource);
        this.newsReader = new ReaderImpl(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<EntryImpl> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("Arturzinho fala sobre a sua manutenção no time", firstEntry.getTitle());
        Assert.assertEquals("23 Apr 2013", firstEntry.getDate());
        Assert.assertEquals("http://www.portaljoinville.com.br/v4/esportes/2013/04/arturzinho-fala-sobre-a-sua-manutencao-no-time", firstEntry.getUrl());
        Assert.assertEquals("http://www.portaljoinville.com.br/v4/esportes/2013/04/arturzinho-fala-sobre-a-sua-manutencao-no-time", firstEntry.getFormattedURL());
        Assert.assertEquals(1366686000000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
}
