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
public class TestKronaEntries {

    final URL resource = this.getClass().getClassLoader().getResource("krona-list.htm");
    SourceReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	SourcePattern entryPattern = SourcePatternFactory.getKronaPattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new SourceReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<SourceEntry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Maratona De Quatro Jogos Em Joinville", firstEntry.getFormattedTitle());
        Assert.assertEquals("30 Abr 13", firstEntry.getDate());
        Assert.assertEquals("./?area=noticias&id=1251", firstEntry.getUrl());
        Assert.assertEquals("http://www.krona.com.br/kronafutsal/./?area=noticias&id=1251", firstEntry.getFormattedURL());
        Assert.assertEquals(1367290800000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
    
    
}
