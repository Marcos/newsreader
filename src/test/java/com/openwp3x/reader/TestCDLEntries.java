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
public class TestCDLEntries {

    final URL resource = this.getClass().getClassLoader().getResource("cdl-joinville-list.htm");
    EntryReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getCDLPattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new EntryReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<EntryImpl> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals(firstEntry.getTitle(), "Vagas CDL Talentos");
        Assert.assertEquals(firstEntry.getDate(), "12/04/2013");
        Assert.assertEquals(firstEntry.getUrl(), "index.php?cat=noticias&id_noticia=7284");
        Assert.assertEquals("http://cdljoinville.com.br/index.php?cat=noticias&id_noticia=7284", firstEntry.getFormattedURL());
        Assert.assertEquals(8, entries.size());
    }
}
