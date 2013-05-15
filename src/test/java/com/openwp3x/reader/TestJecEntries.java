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
public class TestJecEntries {

    final URL resource = this.getClass().getClassLoader().getResource("jec-list.htm");
    SourceReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	SourcePattern entryPattern = SourcePatternFactory.getJecPattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new SourceReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<SourceEntry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Citadino: Jec Vence O Santos", firstEntry.getFormattedTitle());
        Assert.assertEquals(null, firstEntry.getDate());
        Assert.assertEquals("http://jec.com.br/citadino-jec-vence-o-santos/", firstEntry.getUrl());
        Assert.assertEquals("http://jec.com.br/citadino-jec-vence-o-santos/", firstEntry.getFormattedURL());
        Assert.assertEquals(10, entries.size());
    }
}
