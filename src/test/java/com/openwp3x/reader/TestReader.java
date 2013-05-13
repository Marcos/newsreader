/**
 * 
 */
package com.openwp3x.reader;

import java.net.URL;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class TestReader {

	final URL resource1 = this.getClass().getClassLoader().getResource("udesc-n1.htm");
    Reader newsReader;

    @Before
    public void beforeTest() throws Throwable{
    	SourcePattern entryPattern = SourcePatternFactory.getUdescPattern();
    	entryPattern.setSourceURL(resource1);
        this.newsReader = new Reader(resource1, entryPattern.getSource(), entryPattern.getSourceType(), entryPattern.getCharset());
    }
    
    @Test
    public void testGetTitle() throws Exception {
        String entry = "";
        entry = this.newsReader.getFormattedTitle( "//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[1]/p[2]");
        Assert.assertEquals("Programa \"Extensão Udesc\" estreia na Rádio Udesc (91.9 FM)", entry);
    }
    
    @Test
    public void testGetDateContent() throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        final String datePattern = "\\d{2}/\\d{2}/\\d{4}";
        String date = newsReader.getDateContent("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[1]/p", datePattern);
        Assert.assertEquals("10/04/2013", date);
    }

    @Test
    public void testGetText() throws Exception {
        String entry = this.newsReader.getTextContent("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[1]");
        Assert.assertEquals(this.getNewText(), entry);
    }

    private String getNewText() {
		return "\nJoinville, SC - 10/04/2013\n\nPrograma \"Extensão Udesc\" estreia na Rádio Udesc (91.9 FM) \n\n\n\n\nUma série de debates sobre a atuação dos Conselhos Tutelares e dos Conselhos de Direitos da Criança e Adolescente marca a estreia do programa Extensão Udesc, na próxima segunda-feira,15, às 13h30, na Rádio Udesc FM (91,9MHz e pelo site da rádio).\r\n\r\nO programa vai abordar as experiências do Gradcia (Grupo Regional de Articulação de Ações em Defesa dos Direitos das Crianças e dos Adolescentes), formado por conselheiros tutelares e agentes sociais dos municípios das regiões da Amunesc e Amvali.\r\n\r\nA partir de 2002, o Gradcia passou a receber o suporte do Gepes (Grupo de Estudos em Políticas Educacionais e Sociais), que nasceu de um projeto de extensão desenvolvido na Udesc Joinville.\r\nPara a presidente do Gepes, professora Jurema Iara Reis Belli, o programa será uma forma de esclarecer sobre a função dos conselhos tutelares e de direito. Os conselheiros enfrentam muitas dificuldades para atuar em virtude de muitas pessoas e órgãos públicos ainda desconhecerem o papel dos conselhos na sociedade, diz.\r\n\r\nO programa de estreia terá a presença das conselheiras tutelares de São Francisco do Sul, Tânia Maria de Miranda e Rosmari Fátima Costa, com a participação do pastor Euclides Luiz do Amaral, de Jaraguá do Sul e da integrante do Gepes/Gradcia, Lourdes Salete de Aquino. O \"Extensão Udesc\" será apresentado pelo jornalista Sergio Luis e reprisado aos sábados, às 15h.\r\n\n\nAssessoria de Imprensa Udesc Joinville - \r\n    \r\n     \r\n    \n\nJornalista: Isabela Vargas (SC 01915 JP)\n\n\n          Compartilhar: ";
	}

	@Test
    public void testClearText(){
    	String dirtText = "\nJoinville, SC - 10/04/2013\n\nPrograma &quot;Extens&atilde;o Udesc&quot; estreia na R&aacute;dio Udesc (91.9 FM) \n\n\n\n\nUma s&eacute;rie de debates sobre a atua&ccedil;&atilde;o dos Conselhos Tutelares e dos Conselhos de Direitos da Crian&ccedil;a e Adolescente marca a estreia do programa Extens&atilde;o Udesc, na pr&oacute;xima segunda-feira,15, &agrave;s 13h30, na R&aacute;dio Udesc FM (91,9MHz e pelo site da r&aacute;dio).\r\n\r\nO programa vai abordar as experi&ecirc;ncias do Gradcia (Grupo Regional de Articula&ccedil;&atilde;o de A&ccedil;&otilde;es em Defesa dos Direitos das Crian&ccedil;as e dos Adolescentes), formado por conselheiros tutelares e agentes sociais dos munic&iacute;pios das regi&otilde;es da Amunesc e Amvali.\r\n\r\nA partir de 2002, o Gradcia passou a receber o suporte do Gepes (Grupo de Estudos em Pol&iacute;ticas Educacionais e Sociais), que nasceu de um projeto de extens&atilde;o desenvolvido na Udesc Joinville.\r\nPara a presidente do Gepes, professora Jurema Iara Reis Belli, o programa ser&aacute; uma forma de esclarecer sobre a fun&ccedil;&atilde;o dos conselhos tutelares e de direito. Os conselheiros enfrentam muitas dificuldades para atuar em virtude de muitas pessoas e &oacute;rg&atilde;os p&uacute;blicos ainda desconhecerem o papel dos conselhos na sociedade, diz.\r\n\r\nO programa de estreia ter&aacute; a presen&ccedil;a das conselheiras tutelares de S&atilde;o Francisco do Sul, T&acirc;nia Maria de Miranda e Rosmari F&aacute;tima Costa, com a participa&ccedil;&atilde;o do pastor Euclides Luiz do Amaral, de Jaragu&aacute; do Sul e da integrante do Gepes/Gradcia, Lourdes Salete de Aquino. O &quot;Extens&atilde;o Udesc&quot; ser&aacute; apresentado pelo jornalista Sergio Luis e reprisado aos s&aacute;bados, &agrave;s 15h.\r\n\n\nAssessoria de Imprensa Udesc Joinville - \r\n    \r\n     \r\n    \n\nJornalista: Isabela Vargas (SC 01915 JP)\n\n\n          Compartilhar: ";
    	String startPattern = "\\n{5}";
    	String endPattern = "Compartilhar: ";
    	String cleanedText = this.newsReader.clearText(dirtText, startPattern, endPattern);
    	String exptectedText = getExpectedCleanedText();
    	Assert.assertEquals(exptectedText, cleanedText);
    }

    private String getExpectedCleanedText() {
		return "Uma s&eacute;rie de debates sobre a atua&ccedil;&atilde;o dos Conselhos Tutelares e dos Conselhos de Direitos da Crian&ccedil;a e Adolescente marca a estreia do programa Extens&atilde;o Udesc, na pr&oacute;xima segunda-feira,15, &agrave;s 13h30, na R&aacute;dio Udesc FM (91,9MHz e pelo site da r&aacute;dio).\r\n\r\nO programa vai abordar as experi&ecirc;ncias do Gradcia (Grupo Regional de Articula&ccedil;&atilde;o de A&ccedil;&otilde;es em Defesa dos Direitos das Crian&ccedil;as e dos Adolescentes), formado por conselheiros tutelares e agentes sociais dos munic&iacute;pios das regi&otilde;es da Amunesc e Amvali.\r\n\r\nA partir de 2002, o Gradcia passou a receber o suporte do Gepes (Grupo de Estudos em Pol&iacute;ticas Educacionais e Sociais), que nasceu de um projeto de extens&atilde;o desenvolvido na Udesc Joinville.\r\nPara a presidente do Gepes, professora Jurema Iara Reis Belli, o programa ser&aacute; uma forma de esclarecer sobre a fun&ccedil;&atilde;o dos conselhos tutelares e de direito. Os conselheiros enfrentam muitas dificuldades para atuar em virtude de muitas pessoas e &oacute;rg&atilde;os p&uacute;blicos ainda desconhecerem o papel dos conselhos na sociedade, diz.\r\n\r\nO programa de estreia ter&aacute; a presen&ccedil;a das conselheiras tutelares de S&atilde;o Francisco do Sul, T&acirc;nia Maria de Miranda e Rosmari F&aacute;tima Costa, com a participa&ccedil;&atilde;o do pastor Euclides Luiz do Amaral, de Jaragu&aacute; do Sul e da integrante do Gepes/Gradcia, Lourdes Salete de Aquino. O &quot;Extens&atilde;o Udesc&quot; ser&aacute; apresentado pelo jornalista Sergio Luis e reprisado aos s&aacute;bados, &agrave;s 15h.\r\n\n\nAssessoria de Imprensa Udesc Joinville - \r\n    \r\n     \r\n    \n\nJornalista: Isabela Vargas (SC 01915 JP)\n\n\n          ";
	}
    
    @Test
    public void testFindDateInText() {
        final String text = "Joinville, SC - 10/04/2013";
        final String datePatern = "\\d{2}/\\d{2}/\\d{4}";
        Assert.assertEquals("10/04/2013", this.newsReader.getDateFromText(text, datePatern));
    }

    @Test
    public void testCleanerStart() {
        final String dirtText = "\n   18/04/13 - Esta semana tem Café & Negócios na Ajorpeme";
        String treatPrefixPattern = "\\.*\\s-\\s";
        final String cleanedText = new SourceEntry().treatPrefixPattern(dirtText, treatPrefixPattern);
        Assert.assertEquals(cleanedText, "Esta semana tem Café & Negócios na Ajorpeme");
    }

    @Test
    public void testInfoPattern() {
        final String dirtText = "viewRegistro(13680);return false;";
        final Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(dirtText);
        matcher.find();
        Assert.assertEquals(matcher.group(), "13680");

        final String dirtText2 = "viewRegistro(1368088);return false;";
        matcher = pattern.matcher(dirtText2);
        matcher.find();
        Assert.assertEquals(matcher.group(), "1368088");

        final String dirtText3 = "viewRegistro(1368088);return 123;";
        matcher = pattern.matcher(dirtText3);
        matcher.find();
        final String cleanedText = matcher.group();
        Assert.assertEquals(cleanedText, "1368088");

        final String dirtText4 = "viewRegistro(1);return false;";
        matcher = pattern.matcher(dirtText4);
        matcher.find();
        Assert.assertEquals(matcher.group(), "1");
    }
    
    @Test
    public void testDateFormat() {
        System.out.println(new SourceEntry().getDateAsLong("17 | ABR | 2013", new Locale("pt", "BR"), "dd | MMM | yyyy"));
    }
    
}
