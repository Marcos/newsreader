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
public class TestUnivilleEntries {

    final URL resource = this.getClass().getClassLoader().getResource("univille-list.htm");
    EntryReader newsReader;
    final String charset = "ISO-8859-1";

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getUnivillePattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new EntryReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<EntryImpl> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("Maratona de inglês", firstEntry.getTitle());
        Assert.assertEquals("30.04.2013", firstEntry.getDate());
        Assert.assertEquals("http://www.univille.edu.br/contentId/465612", firstEntry.getUrl());
        Assert.assertEquals("http://www.univille.edu.br/contentId/465612",firstEntry.getFormattedURL());
        Assert.assertEquals(1367290800000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
}
