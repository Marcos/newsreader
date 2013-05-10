/**
 * 
 */
package com.openwp3x.reader;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class TestAjorpemeEntries {

    final URL resource = this.getClass().getClassLoader().getResource("ajorpeme-list.htm");
    Reader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	EntryPattern entryPattern = EntryPatternFactory.getAjorpemePattern();
    	entryPattern.setSourceURL(resource);
        this.newsReader = new ReaderImpl(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<EntryImpl> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("\n   18/04/13 - Esta semana tem Café & Negócios na Ajorpeme", firstEntry.getTitle());
        Assert.assertEquals("18/04/13", firstEntry.getDate());
        Assert.assertEquals("/site/noticias/1803-180413-esta-semana-tem-cafe-a-negocios-na-ajorpeme", firstEntry.getUrl());
        Assert.assertEquals("Esta semana tem Café & Negócios na Ajorpeme", firstEntry.getFormattedTitle());
        Assert.assertEquals("http://ajorpeme.com.br/site/noticias/1803-180413-esta-semana-tem-cafe-a-negocios-na-ajorpeme", firstEntry.getFormattedURL());
        
        Assert.assertEquals(10, entries.size());
    }

    @Test
    public void testCleanerStart() {
        final String dirtText = "\n   18/04/13 - Esta semana tem Café & Negócios na Ajorpeme";
        String treatPrefixPattern = "\\.*\\s-\\s";
        final String cleanedText = new EntryImpl().treatPrefixPattern(dirtText, treatPrefixPattern);
        Assert.assertEquals(cleanedText, "Esta semana tem Café & Negócios na Ajorpeme");
    }

    @Test
    public void testInfoPattern() {
        final String dirtText = "viewRegistro(13680);return false;";
        final Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(dirtText);
        matcher.find();
        Assert.assertEquals(matcher.group(), "13680");

        final String dirtText2 = "viewRegistro(1368088);return false;";
        matcher = pattern.matcher(dirtText2);
        matcher.find();
        Assert.assertEquals(matcher.group(), "1368088");

        final String dirtText3 = "viewRegistro(1368088);return 123;";
        matcher = pattern.matcher(dirtText3);
        matcher.find();
        final String cleanedText = matcher.group();
        Assert.assertEquals(cleanedText, "1368088");

        final String dirtText4 = "viewRegistro(1);return false;";
        matcher = pattern.matcher(dirtText4);
        matcher.find();
        Assert.assertEquals(matcher.group(), "1");
    }
}
