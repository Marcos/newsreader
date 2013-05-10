/**
 * 
 */
package com.openwp3x.reader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;

import com.openwp3x.EntryImpl;
import com.openwp3x.EntryPattern;
import com.openwp3x.EntryPatternFactory;
import com.openwp3x.EntryReader;

/**
 * @author marcos.ferreira
 * 
 */
public class TestUdescEntries {

    final URL resource = this.getClass().getClassLoader().getResource("udesc-list.htm");
    final URL link1 = this.getClass().getClassLoader().getResource("udesc-n1.htm");
    final URL link2 = this.getClass().getClassLoader().getResource("udesc-n2.htm");

    @Test
    public void testGetLinks() throws Exception {
    	EntryPattern entryPattern = EntryPatternFactory.getUdescPattern();
    	entryPattern.setSourceURL(resource);
    	EntryReader newsReader = new EntryReader(entryPattern);
        final Collection<EntryImpl> entries = newsReader.getEntries();
        
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("Termina neste domingo prazo para submissão de trabalhos no Encontro Catarinense de LibreOffice", firstEntry.getFormattedTitle());
        Assert.assertEquals("17/04/2013", firstEntry.getDate());
        Assert.assertEquals("index.php?id=5160&pg=1", firstEntry.getUrl());
        Assert.assertEquals("http://www.joinville.udesc.br/portal/noticias/index.php?id=5160&pg=1", firstEntry.getFormattedURL());
        Assert.assertEquals(1366167600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(20, entries.size());
    }
    
    @Test
    public void testReadLink1() throws MalformedURLException{
    	EntryPattern entryPattern = EntryPatternFactory.getUdescPattern();
    	entryPattern.setSourceURL(link1);
    	//Reader newsReader = new Reader(entryPattern);
    }
}
