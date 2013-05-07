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
public class TestJecEntries {

    final URL resource = this.getClass().getClassLoader().getResource("jec-list.htm");
    Reader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getJecPattern();
    	entryPattern.setSourceURL(resource);
        this.newsReader = new ReaderImpl(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<EntryImpl> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("Citadino: JEC vence o Santos", firstEntry.getFormattedTitle());
        Assert.assertEquals(null, firstEntry.getDate());
        Assert.assertEquals("http://jec.com.br/citadino-jec-vence-o-santos/", firstEntry.getUrl());
        Assert.assertEquals("http://jec.com.br/citadino-jec-vence-o-santos/", firstEntry.getFormattedURL());
        Assert.assertEquals(10, entries.size());
    }
}
