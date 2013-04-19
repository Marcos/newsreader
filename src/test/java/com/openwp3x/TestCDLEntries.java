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
public class TestCDLEntries {

    final URL resource = this.getClass().getClassLoader().getResource("cdl-joinville-list.htm");
    EntryReader newsReader;
    final String charset = "UTF-8";

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
        this.newsReader = new EntryReader(this.resource, this.charset, EntryPatternFactory.getCDLPattern());
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<Entry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final Entry firstEntry = entries.iterator().next();
        Assert.assertEquals(firstEntry.getTitle(), "Vagas CDL Talentos");
        Assert.assertEquals(firstEntry.getDate(), "12/04/2013");
        Assert.assertEquals(firstEntry.getUrl(), "index.php?cat=noticias&id_noticia=7284");
        Assert.assertEquals(10, entries.size());
    }
}
