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
public class TestACIJEntries {

    final URL resource = this.getClass().getClassLoader().getResource("acij-list.htm");
    final URL link1 = this.getClass().getClassLoader().getResource("acij-n1.htm");
    final URL link2 = this.getClass().getClassLoader().getResource("acij-n2.htm");
    SourcePattern entryPattern;
    
    @Before
    public void before() throws MalformedURLException{
    	entryPattern = SourcePatternFactory.getAcijPattern();
    }

    @Test
    public void testGetLinks() throws Exception {
    	entryPattern.setSourceURL(resource);
    	SourceReader newsReader = new SourceReader(entryPattern);
        final Collection<SourceEntry> entries = newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Revista 21", firstEntry.getTitle());
        Assert.assertEquals(null, firstEntry.getDate());
        Assert.assertEquals("/noticias/show/id/901%26", firstEntry.getUrl());
        Assert.assertEquals("http://www.acij.com.br/noticias/show/id/901%26", firstEntry.getFormattedURL());
        Assert.assertEquals(4, entries.size());
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
		return "O Gestão Compartilhada Norte recebe o Tenente Ruy Florêncio Teixeira Junior - Comandante de Transito do 8º Batalhão de Polícia Militar de Joinville\nCrédito: DivulgaçãoPerini Business Park\nRua Dona Francisca é recordista em acidentes\nSegundo dados da Polícia Militar de Joinville, o Distrito Industrial é o bairro de Joinville com maior número de ocorrências de trânsito com danos materiais, com mais de 200 ocorrências registradas de janeiro a abril deste ano. Deste total, aproximadamente 85 acidentes ocorreram na rua Dona Francisca, seguida de perto pela Avenida Santos Dumont, com 70 sinistros. Esses números foram apresentados pelo tenente Ruy Florêncio Teixeira Junior, comandante do Pelotão de Trânsito de Joinville, durante reunião do grupo de Gestão Compartilhada Norte da Acij, realizada nesta manhã (dia 9), no Perini Business Park.\nEstava em debate no evento a questão da duplicação da rua Dona Francisca, entre a rua Edmundo Doubrawa e o trevo da Döehler, uma das mais antigas reivindicações dos empresários da área do Distrito Industrial de Joinville. Somente neste trecho de aproximadamente 6 km foram registrados 164 acidentes em 2012 e 8 até abril de 2013. Do total de 172 acidentes, 106 resultaram em danos materiais, 53 em lesão corporal, uma vítima fatal e 12 outros tipos de ocorrência. “A má conservação da via e o tráfego intenso de veículos na zona industrial, incluindo veículos de transporte de carga e o grande fluxo de entrada e saída de motoristas das fábricas, são as principais causas do alto número de incidentes na Dona Franscisca”, constatou Teixeira Junior. Segundo o tenente, já houve uma redução do índice neste ano em função da adoção de uma política de intensificação de atividades de trânsito na região, mas somente a duplicação e a melhoria da pavimentação do local contribuiriam para uma significativa ação de prevenção a acidentes.\nAlém da falta de segurança na rua Dona Francisca, Jonas Tilp, diretor comercial do Perini Business Park e coordenador do Gestão Compartilhada Norte, aponta que a falta de investimento na via é um entrave econômico para o Distrito Industrial – somente as empresas instaladas no condomínio representam 19% do PIB joinvilense. “Somos o município catarinense que mais arrecada impostos e, contraditoriamente, a falta de investimentos em infraestrutura nas vias urbanas atravanca o desenvolvimento da cidade, por isso periodicamente estamos recebendo autoridades do setor com o intuito de apresentar ao poder público um dossiê dos problemas e das melhorias necessárias na rua Dona Francisca”, afirma Tilp.\nOutros dadosAs estatísticas levantadas pela PM também apontam que a maior parte dos acidentes na rua Dona Francisca ocorre no período da tarde (46%) – 24% durante a manhã, 17% à noite e 12% de madrugada  –, sendo que o horário mais perigoso no trânsito é das 16h às 18h, na saída de muitos funcionários das empresas, quando são registradas 23% das ocorrências. Ainda segundo os dados, o dia da semana em que mais acontecem acidentes é na quinta-feira, com mais de 20% dos registros.\nLevantamento parcialA estimativa da Polícia Militar é que o número de acidentes seja 30% maior do que mostram seus registros, já que alguns acidentes são computados somente pela Polícia Civil e outros nem chegam a ser registrados pelos cidadãos. Em Joinville, não existe um cruzamento de informações entre os diferentes órgãos de trânsito, fato que dificulta a radiografia real dos acidentes que ocorrem na cidade.\nPor: Sara Menezes - EDMlogos";
	}

