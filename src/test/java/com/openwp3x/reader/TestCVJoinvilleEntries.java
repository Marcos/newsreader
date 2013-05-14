
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
public class TestCVJoinvilleEntries {

    final URL resource = this.getClass().getClassLoader().getResource("cv-joinville-list.htm");
    SourceReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	SourcePattern entryPattern = SourcePatternFactory.getCVJoinvillePattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new SourceReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<SourceEntry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Legislativo na entrega de ordem de serviço", firstEntry.getTitle());
        Assert.assertEquals("2013-04-24", firstEntry.getDate());
        Assert.assertEquals("/component/content/article/47-noticias/1821-legislativo-na-entrega-de-ordem-de-servico", firstEntry.getUrl());
        Assert.assertEquals("http://www.cvj.sc.gov.br/component/content/article/47-noticias/1821-legislativo-na-entrega-de-ordem-de-servico", firstEntry.getFormattedURL());
        Assert.assertEquals(1366772400000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(9, entries.size());
    }

}
