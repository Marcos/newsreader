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
public class TestUdescEntries {

    final URL resource = this.getClass().getClassLoader().getResource("udesc-list.htm");
    Reader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getUdescPattern();
    	entryPattern.setSourceURL(resource);
        this.newsReader = new ReaderImpl(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<EntryImpl> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("Termina neste domingo prazo para submissão de trabalhos no Encontro Catarinense de LibreOffice", firstEntry.getFormattedTitle());
        Assert.assertEquals("17/04/2013", firstEntry.getDate());
        Assert.assertEquals("index.php?id=5160&pg=1", firstEntry.getUrl());
        Assert.assertEquals("http://www.joinville.udesc.br/portal/noticias/index.php?id=5160&pg=1", firstEntry.getFormattedURL());
        Assert.assertEquals(1366167600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(20, entries.size());
    }
}
