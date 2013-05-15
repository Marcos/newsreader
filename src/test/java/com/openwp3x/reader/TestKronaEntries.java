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
public class TestKronaEntries {

    final URL resource = this.getClass().getClassLoader().getResource("krona-list.htm");
    final URL link1 = this.getClass().getClassLoader().getResource("krona-n1.htm");
    final URL link2 = this.getClass().getClassLoader().getResource("krona-n2.htm");
    SourcePattern entryPattern;
    
    @Before
    public void before() throws MalformedURLException{
    	entryPattern = SourcePatternFactory.getKronaPattern();
    }

    @Test
    public void testGetLinks() throws Exception {
    	entryPattern.setSourceURL(resource);
    	SourceReader newsReader = new SourceReader(entryPattern);
        final Collection<SourceEntry> entries = newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("Maratona De Quatro Jogos Em Joinville", firstEntry.getFormattedTitle());
        Assert.assertEquals("30 Abr 13", firstEntry.getDate());
        Assert.assertEquals("./?area=noticias&id=1251", firstEntry.getUrl());
        Assert.assertEquals("http://www.krona.com.br/kronafutsal/./?area=noticias&id=1251", firstEntry.getFormattedURL());
        Assert.assertEquals(1367290800000L, firstEntry.getDateAsLong().longValue());
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
		return "\n   \n      É dada a largada para o projeto Jovem Pan nas Escolas com as feras da Krona Futsal. A partir desta terça-feira (14/05), os atletas da equipe joinvilense passarão por 12 instituições de ensino de Joinville, entre públicas e privadas, levando a importância da educação por meio do esporte para cerca de 14 mil estudantes, entre 10 e 17 anos. O primeiro colégio visitado foi o da Univille. Revelado na Krona Futsal, o goleiro Dudu representou o tricolor em uma manhã descontraída e muito informativa.A Univille é parceira do projeto Krona Futsal. Desde a temporada 2009, quando a empresa Krona Tubos e Conexões S.A assumiu a gestão do futsal em Joinville, a instituição tem contribuído para o sucesso da equipe, cedendo a estrutura do complexo esportivo para treinos e jogos em alto nível. Já nesta terça-feira (14/05), a Krona Futsal foi até o anfiteatro do colégio para se reunir com os alunos e trocar ideias sobre esporte. “Levar o futsal para os acadêmicos e a importância dos estudos diante da prática esportiva é algo fundamental. O atleta que consegue conciliar a profissão com os estudos sai na frente”, comentou o goleiro Dudu, convocado recentemente para a Seleção Brasileira Sub-21 de Futsal. Durante a visita, o goleiro da Krona Futsal falou sobre a experiência no esporte e a importância da disciplina. Também aproveitou para reforçar a boa fase da Krona na Liga Futsal 2013. A equipe comandada por Fernando Ferretti lidera a competição nacional, com 23 pontos. “Esse carinho que estamos recebendo na cidade é algo único. A temporada tem sido especial e está só começando. Tenho muito orgulho em representar a cidade e atuar ao lado de tantas outras feras, como Tiago, Valdin, Vander Carioca e companhia”, reforçou Dudu. Em cada ação nas escolas serão sorteados brindes da Jovem Pan e também da equipe Krona Futsal. O time joinvilense é o atual campeão catarinense de futsal e conta com a base formada por atletas da Seleção Brasileira de Futsal. Renan PereiraAssessoria de Imprensa Krona FutsalSiga-nos no Twitter: www.twitter.com/kronafutsalTambém estamos no face:  http://www.facebook.com/KronaFutsalJoinville \n";
	}

