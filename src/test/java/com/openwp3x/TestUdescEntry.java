/**
 * 
 */
package com.openwp3x;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author marcos.ferreira
 * 
 */
@Ignore
public class TestUdescEntry {

	final URL resource = this.getClass().getClassLoader().getResource("prefeitura-list.htm");
    EntryReader newsReader;
    final String charset = "ISO-88591";

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException{
    	newsReader = new EntryReader(this.resource, this.charset, EntryPatternFactory.getUdescPattern());
    }

    @Test
    public void testGetTitle() throws Exception {
        String entry = "";
        entry = this.newsReader.getTitle( "//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[1]/p[2]");
        Assert.assertEquals("Programa \"Extensão Udesc\" estreia na Rádio Udesc (91.9 FM)", entry);
    }

    @Test
    public void testGetText() throws Exception {
        String entry = "";
        entry = this.newsReader.getTextContent( "//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[1]");
        Assert.assertEquals(this.getNewText(), entry);
    }

    @Test
    public void testGetDateContent() throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        String date = "";
        final String datePattern = "\\d{2}/\\d{2}/\\d{4}";
        date = newsReader.getDateContent("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[1]/p", datePattern);
        Assert.assertEquals("10/04/2013", date);
    }

    /**
     * @return
     */
    private String getNewText() {
        return "test";
    }

    @Test
    public void testFindDateInText() {
        final String text = "Joinville, SC - 10/04/2013";
        final String datePatern = "\\d{2}/\\d{2}/\\d{4}";
        Assert.assertEquals("10/04/2013", this.newsReader.getDateFromText(text, datePatern));
    }
}
