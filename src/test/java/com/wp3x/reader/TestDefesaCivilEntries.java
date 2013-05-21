/**
 * 
 */
package com.wp3x.reader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.wp3x.SourcePattern;
import com.wp3x.SourcePatternFactory;
import com.wp3x.reader.LinkEntry;
import com.wp3x.reader.LinkException;
import com.wp3x.reader.LinkReader;
import com.wp3x.reader.SourceEntry;
import com.wp3x.reader.SourceReader;

/**
 * @author marcos.ferreira
 * 
 */
public class TestDefesaCivilEntries {

    final URL resource = this.getClass().getClassLoader().getResource("defesa-civil-list.htm");
    final URL link1 = this.getClass().getClassLoader().getResource("defesa-civil-n1.htm");
    final URL link2 = this.getClass().getClassLoader().getResource("defesa-civil-n2.htm");
    SourcePattern entryPattern;
    
    @Before
    public void before() throws MalformedURLException{
    	entryPattern = SourcePatternFactory.getDefesaCivilPattern();
    }

    @Test
    public void testGetLinks() throws Exception {
    	entryPattern.setSourceURL(resource);
    	SourceReader newsReader = new SourceReader(entryPattern);
        final Collection<SourceEntry> entries = newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Frente fria traz chuva forte e frio para SC e Fim de semana mar agitado na costa catarinense ", firstEntry.getTitle());
        Assert.assertEquals("12 | ABR | 2013", firstEntry.getDate());
        Assert.assertEquals("/noticia/131-Frente+fria+traz+chuva+forte+e+frio+para+SC+e+Fim+de+semana+mar+agitado+na+costa+catarinense+.html", firstEntry.getUrl());
        Assert.assertEquals("http://defesacivil.joinville.sc.gov.br/noticia/131-Frente+fria+traz+chuva+forte+e+frio+para+SC+e+Fim+de+semana+mar+agitado+na+costa+catarinense+.html", firstEntry.getFormattedURL());
        Assert.assertEquals(1365735600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(10, entries.size());
    }
    
    @Test
    public void testReadLink1() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link1);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText1(), linkEntry.getText());
    	
    	String formatedText = linkEntry.getFormattedText();
    	Assert.assertEquals(getExpectedFormattedLinkText1(), formatedText);
    	
    	String imgPath = linkEntry.getImgPath();
    	String expectedImg = "http://defesacivil.joinville.sc.gov.br/public/portaldefesaadm/imagens/fotos/thumb-b8b96ef656c7ba80443447b49c4260a2.jpg";
    	Assert.assertEquals(expectedImg, imgPath);
    	
    }
    
    private String getExpectedFormattedLinkText1() {
		return "A Secretaria Nacional de Defesa Civil (SEDEC) oferece novamente o Curso de Capacitação Básica em Defesa Civil (3ª edição).\n\nCerca de 2.000 agentes de defesa civil aprenderão sobre os aspectos estruturantes para promover a difusão da cultura de redução de risco de desastres.\n\nPromovido em parceria com o Centro Universitário de Estudos e Pesquisas sobre Desastres (CEPED), da Universidade Federal de Santa Catarina (UFSC), o curso é gratuito e dispõe de um Ambiente Virtual de Ensino-Aprendizagem (AVEA), no qual o aluno tem acesso a todo o conteúdo do curso: 2 teleconferências; videoaulas; 1 livro-texto digital (em formato PDF), além de serviço de tutoria.\n\nO curso aborda os seguintes temas:\n-    Defesa Civil no Brasil;\n-    Sistema Nacional de Defesa Civil;\n-    Ciclo de Gestão de Defesa Civil;\n-    Estudo dos desastres;\n-    Implantação e operacionalização de Coordenadoria Municipal de Defesa Civil.\n\nO curso será oferecido na modalidade de educação a distância (EaD) e tem início previsto para março de 2013. Os interessados podem fazer a inscrição no período de 19/12/2012 até 28 de fevereiro de 2013.\n\nPara fazer sua inscrição acesse: www.defesacivil.cursoscad.ufsc.br/dc/2/";
	}

	private String getExpectedLinkText1() {
		return "\n A Secretaria Nacional de Defesa Civil (SEDEC) oferece novamente o Curso de Capacitação Básica em Defesa Civil (3ª edição).\r\n\r\nCerca de 2.000 agentes de defesa civil aprenderão sobre os aspectos estruturantes para promover a difusão da cultura de redução de risco de desastres.\r\n\r\nPromovido em parceria com o Centro Universitário de Estudos e Pesquisas sobre Desastres (CEPED), da Universidade Federal de Santa Catarina (UFSC), o curso é gratuito e dispõe de um Ambiente Virtual de Ensino-Aprendizagem (AVEA), no qual o aluno tem acesso a todo o conteúdo do curso: 2 teleconferências; videoaulas; 1 livro-texto digital (em formato PDF), além de serviço de tutoria.\r\n\r\nO curso aborda os seguintes temas:\r\n-    Defesa Civil no Brasil;\r\n-    Sistema Nacional de Defesa Civil;\r\n-    Ciclo de Gestão de Defesa Civil;\r\n-    Estudo dos desastres;\r\n-    Implantação e operacionalização de Coordenadoria Municipal de Defesa Civil.\r\n\r\nO curso será oferecido na modalidade de educação a distância (EaD) e tem início previsto para março de 2013. Os interessados podem fazer a inscrição no período de 19/12/2012 até 28 de fevereiro de 2013.\r\n\r\nPara fazer sua inscrição acesse: www.defesacivil.cursoscad.ufsc.br/dc/2/";
	}

	@Test
    public void testReadLink2() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link2);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText2(), linkEntry.getText());
    	String formatedText = linkEntry.getFormattedText();
    	Assert.assertEquals(getExpectedFormattedLinkText2(), formatedText);
    	
    	String imgPath = linkEntry.getImgPath();
    	String expectedImg = "http://defesacivil.joinville.sc.gov.br/public/portaldefesa/imagens/logo-pmj.png";
    	Assert.assertEquals(expectedImg, imgPath);
    }

	private String getExpectedFormattedLinkText2() {
		return "O 10º Fórum Nacional de Defesa Civil já tem data e local definidos. Será em Joinville, no Centreventos Cau Hansen, de 7 a 9 de agosto de 2013. Para receber o evento, representantes da Defesa Civil estadual e municipal se reuniram nesta quinta-feira (25/4) com servidores da Secretaria de Integração e Desenvolvimento, Secretaria de Educação, Fundação Turística, Fundação Cultural e Gabinete do Vice-prefeito para traçar as estratégias da programação.\n\nO fórum é realizado todos os anos pela Secretaria Nacional de Defesa Civil em parceria com o município eleito para sediar o evento e seu respectivo órgão estadual de Proteção e Defesa Civil. Nos três dias de programação está prevista a participação de mais de 3 mil pessoas, entre técnicos e agentes que atuam na defesa civil de suas cidades.\n\nAlém de fortalecer o sistema nacional de proteção e defesa civil, são propostas do fórum divulgar de ações do Governo Federal que visam à melhoria dos processos nos municípios, debater e estimular ações voltadas à defesa civil, promover apresentação de trabalhos e experiências municipais e incentivar ações locais de prevenção e redução de riscos de desastres.\n\nNo dia 14 de maio uma comitiva da Secretaria Nacional de Defesa Civil estará em Joinville para conhecer a cidade e o local do evento e ainda definir os últimos detalhes do fórum. “Todas as ações serão patrocinadas pela Sedec, cabendo ao município a contrapartida de fornecer o local para o evento”, explica Márnio Luiz Pereira, coordenador operacional da Defesa Civil de Joinville.\n\nSECOM";
	}

	private String getExpectedLinkText2() {
		return "\n O 10º Fórum Nacional de Defesa Civil já tem data e local definidos. Será em Joinville, no Centreventos Cau Hansen, de 7 a 9 de agosto de 2013. Para receber o evento, representantes da Defesa Civil estadual e municipal se reuniram nesta quinta-feira (25/4) com servidores da Secretaria de Integração e Desenvolvimento, Secretaria de Educação, Fundação Turística, Fundação Cultural e Gabinete do Vice-prefeito para traçar as estratégias da programação.\r\n\r\nO fórum é realizado todos os anos pela Secretaria Nacional de Defesa Civil em parceria com o município eleito para sediar o evento e seu respectivo órgão estadual de Proteção e Defesa Civil. Nos três dias de programação está prevista a participação de mais de 3 mil pessoas, entre técnicos e agentes que atuam na defesa civil de suas cidades.\r\n\r\nAlém de fortalecer o sistema nacional de proteção e defesa civil, são propostas do fórum divulgar de ações do Governo Federal que visam à melhoria dos processos nos municípios, debater e estimular ações voltadas à defesa civil, promover apresentação de trabalhos e experiências municipais e incentivar ações locais de prevenção e redução de riscos de desastres.\r\n\r\nNo dia 14 de maio uma comitiva da Secretaria Nacional de Defesa Civil estará em Joinville para conhecer a cidade e o local do evento e ainda definir os últimos detalhes do fórum. “Todas as ações serão patrocinadas pela Sedec, cabendo ao município a contrapartida de fornecer o local para o evento”, explica Márnio Luiz Pereira, coordenador operacional da Defesa Civil de Joinville.\r\n\r\nSECOM";
	}
}
