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
public class TestSociescEntries {

    final URL resource = this.getClass().getClassLoader().getResource("sociesc-list.htm");
    final URL link1 = this.getClass().getClassLoader().getResource("sociesc-n1.htm");
    final URL link2 = this.getClass().getClassLoader().getResource("sociesc-n2.htm");
    SourcePattern entryPattern;
    
    @Before
    public void before() throws MalformedURLException{
    	entryPattern = SourcePatternFactory.getSociescPattern();
    }
    
    @Test
    public void testGetLinks() throws Exception {
    	entryPattern.setSourceURL(resource);
    	SourceReader newsReader = new SourceReader(entryPattern);
        final Collection<SourceEntry> entries = newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Abertas Inscrições Para O Processo Seletivo Dos Mestrados Em Engenharia Mecânica E Engenharia De Produção...", firstEntry.getFormattedTitle());
        Assert.assertEquals("12/04/2013", firstEntry.getDate());
        Assert.assertEquals("viewRegistro(13680);return false;", firstEntry.getUrl());
        Assert.assertEquals("http://www.sociesc.org.br/pt/noticias/ajax_news_view.php?id=13680", firstEntry.getFormattedURL());
        Assert.assertEquals(1365735600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
    
    @Test
    public void testReadLink1() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link1);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText1(), linkEntry.getLinkText());
    	Assert.assertEquals(getExpectedFormattedLinkText1(), linkEntry.getFormattedLinkText());
    }
    
    private String getExpectedFormattedLinkText1() {
		return "\n Quem visitar o Stand da SOCIESC de 14 a 17 de maio na Expogestão pode concorrer a uma bolsa integral da pós em MBA em Gestão Empresarial SOCIESC/FGV\n \n  A Sociedade Educacional de Santa Catarina - SOCIESC promove o Concurso Cultural “Minha Vida na SOCIESC/FGV” de 14 a 17 de maio de 2013. Para participar basta visitar o Stand da SOCIESC na Expogestão, preencher o cupom e usar a criatividade com uma frase com até 30 palavras para responder: Por que a minha vida merece ser presenteada com\n  a pós-graduação MBA em Gestão Empresarial SOCIESC/FGV? O autor da frase mais criativa e coerente com o tema será o ganhador de uma bolsa de estudos integral do MBA em Gestão Empresarial SOCIESC/FGV.\n \n \n  Para consultar o regulamento clique aqui.\n \n \n  O resultado será divulgado em 27/06/2013 no site www.sociesc.org.br\n \n \n \n  Fonte: Assessoria de Imprensa\n \n";
	}

	private String getExpectedLinkText1() {
		return "\n Quem visitar o Stand da SOCIESC de 14 a 17 de maio na Expogestão pode concorrer a uma bolsa integral da pós em MBA em Gestão Empresarial SOCIESC/FGV\n \n  A Sociedade Educacional de Santa Catarina - SOCIESC promove o Concurso Cultural “Minha Vida na SOCIESC/FGV” de 14 a 17 de maio de 2013. Para participar basta visitar o Stand da SOCIESC na Expogestão, preencher o cupom e usar a criatividade com uma frase com até 30 palavras para responder: Por que a minha vida merece ser presenteada com\n  a pós-graduação MBA em Gestão Empresarial SOCIESC/FGV? O autor da frase mais criativa e coerente com o tema será o ganhador de uma bolsa de estudos integral do MBA em Gestão Empresarial SOCIESC/FGV.\n \n \n  Para consultar o regulamento clique aqui.\n \n \n  O resultado será divulgado em 27/06/2013 no site www.sociesc.org.br\n \n \n \n  Fonte: Assessoria de Imprensa\n \n";
	}

	@Test
    public void testReadLink2() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link2);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText2(), linkEntry.getLinkText());
    	Assert.assertEquals(getExpectedFormattedLinkText2(), linkEntry.getFormattedLinkText());
    }

	private String getExpectedFormattedLinkText2() {
		return "\n O Instituto Superior Tupy, IST/Sociesc abre inscrições para o curso de extensão gratuito de Inclusão Digital. Como forma de envolver a comunidade interna, estudantes do IST/Sociesc colocam em prática o que aprendem e serão monitores da atividade.\n O curso tem o objetivo de promover o crescimento pessoal e social da comunidade, levando à melhoria das condições de vida e das perspectivas de atuação no mercado de trabalho, por meio de aprendizagem aos conceitos básicos do computador, informática e o uso dos programas Windows, Word, Excel e Internet.\n “O Inclusão I é para os iniciantes e o Inclusão II para quem já fez o Inclusão I e pretende aperfeiçoar seus conhecimentos Ao final, os alunos serão avaliados pelos conhecimentos adquiridos e receberão um certificado”, comenta o coordenador do projeto de extensão Prof. Moisés de Souza.\n \n  Serviço O quê:\n   Curso de extensão Inclusão Digital\n \n \n  Quando: de 27 de abril a 18 de maio\n \n \n  Onde: Sociesc, campus Marquês de Olinda, das 8 às 12 horas, aos sábados\n \n \n  Inscrições: Sociesc, campus Marquês de Olinda, das 13h30 às 21h30 ou pelo telefone 3461-0520 com Michele.\n \n \n  Quanto: Gratuito e aberto à comunidade\n \n \n  Vagas limitadas\n \n \n \n  Fonte: Assessoria de Imprensa Sociesc\n \n";
	}

	private String getExpectedLinkText2() {
		return "\n O Instituto Superior Tupy, IST/Sociesc abre inscrições para o curso de extensão gratuito de Inclusão Digital. Como forma de envolver a comunidade interna, estudantes do IST/Sociesc colocam em prática o que aprendem e serão monitores da atividade.\n O curso tem o objetivo de promover o crescimento pessoal e social da comunidade, levando à melhoria das condições de vida e das perspectivas de atuação no mercado de trabalho, por meio de aprendizagem aos conceitos básicos do computador, informática e o uso dos programas Windows, Word, Excel e Internet.\n “O Inclusão I é para os iniciantes e o Inclusão II para quem já fez o Inclusão I e pretende aperfeiçoar seus conhecimentos Ao final, os alunos serão avaliados pelos conhecimentos adquiridos e receberão um certificado”, comenta o coordenador do projeto de extensão Prof. Moisés de Souza.\n \n  Serviço O quê:\n   Curso de extensão Inclusão Digital\n \n \n  Quando: de 27 de abril a 18 de maio\n \n \n  Onde: Sociesc, campus Marquês de Olinda, das 8 às 12 horas, aos sábados\n \n \n  Inscrições: Sociesc, campus Marquês de Olinda, das 13h30 às 21h30 ou pelo telefone 3461-0520 com Michele.\n \n \n  Quanto: Gratuito e aberto à comunidade\n \n \n  Vagas limitadas\n \n \n \n  Fonte: Assessoria de Imprensa Sociesc\n \n";
	}
}
