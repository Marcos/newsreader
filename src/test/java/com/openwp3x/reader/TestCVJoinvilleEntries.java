
/**
 * 
 */
package com.openwp3x.reader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.openwp3x.SourcePattern;
import com.openwp3x.SourcePatternFactory;

/**
 * @author marcos.ferreira
 * 
 */
public class TestCVJoinvilleEntries {

    final URL resource = this.getClass().getClassLoader().getResource("cv-joinville-list.htm");
    final URL link1 = this.getClass().getClassLoader().getResource("cv-joinville-n1.htm");
    final URL link2 = this.getClass().getClassLoader().getResource("cv-joinville-n2.htm");
    SourcePattern entryPattern;
    
    @Before
    public void before() throws MalformedURLException{
    	entryPattern = SourcePatternFactory.getCVJoinvillePattern();
    }

    @Test
    public void testGetLinks() throws Exception {
    	entryPattern.setSourceURL(resource);
    	SourceReader newsReader = new SourceReader(entryPattern);
        final Collection<SourceEntry> entries = newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Legislativo na entrega de ordem de serviço", firstEntry.getTitle());
        Assert.assertEquals("2013-04-24", firstEntry.getDate());
        Assert.assertEquals("/component/content/article/47-noticias/1821-legislativo-na-entrega-de-ordem-de-servico", firstEntry.getUrl());
        Assert.assertEquals("http://www.cvj.sc.gov.br/component/content/article/47-noticias/1821-legislativo-na-entrega-de-ordem-de-servico", firstEntry.getFormattedURL());
        Assert.assertEquals(1366772400000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(9, entries.size());
    }
    
    @Test
    public void testReadLink1() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link1);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText1(), linkEntry.getText());
    	String formatedText = linkEntry.getFormattedText();
    	Assert.assertEquals(getExpectedFormattedLinkText1(), formatedText);
    }
    
    private String getExpectedFormattedLinkText1() {
		return "Atribui-se ao filósofo grego Aristóteles a frase: “A coragem é a primeira das qualidades humanas, porque garante todas as outras”. Nilson Wilson Bender, carinhosamente entre nós, joinvilenses, o “Seu Bender” ou “Doutor Bender”, as tinha todas, com destaque especial para a visão de futuro, o senso de justiça e humanidade, e, principalmente, a humilde.";
	}

	private String getExpectedLinkText1() {
		return "Atribui-se ao filósofo grego Aristóteles a frase: “A coragem é a primeira das qualidades humanas, porque garante todas as outras”. Nilson Wilson Bender, carinhosamente entre nós, joinvilenses, o “Seu Bender” ou “Doutor Bender”, as tinha todas, com destaque especial para a visão de futuro, o senso de justiça e humanidade, e, principalmente, a humilde.";
	}

	@Test
    public void testReadLink2() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link2);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText2(), linkEntry.getText());
    	String formatedText = linkEntry.getFormattedText();
    	Assert.assertEquals(getExpectedFormattedLinkText2(), formatedText);
    }

	private String getExpectedFormattedLinkText2() {
		return "A Câmara de Vereadores de Joinville sedia nesta quinta (9) e sexta-feira (10) o 4º Encontro de Mulheres Parlamentares de Santa Catarina. O horário do evento acontece das 8h30min às 16h. Estarão participando do evento deputadas, vereadoras, lideranças sociais e comunitárias engajadas na atuação política. Ao todo, serão 12 horas/aula. Durante o evento, será realizada a eleição para a mesa diretora do Fórum da Mulher Vereadora de SC. Inscrições gratuitas e informações no site da Escola do Legislativo da Alesc, ou pelos telefones (48) 3221-2952 e (48) 2828/2927. Mais informações, em Joinville, com a Escola do Legislativo Joinvilense, pelo telefone: (47) 2101-3333.";
	}

	private String getExpectedLinkText2() {
		return "A Câmara de Vereadores de Joinville sedia nesta quinta (9) e sexta-feira (10) o 4º Encontro de Mulheres Parlamentares de Santa Catarina. O horário do evento acontece das 8h30min às 16h. Estarão participando do evento deputadas, vereadoras, lideranças sociais e comunitárias engajadas na atuação política. Ao todo, serão 12 horas/aula. Durante o evento, será realizada a eleição para a mesa diretora do Fórum da Mulher Vereadora de SC. Inscrições gratuitas e informações no site da Escola do Legislativo da Alesc, ou pelos telefones (48) 3221-2952 e (48) 2828/2927. Mais informações, em Joinville, com a Escola do Legislativo Joinvilense, pelo telefone: (47) 2101-3333.";
	}

}