	private String getExpectedLinkText1() {
		return "\n   \n      É dada a largada para o projeto Jovem Pan nas Escolas com as feras da Krona Futsal. A partir desta terça-feira (14/05), os atletas da equipe joinvilense passarão por 12 instituições de ensino de Joinville, entre públicas e privadas, levando a importância da educação por meio do esporte para cerca de 14 mil estudantes, entre 10 e 17 anos. O primeiro colégio visitado foi o da Univille. Revelado na Krona Futsal, o goleiro Dudu representou o tricolor em uma manhã descontraída e muito informativa.A Univille é parceira do projeto Krona Futsal. Desde a temporada 2009, quando a empresa Krona Tubos e Conexões S.A assumiu a gestão do futsal em Joinville, a instituição tem contribuído para o sucesso da equipe, cedendo a estrutura do complexo esportivo para treinos e jogos em alto nível. Já nesta terça-feira (14/05), a Krona Futsal foi até o anfiteatro do colégio para se reunir com os alunos e trocar ideias sobre esporte. “Levar o futsal para os acadêmicos e a importância dos estudos diante da prática esportiva é algo fundamental. O atleta que consegue conciliar a profissão com os estudos sai na frente”, comentou o goleiro Dudu, convocado recentemente para a Seleção Brasileira Sub-21 de Futsal. Durante a visita, o goleiro da Krona Futsal falou sobre a experiência no esporte e a importância da disciplina. Também aproveitou para reforçar a boa fase da Krona na Liga Futsal 2013. A equipe comandada por Fernando Ferretti lidera a competição nacional, com 23 pontos. “Esse carinho que estamos recebendo na cidade é algo único. A temporada tem sido especial e está só começando. Tenho muito orgulho em representar a cidade e atuar ao lado de tantas outras feras, como Tiago, Valdin, Vander Carioca e companhia”, reforçou Dudu. Em cada ação nas escolas serão sorteados brindes da Jovem Pan e também da equipe Krona Futsal. O time joinvilense é o atual campeão catarinense de futsal e conta com a base formada por atletas da Seleção Brasileira de Futsal. Renan PereiraAssessoria de Imprensa Krona FutsalSiga-nos no Twitter: www.twitter.com/kronafutsalTambém estamos no face:  http://www.facebook.com/KronaFutsalJoinville \n";
	}

	@Test
    public void testReadLink2() throws MalformedURLException, LinkException{
    	LinkReader linkReader = new LinkReader(entryPattern, link2);
    	LinkEntry linkEntry = linkReader.getLinkEntry();
    	Assert.assertEquals(getExpectedLinkText2(), linkEntry.getLinkText());
    	Assert.assertEquals(getExpectedFormattedLinkText2(), linkEntry.getFormattedLinkText());
    }

	private String getExpectedFormattedLinkText2() {
		return "\n   \n      A Krona Futsal segue na liderança da Liga Futsal 2013. Nesta segunda-feira (13/05), o time joinvilense goleou a Assoeva/Venâncio Aires, por 5 a 0, no Ginásio da Univille, em Joinville (SC). Agora, soma nove jogos de invencibilidade na competição nacional, com 23 pontos. Os gols da equipe foram marcados por Vander Carioca (2), Deives (2) e Dyego.Pela primeira vez jogando oficialmente no Ginásio da Univille em 2013, a Krona Futsal não tomou reconhecimento dos visitantes e construiu o resultado positivo com superioridade. A equipe abriu o placar aos oito minutos, no voleio certeiro do pivô Vander Carioca, artilheiro da Liga Futsal 2013. Depois do gol, a partida ficou ainda mais ofensiva. O time joinvilense teve a chance de marcar o segundo com o pivô Elisandro, mas a bola saiu pela linha de fundo. Valdin também arriscou, em nova investida ao ataque. Desta vez, Basílio espalmou para escanteio. Já a Assoeva tentou o gol aos 12 minutos. Thiaguinho bateu de fora da área e Tiago pegou. Em seguida, Rômulo recebeu passe dentro da área, mas o goleiro da Krona Futsal evitou a reação do clube de Venâncio Aires (RS).A rede voltou a balançar ainda na primeira etapa. Em cobrança ensaiada de escanteio, no oportunismo, Vander Carioca ampliou a contagem para Krona Futsal. O tricolor levou o resultado para o intervalo, confirmando a vitória na etapa final. Aos 12 minutos do segundo tempo, Valdin arrancou em velocidade e encontrou Dyego, livre de marcação. O ala da Krona concluiu na saída do goleiro, marcando o terceiro dos joinvilenses. Depois do gol, a Assoeva passou a arriscar com o goleiro-linha, mas quem levou a melhor foi a Krona Futsal. Bem postada na marcação e efetiva no ataque, a Krona Futsal chegou ainda ao quarto e quinto gol, ambos anotados pelo pivô Deives. Placar final: Krona Futsal 5 x 0 Assoeva. O tricolor encerra assim a maratona de quatro jogos seguidos em Joinville. O próximo compromisso ocorre no sábado (18/05), contra o Guarapuava, no interior do Paraná. Gols da Krona Futsal: Vander Carioca (2), Deives (2) e Dyego.Renan PereiraAssessoria de Imprensa Krona FutsalSiga-nos no Twitter: www.twitter.com/kronafutsalTambém estamos no face:  http://www.facebook.com/KronaFutsalJoinville\n";
	}