	private String getExpectedLinkText1() {
		return "\r\n  O Gestão Compartilhada Norte recebe o Tenente Ruy Florêncio Teixeira Junior - Comandante de Transito do 8º Batalhão de Polícia Militar de Joinville\r\n  \r\n Crédito: DivulgaçãoPerini Business Park\r\n \r\n \r\nRua Dona Francisca é recordista em acidentes\r\nSegundo dados da Polícia Militar de Joinville, o Distrito Industrial é o bairro de Joinville com maior número de ocorrências de trânsito com danos materiais, com mais de 200 ocorrências registradas de janeiro a abril deste ano. Deste total, aproximadamente 85 acidentes ocorreram na rua Dona Francisca, seguida de perto pela Avenida Santos Dumont, com 70 sinistros. Esses números foram apresentados pelo tenente Ruy Florêncio Teixeira Junior, comandante do Pelotão de Trânsito de Joinville, durante reunião do grupo de Gestão Compartilhada Norte da Acij, realizada nesta manhã (dia 9), no Perini Business Park.\r\nEstava em debate no evento a questão da duplicação da rua Dona Francisca, entre a rua Edmundo Doubrawa e o trevo da Döehler, uma das mais antigas reivindicações dos empresários da área do Distrito Industrial de Joinville. Somente neste trecho de aproximadamente 6 km foram registrados 164 acidentes em 2012 e 8 até abril de 2013. Do total de 172 acidentes, 106 resultaram em danos materiais, 53 em lesão corporal, uma vítima fatal e 12 outros tipos de ocorrência. “A má conservação da via e o tráfego intenso de veículos na zona industrial, incluindo veículos de transporte de carga e o grande fluxo de entrada e saída de motoristas das fábricas, são as principais causas do alto número de incidentes na Dona Franscisca”, constatou Teixeira Junior. Segundo o tenente, já houve uma redução do índice neste ano em função da adoção de uma política de intensificação de atividades de trânsito na região, mas somente a duplicação e a melhoria da pavimentação do local contribuiriam para uma significativa ação de prevenção a acidentes.\r\nAlém da falta de segurança na rua Dona Francisca, Jonas Tilp, diretor comercial do Perini Business Park e coordenador do Gestão Compartilhada Norte, aponta que a falta de investimento na via é um entrave econômico para o Distrito Industrial – somente as empresas instaladas no condomínio representam 19% do PIB joinvilense. “Somos o município catarinense que mais arrecada impostos e, contraditoriamente, a falta de investimentos em infraestrutura nas vias urbanas atravanca o desenvolvimento da cidade, por isso periodicamente estamos recebendo autoridades do setor com o intuito de apresentar ao poder público um dossiê dos problemas e das melhorias necessárias na rua Dona Francisca”, afirma Tilp.\r\nOutros dadosAs estatísticas levantadas pela PM também apontam que a maior parte dos acidentes na rua Dona Francisca ocorre no período da tarde (46%) – 24% durante a manhã, 17% à noite e 12% de madrugada  –, sendo que o horário mais perigoso no trânsito é das 16h às 18h, na saída de muitos funcionários das empresas, quando são registradas 23% das ocorrências. Ainda segundo os dados, o dia da semana em que mais acontecem acidentes é na quinta-feira, com mais de 20% dos registros.\r\nLevantamento parcialA estimativa da Polícia Militar é que o número de acidentes seja 30% maior do que mostram seus registros, já que alguns acidentes são computados somente pela Polícia Civil e outros nem chegam a ser registrados pelos cidadãos. Em Joinville, não existe um cruzamento de informações entre os diferentes órgãos de trânsito, fato que dificulta a radiografia real dos acidentes que ocorrem na cidade.\r\nPor: Sara Menezes - EDMlogos\r\n  ";
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
		return "Articulação estratégica de empresas, universidade e governo, identifica novas vocações para a economia regional.\nA necessidade de “fazer diferente” existe, para que os indivíduos possam se sobressair, seja na vida pessoal ou no meio corporativo. Assim, a inovação representa ideias, métodos ou objetos que pouco se parecem com os padrões anteriores. Inovação tecnológica seria seria “tudo o que acontece na fronteira do conhecimento”.A 5ª edição da Revista 21 mostra que Joinville caminha a passos largos neste sentido, sendo o primeiro município catarinense a aprovar a Lei da Inovação, e investindo na criação de parques tecnológicos.Acompanhe este processo e fique por dentro das principais novidades e acontecimentos empresariais de Joinville e região, com a versão digital da Revista:\n[ACIJ] Revista 21 - Fevereiro/2013  from Associação Empresarial de Joinville - ACIJ";
	}

	private String getExpectedLinkText2() {
		return "\r\n  Articulação estratégica de empresas, universidade e governo, identifica novas vocações para a economia regional.\r\n  A necessidade de “fazer diferente” existe, para que os indivíduos possam se sobressair, seja na vida pessoal ou no meio corporativo. Assim, a inovação representa ideias, métodos ou objetos que pouco se parecem com os padrões anteriores. Inovação tecnológica seria seria “tudo o que acontece na fronteira do conhecimento”.A 5ª edição da Revista 21 mostra que Joinville caminha a passos largos neste sentido, sendo o primeiro município catarinense a aprovar a Lei da Inovação, e investindo na criação de parques tecnológicos.Acompanhe este processo e fique por dentro das principais novidades e acontecimentos empresariais de Joinville e região, com a versão digital da Revista:\r\n \r\n [ACIJ] Revista 21 - Fevereiro/2013  from Associação Empresarial de Joinville - ACIJ ";
	}
}
