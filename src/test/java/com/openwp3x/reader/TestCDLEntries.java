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
public class TestCDLEntries {

    final URL resource = this.getClass().getClassLoader().getResource("cdl-joinville-list.htm");
    final URL link1 = this.getClass().getClassLoader().getResource("cdl-joinville-n1.htm");
    final URL link2 = this.getClass().getClassLoader().getResource("cdl-joinville-n2.htm");
    SourcePattern entryPattern;
    
    @Before
    public void before() throws MalformedURLException{
    	entryPattern = SourcePatternFactory.getCDLPattern();
    }

    @Test
    public void testGetLinks() throws Exception {
    	entryPattern.setSourceURL(resource);
    	SourceReader newsReader = new SourceReader(entryPattern);
        final Collection<SourceEntry> entries = newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("\n      Curso: Oratória do Básico ao AvançadoObjetivos /\nJustificativas: \n\n·\nOportunizar\naos participantes condições de se…\n    ", firstEntry.getTitle());
        String formattedTitle = firstEntry.getFormattedTitle();
        Assert.assertEquals("Curso: Oratória do Básico ao AvançadoObjetivos /", formattedTitle);
        Assert.assertEquals("noticia/13", firstEntry.getUrl());
        Assert.assertEquals("http://www.cdljoinville.com.br/noticia/13", firstEntry.getFormattedURL());
        Assert.assertEquals(5, entries.size());
    }
    
