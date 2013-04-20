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
        final Collection<Entry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final Entry firstEntry = entries.iterator().next();
        Assert.assertEquals(firstEntry.getTitle(), "Aprovada doação de terreno para construção do Centro de Inovação no Inovaparq");
        Assert.assertEquals(firstEntry.getDate(), "16.04.2013");
        Assert.assertEquals(firstEntry.getUrl(), "http://www.univille.edu.br/contentId/458963");
        Assert.assertEquals(firstEntry.getFormattedURL(), "http://www.univille.edu.br/contentId/458963");
        Assert.assertEquals(firstEntry.getDateAsLong().longValue(), 1366081200000L);
        Assert.assertEquals(10, entries.size());
    }
}
