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

import com.openwp3x.SourcePattern;
import com.openwp3x.SourcePatternFactory;

/**
 * @author marcos.ferreira
 * 
 */
public class TestOsnyMartinsEntries {

    final URL resource = this.getClass().getClassLoader().getResource("osny-martins-list.htm");
    SourceReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	SourcePattern entryPattern = SourcePatternFactory.getOsnyMartinsPattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new SourceReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<SourceEntry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("24-04-2013 CONFIRMADA 10. FEIJOADA DO “BREAKFAST” PARA DUAS ENTIDADES", firstEntry.getTitle());
        Assert.assertEquals("Confirmada 10. Feijoada Do “breakfast” Para Duas Entidades", firstEntry.getFormattedTitle());
        Assert.assertEquals("24 Apr 2013", firstEntry.getDate());
        Assert.assertEquals("http://osnymartins.com.br/geral/24-04-2013-confirmada-10-feijoada-do-breakfast-para-duas-entidades/", firstEntry.getUrl());
        Assert.assertEquals("http://osnymartins.com.br/geral/24-04-2013-confirmada-10-feijoada-do-breakfast-para-duas-entidades/", firstEntry.getFormattedURL());
        Assert.assertEquals(1366772400000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals("Este ano, a já tradicional feijoada do “Breakfast” vai ter uma novidade básica. O lucro líquido da promoção, como sempre acontece, desde a primeira edição, será todo doado, mas com o benefício para duas entidades e não apenas uma como aconteceu até o ano passado. Além do Centrinho, que realiza um trabalho referência catarinense no [...]", firstEntry.getText());
        Assert.assertEquals(10, entries.size());
    }
}
