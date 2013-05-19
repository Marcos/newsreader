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
public class TestUnivilleEntries {

    URL resource = this.getClass().getClassLoader().getResource("univille-list.htm");
    URL link1 = this.getClass().getClassLoader().getResource("univille-n1.htm");
    URL link2 = this.getClass().getClassLoader().getResource("univille-n2.htm");
    SourcePattern entryPattern;
    
    @Before
    public void before() throws MalformedURLException{
    	entryPattern = SourcePatternFactory.getUnivillePattern();
    }

    @Test
    public void testGetLinks() throws Exception {
    	entryPattern.setSourceURL(resource);
    	SourceReader newsReader = new SourceReader(entryPattern);
        final Collection<SourceEntry> entries = newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Maratona de inglês", firstEntry.getTitle());
        Assert.assertEquals("30.04.2013", firstEntry.getDate());
        Assert.assertEquals("http://www.univille.edu.br/contentId/465612", firstEntry.getUrl());
        Assert.assertEquals("http://www.univille.edu.br/contentId/465612",firstEntry.getFormattedURL());
        Assert.assertEquals(1367290800000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
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
		return "Nada mais propício à solidariedade e à união do que uma refeição. O processo de cozinhar gera trabalho em equipe, acolhe e proporciona momentos de intimidade e de cuidado durante a alimentação. Um dos desdobramentos do projeto de extensão Ecosol da Univille, o Solabor (o sabor de solidariedade nos empreendimentos de alimentação) se fortalece como um projeto que tem a intenção de capacitar agentes na área de gastronomia e aspectos do design a ela relacionados. Atende também às demandas apresentadas pelos parceiros participantes do Fórum de Economia Solidária de Joinville e Norte Catarinense.\n\n O Solabor tem 40 participantes, empreendedores em alimentação, oriundos de diversas comunidades, incluindo empreendimentos da área rural de Joinville e região. O Instituto Consulado da Mulher de Joinville é parceiro da iniciativa.\n\n O projeto, gratuito para os participantes, atende indiretamente mais de 150 pessoas, segundo a coordenadora, professora Rita Inês Petrykowski Peixe, do Departamento de Design. “Muitos dos participantes são agentes multiplicadores dos conhecimentos adquiridos”, explica. Os encontros acontecem às terças-feiras à tarde, no Centro de Artes e Design CAD ou no espaço da Gastronomia.\n\n O projeto de extensão participa indiretamente de pelo menos três dos oitos jeitos de transformar o mundo, estabelecidos pela Organização das Nações Unidas (ONU) como os Objetivos de Desenvolvimento do Milênio (ODM): acabar com a fome e a miséria; igualdade entre os sexos e valorização da mulher; e todo mundo trabalhando pelo desenvolvimento.";
	}

	private String getExpectedLinkText1() {
		return "\r\n  \n  \n\n Nada mais propício à solidariedade e à união do que uma refeição. O processo de cozinhar gera trabalho em equipe, acolhe e proporciona momentos de intimidade e de cuidado durante a alimentação. Um dos desdobramentos do projeto de extensão Ecosol da Univille, o Solabor (o sabor de solidariedade nos empreendimentos de alimentação) se fortalece como um projeto que tem a intenção de capacitar agentes na área de gastronomia e aspectos do design a ela relacionados. Atende também às demandas apresentadas pelos parceiros participantes do Fórum de Economia Solidária de Joinville e Norte Catarinense.\n\n O Solabor tem 40 participantes, empreendedores em alimentação, oriundos de diversas comunidades, incluindo empreendimentos da área rural de Joinville e região. O Instituto Consulado da Mulher de Joinville é parceiro da iniciativa.\n\n O projeto, gratuito para os participantes, atende indiretamente mais de 150 pessoas, segundo a coordenadora, professora Rita Inês Petrykowski Peixe, do Departamento de Design. “Muitos dos participantes são agentes multiplicadores dos conhecimentos adquiridos”, explica. Os encontros acontecem às terças-feiras à tarde, no Centro de Artes e Design CAD ou no espaço da Gastronomia.\n\n O projeto de extensão participa indiretamente de pelo menos três dos oitos jeitos de transformar o mundo, estabelecidos pela Organização das Nações Unidas (ONU) como os Objetivos de Desenvolvimento do Milênio (ODM): acabar com a fome e a miséria; igualdade entre os sexos e valorização da mulher; e todo mundo trabalhando pelo desenvolvimento.\r\n ";
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
		return "O curso superior de Fotografia da Univille promove neste sádado, dia 11, na praça Dario Salles, a 1ªMaratona Fotográfica com o tema Olhares para Joinville. O concurso, com participação gratuita e aberta à população, fotógrafos ou não, começa às 8h e vai ter duas categorias, a digital e a instagram.\n\n As fotografias serão selecionadas por um júri nomeado pela comissão organizadora, e os critérios de avaliação levarão em conta o conjunto de fotografias, criatividade, técnica e adequação ao tema.  Além da premiação, os vencedores terão suas obras divulgadas na página do Facebook do Curso Superior de Fotografia e nas demais redes sociais. Entre os prêmios estão câmera fotográfica digital, celular e livros de fotografia.\n\n O objetivo, segundo a professora Karla Pfeiffer, é incentivar a produção de imagens fotográficas, aumentar os conhecimentos dos participantes sobre a cidade de Joinville, e despertar o sentido de memória e preservação do patrimônio e de sua história. \n\n Pelo regulamento,  os participantes deverão fotografar oito temas, sendo uma foto por tema retratando as peculiaridades naturais e culturais de Joinville. A largada da 1ª etapa será às 9h, com divulgação dos quatro primeiros temas a serem fotografados. Às 13h, os participantes deverão retornar para o local de largada, onde serão divulgados os quatro temas da tarde. A entrega do material deve ser realizada no mesmo local, até às 17h.\n\n  \n\n O quê: 1ª Maratona Fotográfica de Joinville\n\n Quando: sábado, dia 11\n\n Onde: praça Dario Salles\n\n Quanto: gratuito\n\n Mais informações: 3461 9088";
	}

	private String getExpectedLinkText2() {
		return "\r\n  \n  \n\n O curso superior de Fotografia da Univille promove neste sádado, dia 11, na praça Dario Salles, a 1ªMaratona Fotográfica com o tema Olhares para Joinville. O concurso, com participação gratuita e aberta à população, fotógrafos ou não, começa às 8h e vai ter duas categorias, a digital e a instagram.\n\n As fotografias serão selecionadas por um júri nomeado pela comissão organizadora, e os critérios de avaliação levarão em conta o conjunto de fotografias, criatividade, técnica e adequação ao tema.  Além da premiação, os vencedores terão suas obras divulgadas na página do Facebook do Curso Superior de Fotografia e nas demais redes sociais. Entre os prêmios estão câmera fotográfica digital, celular e livros de fotografia.\n\n O objetivo, segundo a professora Karla Pfeiffer, é incentivar a produção de imagens fotográficas, aumentar os conhecimentos dos participantes sobre a cidade de Joinville, e despertar o sentido de memória e preservação do patrimônio e de sua história. \n\n Pelo regulamento,  os participantes deverão fotografar oito temas, sendo uma foto por tema retratando as peculiaridades naturais e culturais de Joinville. A largada da 1ª etapa será às 9h, com divulgação dos quatro primeiros temas a serem fotografados. Às 13h, os participantes deverão retornar para o local de largada, onde serão divulgados os quatro temas da tarde. A entrega do material deve ser realizada no mesmo local, até às 17h.\n\n  \n\n O quê: 1ª Maratona Fotográfica de Joinville\n\n Quando: sábado, dia 11\n\n Onde: praça Dario Salles\n\n Quanto: gratuito\n\n Mais informações: 3461 9088\r\n ";
	}
}