    @Test
    public void testReadLink1() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link1);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText1(), linkEntry.getText());
    	String formattedText = linkEntry.getFormattedText();
    	Assert.assertEquals(getExpectedFormattedLinkText1(), formattedText);
    }
    
    private String getExpectedFormattedLinkText1() {
		return "Curso: Vendas e Negociação \n\n \n\nObjetivos / Justificativas: Compartilhar conhecimentos\nsobre a importância de um gestor formar e manter uma equipe de vendas de\nsucesso. Conhecer a importância da qualidade no atendimento para atingir bons\nresultados de vendas.  Discutir estratégias\nde negociação para boas vendas e compras visando resultados eficazes. \n\n \n\nPrograma\n/ Conteúdo:\n\n \n\n·        \nA importância do gestor na formação e\nmanutenção de sua equipe \n\n·        \nO papel do gestor em criar novos consultores\nde vendas.\n\n·        \nA qualidade no atendimento para bons\nresultados. \n\n·        \nPlanejamento de vendas e o uso das técnicas\npara alcançar bons resultados.\n\n·        \nComo lidar com reclamações de clientes.\n\n·        \nA importância da gestão de relacionamento com\no cliente.\n\n·        \nTipos de negociação\n\n·        \nTécnicas de negociação para bons resultados. \n\n·        \nComo desenvolver a empatia\nnecessária para uma negociação eficaz\n\n·        \nJogos e estudo de casos de\nnegociação \n\n \n\nCarga\nhorária: 6 horas \n\n\n\n\n\nDatas: 27/05/2013 e 28/05/2013 Horário: 19:30 as 22:30 todos os dois dias. \n\n\n\nPúblico\nalvo: Gerentes, líderes,\nmediadores, supervisores, consultores de vendas e demais interessados.\n\n\n\n \n\nNome\ndo ministrante: Simone Wessling \n\n\n\n\n\nMini Currículo: Mestra em Educação,\nEspecialista em Comunicação, Marketing e Negócios, Bacharel em Administração de\nEmpresas. Atuou 14 anos em áreas de coordenação de equipes e 8 anos como\ndiretora das instituições FATESC e Faculdade Anhanguera de Joinville. É\nSócia-proprietária da empresa NDS Tecnologia em Informática Ltda , consultora\nempresarial,  gestora na área de\ntreinamentos empresariais , professora do SENAC e CDL. \n\n \n\nIncluso:\n\n- Certificado de\nParticipação (Válido como Horas Complementares em Cursos de Graduação);\n\n- Material Didático\nde Apoio;\n\n- Coffee Break;\n\n- Sala Climatizada\ncom wifi; \n\n- Estacionamento\nGratuito conforme Disponibilidade de Vagas;\n\n \n\nInvestimento: Associado: 2 x R$70,98\n\n                     \nNão Associado: 2 x R$110,98\n\n \n\nInformações e\nInscrições: 47 3461-2539 /\n3461-2544 / cursos@cdljoinville.com.br";
	}

	private String getExpectedLinkText1() {
		return "\n   \n\nCurso: Vendas e Negociação \n\n \n\nObjetivos / Justificativas: Compartilhar conhecimentos\nsobre a importância de um gestor formar e manter uma equipe de vendas de\nsucesso. Conhecer a importância da qualidade no atendimento para atingir bons\nresultados de vendas.  Discutir estratégias\nde negociação para boas vendas e compras visando resultados eficazes. \n\n \n\nPrograma\n/ Conteúdo:\n\n \n\n·        \nA importância do gestor na formação e\nmanutenção de sua equipe \n\n·        \nO papel do gestor em criar novos consultores\nde vendas.\n\n·        \nA qualidade no atendimento para bons\nresultados. \n\n·        \nPlanejamento de vendas e o uso das técnicas\npara alcançar bons resultados.\n\n·        \nComo lidar com reclamações de clientes.\n\n·        \nA importância da gestão de relacionamento com\no cliente.\n\n·        \nTipos de negociação\n\n·        \nTécnicas de negociação para bons resultados. \n\n·        \nComo desenvolver a empatia\nnecessária para uma negociação eficaz\n\n·        \nJogos e estudo de casos de\nnegociação \n\n \n\nCarga\nhorária: 6 horas \n\n\n\n\n\nDatas: 27/05/2013 e 28/05/2013 Horário: 19:30 as 22:30 todos os dois dias. \n\n\n\nPúblico\nalvo: Gerentes, líderes,\nmediadores, supervisores, consultores de vendas e demais interessados.\n\n\n\n \n\nNome\ndo ministrante: Simone Wessling \n\n\n\n\n\nMini Currículo: Mestra em Educação,\nEspecialista em Comunicação, Marketing e Negócios, Bacharel em Administração de\nEmpresas. Atuou 14 anos em áreas de coordenação de equipes e 8 anos como\ndiretora das instituições FATESC e Faculdade Anhanguera de Joinville. É\nSócia-proprietária da empresa NDS Tecnologia em Informática Ltda , consultora\nempresarial,  gestora na área de\ntreinamentos empresariais , professora do SENAC e CDL. \n\n \n\nIncluso:\n\n- Certificado de\nParticipação (Válido como Horas Complementares em Cursos de Graduação);\n\n- Material Didático\nde Apoio;\n\n- Coffee Break;\n\n- Sala Climatizada\ncom wifi; \n\n- Estacionamento\nGratuito conforme Disponibilidade de Vagas;\n\n \n\nInvestimento: Associado: 2 x R$70,98\n\n                     \nNão Associado: 2 x R$110,98\n\n \n\nInformações e\nInscrições: 47 3461-2539 /\n3461-2544 / cursos@cdljoinville.com.br\n\n\n \n\n\n   ";
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
		return "A CDL Joinville, através do presidente Carlos Grendene, dos vice-presidentes José Manoel Ramos e Ivonir Meurer e de lojistas associados, participou da Audiência Pública que discutiu o rebaixamento completo de meio-fio em frente a estabelecimentos comerciais dos bairros de Joinville. Esta reunião foi proposta pelas Comissões de Legislação e de Urbanismo da Câmara de Vereadores de Joinville.Em pauta, estava projeto de lei complementar do vereador Roberto Bisoni. Ele alega que os pequenos comerciantes estão fadados a fechar as portas, porque, sem a possibilidade de rebaixar as calçadas, os clientes não têm onde estacionar e, por consequência, não param no estabelecimento.O presidente da CDL, Carlos Grendene, comentou que é preciso ter uma definição logo, pois já se passaram várias décadas e nada foi definido concretamente.O Poder Executivo, representado na audiência pela Fundação IPPUJ e pelo ITTRAN, abordou aspectos da lei em vigor e como é feita a fiscalização. Hoje, somente é permitido o rebaixo em um espaço pequeno para saída e outro para entrada, sob justificativa de provimento de mais segurança para os pedestres.O debate gerou um debate sobre a falta de fiscalização, uma vez que, segundo o vereador Bisoni, mais de 80% dos estabelecimentos têm alguma irregularidade. Tal argumento foi apoiado pelos vários comerciantes que participaram das discussões.“É preciso analisar o impacto dessa proposta de forma ampla, não só olhando lado do comerciante, mas também dos pedestres, dos portadores de deficiência, da fiscalização do município, e chegar a um ponto que seja a melhor solução para longo prazo, ainda que se não seja possível atender a todos ao mesmo tempo”, afirmou o vereador Manoel Francisco Bento, que já apresentou uma emenda ao projeto.Na opinião do representante do Conselho Municipal dos Direitos da Pessoa com Deficiência de Joinville (Comde), Sérgio Luiz da Silva, já há lei federal que aborda o assunto e que veda o rebaixamento completo do meio-fio. “Se isso ocorrer em Joinville, vamos iniciar uma batalha judicial contra o município, porque não é sensato que ignoremos solemente uma lei maior”, advertiu.Além disso, o argumento do Comde veio no sentido de que o rebaixamento, ainda que não dificulte o acesso das pessoas, aumentará os riscos de colisões dos automóveis em marcha à ré com os cadeirantes ou as crianças que estiverem passando, uma vez que suas alturas dificultariam a observação pelo retrovisor; questão de segurança.O encontro serviu para que a opinião popular pudesse guiar os vereadores das comissões de Legislação e de Urbanismo rumo a um parecer favorável ou contrário. Ainda que divergentes, especialmente entre comerciantes e Comde, a tendência é de aprovação, uma vez que está acostado ao projeto um abaixo-assinado com cerca de 2.000 assinaturas de apoio. Ainda não há data para sua deliberação em plenário da Câmara de Vereadores de Joinville. Fonte: Ascom CDL";
	}

	private String getExpectedLinkText2() {
		return "\n   A CDL Joinville, através do presidente Carlos Grendene, dos vice-presidentes José Manoel Ramos e Ivonir Meurer e de lojistas associados, participou da Audiência Pública que discutiu o rebaixamento completo de meio-fio em frente a estabelecimentos comerciais dos bairros de Joinville. Esta reunião foi proposta pelas Comissões de Legislação e de Urbanismo da Câmara de Vereadores de Joinville.Em pauta, estava projeto de lei complementar do vereador Roberto Bisoni. Ele alega que os pequenos comerciantes estão fadados a fechar as portas, porque, sem a possibilidade de rebaixar as calçadas, os clientes não têm onde estacionar e, por consequência, não param no estabelecimento.O presidente da CDL, Carlos Grendene, comentou que é preciso ter uma definição logo, pois já se passaram várias décadas e nada foi definido concretamente.O Poder Executivo, representado na audiência pela Fundação IPPUJ e pelo ITTRAN, abordou aspectos da lei em vigor e como é feita a fiscalização. Hoje, somente é permitido o rebaixo em um espaço pequeno para saída e outro para entrada, sob justificativa de provimento de mais segurança para os pedestres.O debate gerou um debate sobre a falta de fiscalização, uma vez que, segundo o vereador Bisoni, mais de 80% dos estabelecimentos têm alguma irregularidade. Tal argumento foi apoiado pelos vários comerciantes que participaram das discussões.“É preciso analisar o impacto dessa proposta de forma ampla, não só olhando lado do comerciante, mas também dos pedestres, dos portadores de deficiência, da fiscalização do município, e chegar a um ponto que seja a melhor solução para longo prazo, ainda que se não seja possível atender a todos ao mesmo tempo”, afirmou o vereador Manoel Francisco Bento, que já apresentou uma emenda ao projeto.Na opinião do representante do Conselho Municipal dos Direitos da Pessoa com Deficiência de Joinville (Comde), Sérgio Luiz da Silva, já há lei federal que aborda o assunto e que veda o rebaixamento completo do meio-fio. “Se isso ocorrer em Joinville, vamos iniciar uma batalha judicial contra o município, porque não é sensato que ignoremos solemente uma lei maior”, advertiu.Além disso, o argumento do Comde veio no sentido de que o rebaixamento, ainda que não dificulte o acesso das pessoas, aumentará os riscos de colisões dos automóveis em marcha à ré com os cadeirantes ou as crianças que estiverem passando, uma vez que suas alturas dificultariam a observação pelo retrovisor; questão de segurança.O encontro serviu para que a opinião popular pudesse guiar os vereadores das comissões de Legislação e de Urbanismo rumo a um parecer favorável ou contrário. Ainda que divergentes, especialmente entre comerciantes e Comde, a tendência é de aprovação, uma vez que está acostado ao projeto um abaixo-assinado com cerca de 2.000 assinaturas de apoio. Ainda não há data para sua deliberação em plenário da Câmara de Vereadores de Joinville. Fonte: Ascom CDL\n   ";
	}
}
