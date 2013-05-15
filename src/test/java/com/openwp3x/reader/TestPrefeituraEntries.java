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

import com.openwp3x.SourceEntry;
import com.openwp3x.SourcePattern;
import com.openwp3x.SourcePatternFactory;

/**
 * @author marcos.ferreira
 * 
 */
public class TestPrefeituraEntries {

    final URL resource = this.getClass().getClassLoader().getResource("prefeitura-list.htm");
    final URL link1 = this.getClass().getClassLoader().getResource("prefeitura-n1.htm");
    final URL link2 = this.getClass().getClassLoader().getResource("prefeitura-n2.htm");
    SourcePattern entryPattern;
    
    @Before
    public void before() throws MalformedURLException{
    	entryPattern = SourcePatternFactory.getPrefeituraPattern();
    }

    @Test
    public void testGetLinks() throws Exception {
    	entryPattern.setSourceURL(resource);
    	SourceReader newsReader = new SourceReader(entryPattern);
        final Collection<SourceEntry> entries = newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals(firstEntry.getTitle(), "Fundação 25 de Julho e Secretaria de Desenvolvimento Econômico visitam produtores rurais");
        Assert.assertEquals(firstEntry.getDate(), "17 | ABR | 2013");
        Assert.assertEquals(firstEntry.getUrl(), "/noticia/4202-Funda%C3%A7%C3%A3o+25+de+Julho+e+Secretaria+de+Desenvolvimento+Econ%C3%B4mico+visitam+produtores+rurais.html");
        Assert.assertEquals(firstEntry.getFormattedURL(), "http://www.joinville.sc.gov.br/noticia/4202-Funda%C3%A7%C3%A3o+25+de+Julho+e+Secretaria+de+Desenvolvimento+Econ%C3%B4mico+visitam+produtores+rurais.html");
        Assert.assertEquals(firstEntry.getDateAsLong().longValue(), 1366167600000L);
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
		return "\n Neste domingo (5/5), a Escola Municipal Max Colin será palco do 1º Trick Attack, campeonato que reunirá mais de 150 skatistas de Joinville. O evento, organizado pela Associação Joinvilense de Skate, acontecerá a partir das 8 horas. “Conseguimos uma parceria com a escola, através da Coordenação Municipal da Juventude. Em Joinville, o skate fica restrito ao Parque da Cidade, mas desta forma conseguimos levar o esporte aos bairros e para mais perto da população”, conta o vice-presidente da Associação, Fabrício Correa.\r\n\r\nO campeonato será dividido entre as categorias mirim, iniciante, amador 2, amador 1 e feminino e os participantes precisarão pagar uma taxa de inscrição, no dia do evento. O 1º Trick Attack contará pontos para o ranking do circuito de competições da Associação Joinvilense de Skate, que prevê outros dois eventos ainda para este ano. Ao final dos três campeonatos, os melhores colocados em cada categoria serão premiados. \r\n\r\nA Escola Municipal Max Colin fica na Rua Pasteur, nº 1.079, bairro Iririú. O evento é aberto à comunidade e a entrada é um quilo de alimento não perecível. \r\n \r\nTaxa de inscrição para os participantes do 1º Trick Attack:\r\n- Categoria Mirim: R$8,00.\r\n- Categoria Iniciante: R$10,00.\r\n- Categoria Amador 2: R$15,00.\r\n- Categoria Amador 1: R$18,00.\r\n- Categoria Feminino: R$10,00.\r\n";
	}

	private String getExpectedLinkText1() {
		return "\n Neste domingo (5/5), a Escola Municipal Max Colin será palco do 1º Trick Attack, campeonato que reunirá mais de 150 skatistas de Joinville. O evento, organizado pela Associação Joinvilense de Skate, acontecerá a partir das 8 horas. “Conseguimos uma parceria com a escola, através da Coordenação Municipal da Juventude. Em Joinville, o skate fica restrito ao Parque da Cidade, mas desta forma conseguimos levar o esporte aos bairros e para mais perto da população”, conta o vice-presidente da Associação, Fabrício Correa.\r\n\r\nO campeonato será dividido entre as categorias mirim, iniciante, amador 2, amador 1 e feminino e os participantes precisarão pagar uma taxa de inscrição, no dia do evento. O 1º Trick Attack contará pontos para o ranking do circuito de competições da Associação Joinvilense de Skate, que prevê outros dois eventos ainda para este ano. Ao final dos três campeonatos, os melhores colocados em cada categoria serão premiados. \r\n\r\nA Escola Municipal Max Colin fica na Rua Pasteur, nº 1.079, bairro Iririú. O evento é aberto à comunidade e a entrada é um quilo de alimento não perecível. \r\n \r\nTaxa de inscrição para os participantes do 1º Trick Attack:\r\n- Categoria Mirim: R$8,00.\r\n- Categoria Iniciante: R$10,00.\r\n- Categoria Amador 2: R$15,00.\r\n- Categoria Amador 1: R$18,00.\r\n- Categoria Feminino: R$10,00.\r\n";
	}

	@Test
    public void testReadLink2() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link2);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText2(), linkEntry.getLinkText());
    	Assert.assertEquals(getExpectedFormattedLinkText2(), linkEntry.getFormattedLinkText());
    }

	private String getExpectedFormattedLinkText2() {
		return "\n Perfurações para concretagem das fundações nos terrenos que abrigarão duas praças fazem parte dos trabalhos que estão sendo executados no local do futuro Parque Porta do Mar, no bairro Espinheiros. A Belga Empreiteira Ltda, empresa que executa a obra, também está fabricando em sua sede a estrutura pré-moldada para o trapiche público.\r\n\r\nO projeto do Parque Porta do Mar inclui a construção e alargamento dos passeios da orla da rua Antônio Gonçalves, construção do trapiche público, com 120 metros de extensão e equipado com deck flutuante, construção da praça com monumento “Porta do Mar”, construção de praça com Academia da Melhor Idade, nova iluminação e novo mobiliário urbano.\r\n\r\nA obra, no valor de R$ 1.382.829,23, terá prazo para ser  concluída em seis meses.";
	}

	private String getExpectedLinkText2() {
		return "\n Perfurações para concretagem das fundações nos terrenos que abrigarão duas praças fazem parte dos trabalhos que estão sendo executados no local do futuro Parque Porta do Mar, no bairro Espinheiros. A Belga Empreiteira Ltda, empresa que executa a obra, também está fabricando em sua sede a estrutura pré-moldada para o trapiche público.\r\n\r\nO projeto do Parque Porta do Mar inclui a construção e alargamento dos passeios da orla da rua Antônio Gonçalves, construção do trapiche público, com 120 metros de extensão e equipado com deck flutuante, construção da praça com monumento “Porta do Mar”, construção de praça com Academia da Melhor Idade, nova iluminação e novo mobiliário urbano.\r\n\r\nA obra, no valor de R$ 1.382.829,23, terá prazo para ser  concluída em seis meses.";
	}

   
}
