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
public class TestDefesaCivilEntries {

    final URL resource = this.getClass().getClassLoader().getResource("defesa-civil-list.htm");
    EntryReader newsReader;
    final String charset = "UTF-8";

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
        this.newsReader = new EntryReader(this.resource, this.charset, EntryPatternFactory.getDefesaCivilPattern());
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<Entry> entries = this.newsReader.getEntries();
        System.out.println(entries);

        final Entry firstEntry = entries.iterator().next();
        Assert.assertEquals("Frente fria traz chuva forte e frio para SC e Fim de semana mar agitado na costa catarinense ", firstEntry.getTitle());
        Assert.assertEquals("12 | ABR | 2013", firstEntry.getDate());
        Assert.assertEquals("/noticia/131-Frente+fria+traz+chuva+forte+e+frio+para+SC+e+Fim+de+semana+mar+agitado+na+costa+catarinense+.html", firstEntry.getUrl());
        Assert.assertEquals(10, entries.size());
    }
}
