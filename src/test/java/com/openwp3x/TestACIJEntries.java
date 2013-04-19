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
public class TestACIJEntries {

    final URL resource = this.getClass().getClassLoader().getResource("acij-list.htm");
    EntryReader newsReader;
    final String charset = "UTF-8";

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
        this.newsReader = new EntryReader(this.resource, this.charset, EntryPatternFactory.getAcijPattern());
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<Entry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final Entry firstEntry = entries.iterator().next();
        Assert.assertEquals(firstEntry.getTitle(), "Reunião do Conselho");
        Assert.assertEquals(firstEntry.getDate(), null);
        Assert.assertEquals(firstEntry.getUrl(), "/noticias/show/id/902%26");
        Assert.assertEquals(10, entries.size());
    }
}
