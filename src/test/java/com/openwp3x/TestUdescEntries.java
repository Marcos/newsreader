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
public class TestUdescEntries {

    final URL resource = this.getClass().getClassLoader().getResource("udesc-list.htm");
    EntryReader newsReader;
    final String charset = "ISO-8859-1";

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
        this.newsReader = new EntryReader(this.resource, this.charset, EntryPatternFactory.getUdescPattern());
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<Entry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final Entry firstEntry = entries.iterator().next();
        Assert.assertEquals("Termina neste domingo prazo para submissão de trabalhos no Encontro Catarinense de LibreOffice", firstEntry.getFormattedTitle());
        Assert.assertEquals("17/04/2013", firstEntry.getDate());
        Assert.assertEquals("index.php?id=5160&pg=1", firstEntry.getUrl());
        Assert.assertEquals("http://www.joinville.udesc.br/portal/noticias/index.php?id=5160&pg=1", firstEntry.getFormattedURL());
        Assert.assertEquals(1366167600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(20, entries.size());
    }
}
