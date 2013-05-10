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
import com.openwp3x.Reader;
import com.openwp3x.Reader;

/**
 * @author marcos.ferreira
 * 
 */
public class TestACIJEntries {

    final URL resource = this.getClass().getClassLoader().getResource("acij-list.htm");
    EntryReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getAcijPattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new EntryReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<EntryImpl> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("Revista 21", firstEntry.getTitle());
        Assert.assertEquals(null, firstEntry.getDate());
        Assert.assertEquals("/noticias/show/id/901%26", firstEntry.getUrl());
        Assert.assertEquals("http://www.acij.com.br/noticias/show/id/901%26", firstEntry.getFormattedURL());
        Assert.assertEquals(4, entries.size());
    }
}
