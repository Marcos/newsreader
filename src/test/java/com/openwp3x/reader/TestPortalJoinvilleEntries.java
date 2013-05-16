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
public class TestPortalJoinvilleEntries {

    final URL resource = this.getClass().getClassLoader().getResource("portal-jlle-list.htm");
    SourceReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	SourcePattern entryPattern = SourcePatternFactory.getNDJoinvillePattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new SourceReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<SourceEntry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Arturzinho fala sobre a sua manutenção no time", firstEntry.getTitle());
        Assert.assertEquals("23 Apr 2013", firstEntry.getDate());
        Assert.assertEquals("http://www.portaljoinville.com.br/v4/esportes/2013/04/arturzinho-fala-sobre-a-sua-manutencao-no-time", firstEntry.getUrl());
        Assert.assertEquals("http://www.portaljoinville.com.br/v4/esportes/2013/04/arturzinho-fala-sobre-a-sua-manutencao-no-time", firstEntry.getFormattedURL());
        Assert.assertEquals(1366686000000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(getText(), firstEntry.getText());
        String formattedText = firstEntry.getFormattedText();
        Assert.assertEquals(getFormattedText(), formattedText);
        Assert.assertEquals(10, entries.size());
    }

	private String getFormattedText() {
		return "O elenco do Joinville se reapresentou nesta terça-feira e, em dois períodos, trabalhou no Centro de Treinamentos e na quadra de areia da Schultz, no bairro Anita Garibaldi. Entre as atividades, o comandante concedeu entrevista para falar da reunião com a diretoria, pretensões para a Série B, além das declarações…";
	}

	private String getText() {
		return "O elenco do Joinville se reapresentou nesta terça-feira e, em dois períodos, trabalhou no Centro de Treinamentos e na quadra de areia da Schultz, no bairro Anita Garibaldi. Entre as atividades, o comandante concedeu entrevista para falar da reunião com a diretoria, pretensões para a Série B, além das declarações…\n<br /><br /> <img src=\"http://www.portaljoinville.com.br/v4/facecms/conteudo/temp/pj_25783.jpg\" />";
	}
}
