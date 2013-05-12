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

import com.openwp3x.EntryImpl;
import com.openwp3x.EntryPattern;
import com.openwp3x.EntryPatternFactory;
import com.openwp3x.EntryReader;

/**
 * @author marcos.ferreira
 * 
 */
public class TestUdescEntries {

    final URL resource = this.getClass().getClassLoader().getResource("udesc-list.htm");
    final URL link1 = this.getClass().getClassLoader().getResource("udesc-n1.htm");
    final URL link2 = this.getClass().getClassLoader().getResource("udesc-n2.htm");
    EntryPattern entryPattern;
    
    @Before
    public void before() throws MalformedURLException{
    	entryPattern = EntryPatternFactory.getUdescPattern();
    }
    
    
    @Test
    public void testGetLinks() throws Exception {
    	entryPattern.setSourceURL(resource);
    	EntryReader newsReader = new EntryReader(entryPattern);
        final Collection<EntryImpl> entries = newsReader.getEntries();
        
        final EntryImpl firstEntry = entries.iterator().next();
        Assert.assertEquals("Termina neste domingo prazo para submissão de trabalhos no Encontro Catarinense de LibreOffice", firstEntry.getFormattedTitle());
        Assert.assertEquals("17/04/2013", firstEntry.getDate());
        Assert.assertEquals("index.php?id=5160&pg=1", firstEntry.getUrl());
        Assert.assertEquals("http://www.joinville.udesc.br/portal/noticias/index.php?id=5160&pg=1", firstEntry.getFormattedURL());
        Assert.assertEquals(1366167600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(20, entries.size());
    }
    
    @Test
    public void testReadLink1() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link1);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText1(), linkEntry.getLinkText());
    	Assert.assertEquals(getExpectedFormattedLinkText1(), linkEntry.getFormattedLinkText());
    }
    
    @Test
    public void testReadLink2() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link2);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText2(), linkEntry.getLinkText());
    	Assert.assertEquals(getExpectedFormattedLinkText2(), linkEntry.getFormattedLinkText());
    }

	private String getExpectedFormattedLinkText2() {
		return "\n\nA Universidade do Estado de Santa Catarina (Udesc) anunciou nesta quinta-feira, 9, que as inscrições para o Vestibular de Inverno 2013 serão realizadas de 21 de maio a 18 de junho por meio do site http://www.vestibular.udesc.br/.\r\n\r\nAs provas serão aplicadas em 7 de julho nas cidades de Florianópolis, Joinville, Lages, Ibirama, São Bento do Sul, Chapecó, Laguna e Balneário Camboriú. O resultado será divulgado até 17 de julho.\r\n\r\nA coordenadora do Vestibular da Udesc, Rosângela de Souza Machado, informa ainda que o edital com o número de vagas e opções de cursos será divulgado até 21 de maio.\r\n\r\nOutras informações podem ser obtidas pelo e-mail vestiba@udesc.br ou com a Coordenadoria do Vestibular pelo telefone (48) 3321-8098, de segunda a sexta-feira, das 13h às 19h.\r\n\r\n--\r\n\r\nAssessoria de Comunicação da Udesc\r\nJornalista Thiago Augusto\r\nE-mail: thiago.augusto@udesc.br\r\nFone: (48) 3321-8142 ";
	}

	private String getExpectedLinkText2() {
		return "\n\nA Universidade do Estado de Santa Catarina (Udesc) anunciou nesta quinta-feira, 9, que as inscrições para o Vestibular de Inverno 2013 serão realizadas de 21 de maio a 18 de junho por meio do site http://www.vestibular.udesc.br/.\r\n\r\nAs provas serão aplicadas em 7 de julho nas cidades de Florianópolis, Joinville, Lages, Ibirama, São Bento do Sul, Chapecó, Laguna e Balneário Camboriú. O resultado será divulgado até 17 de julho.\r\n\r\nA coordenadora do Vestibular da Udesc, Rosângela de Souza Machado, informa ainda que o edital com o número de vagas e opções de cursos será divulgado até 21 de maio.\r\n\r\nOutras informações podem ser obtidas pelo e-mail vestiba@udesc.br ou com a Coordenadoria do Vestibular pelo telefone (48) 3321-8098, de segunda a sexta-feira, das 13h às 19h.\r\n\r\n--\r\n\r\nAssessoria de Comunicação da Udesc\r\nJornalista Thiago Augusto\r\nE-mail: thiago.augusto@udesc.br\r\nFone: (48) 3321-8142 ";
	}

	private String getExpectedFormattedLinkText1() {
		return "\n\nUma série de debates sobre a atuação dos Conselhos Tutelares e dos Conselhos de Direitos da Criança e Adolescente marca a estreia do programa Extensão Udesc, na próxima segunda-feira,15, às 13h30, na Rádio Udesc FM (91,9MHz e pelo site da rádio).\r\n\r\nO programa vai abordar as experiências do Gradcia (Grupo Regional de Articulação de Ações em Defesa dos Direitos das Crianças e dos Adolescentes), formado por conselheiros tutelares e agentes sociais dos municípios das regiões da Amunesc e Amvali.\r\n\r\nA partir de 2002, o Gradcia passou a receber o suporte do Gepes (Grupo de Estudos em Políticas Educacionais e Sociais), que nasceu de um projeto de extensão desenvolvido na Udesc Joinville.\r\nPara a presidente do Gepes, professora Jurema Iara Reis Belli, o programa será uma forma de esclarecer sobre a função dos conselhos tutelares e de direito. Os conselheiros enfrentam muitas dificuldades para atuar em virtude de muitas pessoas e órgãos públicos ainda desconhecerem o papel dos conselhos na sociedade, diz.\r\n\r\nO programa de estreia terá a presença das conselheiras tutelares de São Francisco do Sul, Tânia Maria de Miranda e Rosmari Fátima Costa, com a participação do pastor Euclides Luiz do Amaral, de Jaraguá do Sul e da integrante do Gepes/Gradcia, Lourdes Salete de Aquino. O \"Extensão Udesc\" será apresentado pelo jornalista Sergio Luis e reprisado aos sábados, às 15h.\r\n";
	}

	private String getExpectedLinkText1() {
		return "\n\nUma série de debates sobre a atuação dos Conselhos Tutelares e dos Conselhos de Direitos da Criança e Adolescente marca a estreia do programa Extensão Udesc, na próxima segunda-feira,15, às 13h30, na Rádio Udesc FM (91,9MHz e pelo site da rádio).\r\n\r\nO programa vai abordar as experiências do Gradcia (Grupo Regional de Articulação de Ações em Defesa dos Direitos das Crianças e dos Adolescentes), formado por conselheiros tutelares e agentes sociais dos municípios das regiões da Amunesc e Amvali.\r\n\r\nA partir de 2002, o Gradcia passou a receber o suporte do Gepes (Grupo de Estudos em Políticas Educacionais e Sociais), que nasceu de um projeto de extensão desenvolvido na Udesc Joinville.\r\nPara a presidente do Gepes, professora Jurema Iara Reis Belli, o programa será uma forma de esclarecer sobre a função dos conselhos tutelares e de direito. Os conselheiros enfrentam muitas dificuldades para atuar em virtude de muitas pessoas e órgãos públicos ainda desconhecerem o papel dos conselhos na sociedade, diz.\r\n\r\nO programa de estreia terá a presença das conselheiras tutelares de São Francisco do Sul, Tânia Maria de Miranda e Rosmari Fátima Costa, com a participação do pastor Euclides Luiz do Amaral, de Jaraguá do Sul e da integrante do Gepes/Gradcia, Lourdes Salete de Aquino. O \"Extensão Udesc\" será apresentado pelo jornalista Sergio Luis e reprisado aos sábados, às 15h.\r\n";
	}
}
