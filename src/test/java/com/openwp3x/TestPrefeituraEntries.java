/**
 * 
 */
package com.openwp3x;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author marcos.ferreira
 * 
 */
public class TestPrefeituraEntries {

    final URL resource = this.getClass().getClassLoader().getResource("prefeitura-list.htm");
    EntryReader newsReader;
    final String charset = "UTF-8";

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
        this.newsReader = new EntryReader(this.resource, this.charset, EntryPatternFactory.getPrefeituraPattern());
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<Entry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final Entry firstEntry = entries.iterator().next();
        Assert.assertEquals(firstEntry.getTitle(), "Fundação 25 de Julho e Secretaria de Desenvolvimento Econômico visitam produtores rurais");
        Assert.assertEquals(firstEntry.getDate(), "17 | ABR | 2013");
        Assert.assertEquals(firstEntry.getUrl(), "/noticia/4202-Funda%C3%A7%C3%A3o+25+de+Julho+e+Secretaria+de+Desenvolvimento+Econ%C3%B4mico+visitam+produtores+rurais.html");
        Assert.assertEquals(firstEntry.getFormattedURL(), "http://www.joinville.sc.gov.br/noticia/4202-Funda%C3%A7%C3%A3o+25+de+Julho+e+Secretaria+de+Desenvolvimento+Econ%C3%B4mico+visitam+produtores+rurais.html");
        Assert.assertEquals(firstEntry.getDateAsLong().longValue(), 1366167600000L);
        Assert.assertEquals(10, entries.size());
    }

    @Test
    public void testDateFormat() {
        System.out.println(Entry.getDateAsLong("17 | ABR | 2013", new Locale("pt", "BR"), "dd | MMM | yyyy"));
    }
}
