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

import com.openwp3x.EntryImpl;
import com.openwp3x.EntryPattern;
import com.openwp3x.EntryPatternFactory;
import com.openwp3x.EntryReader;

/**
 * @author marcos.ferreira
 * 
 */
public class TestOsnyMartinsEntries {

    final URL resource = this.getClass().getClassLoader().getResource("osny-martins-list.htm");
    EntryReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getNDJoinvillePattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new EntryReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<EntryImpl> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("24-04-2013 CONFIRMADA 10. FEIJOADA DO “BREAKFAST” PARA DUAS ENTIDADES", firstEntry.getTitle());
        Assert.assertEquals("24 Apr 2013", firstEntry.getDate());
        Assert.assertEquals("http://osnymartins.com.br/geral/24-04-2013-confirmada-10-feijoada-do-breakfast-para-duas-entidades/", firstEntry.getUrl());
        Assert.assertEquals("http://osnymartins.com.br/geral/24-04-2013-confirmada-10-feijoada-do-breakfast-para-duas-entidades/", firstEntry.getFormattedURL());
        Assert.assertEquals(1366772400000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
}
