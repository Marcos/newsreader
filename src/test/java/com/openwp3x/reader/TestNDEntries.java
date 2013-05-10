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
import com.openwp3x.Reader;
import com.openwp3x.ReaderImpl;

/**
 * @author marcos.ferreira
 * 
 */
public class TestNDEntries {

    final URL resource = this.getClass().getClassLoader().getResource("nd-joinville-list.htm");
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
        Assert.assertEquals("O arauto da Tapera", firstEntry.getTitle());
        Assert.assertEquals("22 Apr 2013", firstEntry.getDate());
        Assert.assertEquals("http://www.ndonline.com.br/joinville/noticias/65628-o-arauto-da-tapera.html", firstEntry.getUrl());
        Assert.assertEquals("http://www.ndonline.com.br/joinville/noticias/65628-o-arauto-da-tapera.html", firstEntry.getFormattedURL());
        Assert.assertEquals(1366599600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
}
