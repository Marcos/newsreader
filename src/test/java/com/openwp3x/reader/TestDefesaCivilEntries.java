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
import com.openwp3x.Reader;
import com.openwp3x.ReaderImpl;

/**
 * @author marcos.ferreira
 * 
 */
public class TestDefesaCivilEntries {

    final URL resource = this.getClass().getClassLoader().getResource("defesa-civil-list.htm");
    Reader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getDefesaCivilPattern();
    	entryPattern.setSourceURL(resource);
        this.newsReader = new ReaderImpl(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<EntryImpl> entries = this.newsReader.getEntries();
        System.out.println(entries);

        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("Frente fria traz chuva forte e frio para SC e Fim de semana mar agitado na costa catarinense ", firstEntry.getTitle());
        Assert.assertEquals("12 | ABR | 2013", firstEntry.getDate());
        Assert.assertEquals("/noticia/131-Frente+fria+traz+chuva+forte+e+frio+para+SC+e+Fim+de+semana+mar+agitado+na+costa+catarinense+.html", firstEntry.getUrl());
        Assert.assertEquals("http://defesacivil.joinville.sc.gov.br/noticia/131-Frente+fria+traz+chuva+forte+e+frio+para+SC+e+Fim+de+semana+mar+agitado+na+costa+catarinense+.html", firstEntry.getFormattedURL());
        Assert.assertEquals(1365735600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
    
}