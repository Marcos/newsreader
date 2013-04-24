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
public class TestAnoticiaEntries {

    final URL resource = this.getClass().getClassLoader().getResource("anoticia-list.htm");
    EntryReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getNDJoinvillePattern();
    	entryPattern.setSourceURL(resource);
        this.newsReader = new EntryReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<Entry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final Entry firstEntry = entries.iterator().next();
        Assert.assertEquals("Justiça exige solução para a superlotação na Central de Polícia em Joinville", firstEntry.getTitle());
        Assert.assertEquals("23 Apr 2013", firstEntry.getDate());
        Assert.assertEquals("http://anoticia.clicrbs.com.br/sc/noticia/2013/04/justica-exige-solucao-para-a-superlotacao-na-central-de-policia-em-joinville-4115903.html", firstEntry.getUrl());
        Assert.assertEquals("http://anoticia.clicrbs.com.br/sc/noticia/2013/04/justica-exige-solucao-para-a-superlotacao-na-central-de-policia-em-joinville-4115903.html", firstEntry.getFormattedURL());
        Assert.assertEquals(1366686000000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
}
