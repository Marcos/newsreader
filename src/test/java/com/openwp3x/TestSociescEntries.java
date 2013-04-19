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
        Assert.assertEquals(firstEntry.getTitle(), "Abertas inscrições para o processo seletivo dos Mestrados em Engenharia Mecânica e Engenharia de Produção...");
        Assert.assertEquals(firstEntry.getDate(), "12/04/2013");
        Assert.assertEquals(firstEntry.getUrl(), "viewRegistro(13680);return false;");
        Assert.assertEquals(firstEntry.getFormattedURL(), "http://www.sociesc.org.br/pt/noticias/ajax_news_view.php?id=13680");
        Assert.assertEquals(firstEntry.getDateAsLong().longValue(), 1365735600000L);
        Assert.assertEquals(10, entries.size());
    }
}
