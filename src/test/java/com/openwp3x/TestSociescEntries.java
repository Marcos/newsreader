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
public class TestSociescEntries {

    final URL resource = this.getClass().getClassLoader().getResource("sociesc-list.htm");
    EntryReader newsReader;
    final String charset = "ISO-8859-1";

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
        this.newsReader = new EntryReader(this.resource, this.charset, EntryPatternFactory.getSociescPattern());
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<Entry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final Entry firstEntry = entries.iterator().next();
        Assert.assertEquals("Abertas inscrições para o processo seletivo dos Mestrados em Engenharia Mecânica e Engenharia de Produção...", firstEntry.getFormattedTitle());
        Assert.assertEquals("12/04/2013", firstEntry.getDate());
        Assert.assertEquals("viewRegistro(13680);return false;", firstEntry.getUrl());
        Assert.assertEquals("http://www.sociesc.org.br/pt/noticias/ajax_news_view.php?id=13680", firstEntry.getFormattedURL());
        Assert.assertEquals(1365735600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
}