	private String getExpectedLinkText2() {
		return "\n   \n      A Krona Futsal segue na liderança da Liga Futsal 2013. Nesta segunda-feira (13/05), o time joinvilense goleou a Assoeva/Venâncio Aires, por 5 a 0, no Ginásio da Univille, em Joinville (SC). Agora, soma nove jogos de invencibilidade na competição nacional, com 23 pontos. Os gols da equipe foram marcados por Vander Carioca (2), Deives (2) e Dyego.Pela primeira vez jogando oficialmente no Ginásio da Univille em 2013, a Krona Futsal não tomou reconhecimento dos visitantes e construiu o resultado positivo com superioridade. A equipe abriu o placar aos oito minutos, no voleio certeiro do pivô Vander Carioca, artilheiro da Liga Futsal 2013. Depois do gol, a partida ficou ainda mais ofensiva. O time joinvilense teve a chance de marcar o segundo com o pivô Elisandro, mas a bola saiu pela linha de fundo. Valdin também arriscou, em nova investida ao ataque. Desta vez, Basílio espalmou para escanteio. Já a Assoeva tentou o gol aos 12 minutos. Thiaguinho bateu de fora da área e Tiago pegou. Em seguida, Rômulo recebeu passe dentro da área, mas o goleiro da Krona Futsal evitou a reação do clube de Venâncio Aires (RS).A rede voltou a balançar ainda na primeira etapa. Em cobrança ensaiada de escanteio, no oportunismo, Vander Carioca ampliou a contagem para Krona Futsal. O tricolor levou o resultado para o intervalo, confirmando a vitória na etapa final. Aos 12 minutos do segundo tempo, Valdin arrancou em velocidade e encontrou Dyego, livre de marcação. O ala da Krona concluiu na saída do goleiro, marcando o terceiro dos joinvilenses. Depois do gol, a Assoeva passou a arriscar com o goleiro-linha, mas quem levou a melhor foi a Krona Futsal. Bem postada na marcação e efetiva no ataque, a Krona Futsal chegou ainda ao quarto e quinto gol, ambos anotados pelo pivô Deives. Placar final: Krona Futsal 5 x 0 Assoeva. O tricolor encerra assim a maratona de quatro jogos seguidos em Joinville. O próximo compromisso ocorre no sábado (18/05), contra o Guarapuava, no interior do Paraná. Gols da Krona Futsal: Vander Carioca (2), Deives (2) e Dyego.Renan PereiraAssessoria de Imprensa Krona FutsalSiga-nos no Twitter: www.twitter.com/kronafutsalTambém estamos no face:  http://www.facebook.com/KronaFutsalJoinville\n";
	}
    
}
