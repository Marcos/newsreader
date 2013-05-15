/**
 * 
 */
package com.openwp3x.reader;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.openwp3x.SourcePattern;
import com.openwp3x.SourcePatternFactory;

/**
 * @author marcos.ferreira
 * 
 */
public class TestNDEntries {

    final URL resource = this.getClass().getClassLoader().getResource("nd-joinville-list.htm");
    SourceReader newsReader;

    @Before
    public void beforeTest() throws IOException, ParserConfigurationException {
    	SourcePattern entryPattern = SourcePatternFactory.getNDJoinvillePattern();
    	entryPattern.setSourceURL(resource);
    	this.newsReader = new SourceReader(entryPattern);
    }

    @Test
    public void testGetLinks() throws Exception {
        final Collection<SourceEntry> entries = this.newsReader.getEntries();
        System.out.println(entries);
        final SourceEntry firstEntry = entries.iterator().next();
        Assert.assertEquals("O arauto da Tapera", firstEntry.getTitle());
        Assert.assertEquals("22 Apr 2013", firstEntry.getDate());
        Assert.assertEquals("http://www.ndonline.com.br/joinville/noticias/65628-o-arauto-da-tapera.html", firstEntry.getUrl());
        Assert.assertEquals("http://www.ndonline.com.br/joinville/noticias/65628-o-arauto-da-tapera.html", firstEntry.getFormattedURL());
        Assert.assertEquals(1366599600000L, firstEntry.getDateAsLong().longValue());
        Assert.assertEquals(getText(), firstEntry.getText());
        Assert.assertEquals(10, entries.size());
    }

	private String getText() {
		return "<img src='http://www.ndonline.com.br//uploads/global/materias/2013/04/21-04-2013-17-09-33-perfil-cirenio-engenho-de-farinha-by-luciano-moraes-48-.jpg'><br>\n\n\r\nFotos Luciano Moraes/ND\r\n\r\nMedicinal. Seu Cireno colhe macela e faz travesseiros\r\n\r\n\r\n\r\nO nome dele é Cirenio Antonio Madeira. Porém, como todo francisquense que se preza, tem apelido; dois até: “Sereno, porque ando muito devagar. Também tem quem me conheça como Baú, por causa da corcunda de tanto trabalhar”. Trabalho é o que não falta no cotidiano do Sereno, seja no engenho de farinha de mandioca, um dos últimos da região ainda em atividade; seja na produção de travesseiros forrados com flor de macela, santo remédio para constipação das vias aéreas; seja na paciente atualização do diário onde são registrados todos os óbitos do bairro da Tapera.\r\n“Anoto todos que morreram desde Posidônio Maciel, em 1949. Ele foi levado dentro de um caixão roxo, num coche preto puxado por um cavalo vermelho”, recita o diarista, demonstrando uma de suas grandes virtudes, a memória afiadíssima. Outra marca registrada é o arrastado e sincero “tuuuudo bem...”, com que arremata grande parte das frases.\r\nDetalhe: aquele primeiro registro foi feito quando ele tinha apenas 4 anos de idade. “Minha mãe me ensinou a ler e escrever em papel de embrulho, daquele que ficava em rolos, no balcão da venda.” Sereno devia ser um aluno dos mais aplicados, tanto em casa, com a mãe-professora, quanto no colégio Capitão Zeferino Évora Rosa, onde estudou apenas até a 3ª série primária. É só ver a letra ainda caprichada nos seus cadernos-diários-obituários. Ele ainda guarda vários objetos do tempo da escola, como uma maleta de metal, ganha em 1957, e um estojo de madeira – também conhecido como penal – onde estão lápis, borracha e caneta, tudo bem conservadinho. “A escola era rigorosa naquele tempo, e aprendi muito com o professor Altino Zuzak”, conta Sereno, certo de que, atualmente, falta mais disciplina às crianças.\r\n\r\nConstrutor aos 14 anosCirenio Antonio Madeira nasceu no dia 17 de abril de 1945, ali mesmo na Tapera. Primeiro de uma prole de 16 filhos, de dois casamentos da mãe, mal conheceu o pai, Odorico, um estivador que morreu quando Sereno ainda era novinho. Ele se orgulha das raízes açorianas: “Meus avôs, dos dois lados, vieram dos Açores”. Seu segundo nome, por sinal é uma homenagem aos avôs, Antonio Madeira e Antonio da Conceição, pescador conhecido como Chumbada.\r\n“Minha mãe trabalhava na roça e casou-se de novo alguns anos depois de enviuvar do meu pai. Mas eu não gostava do meu padrasto, que bebia muito e era violento.” Devido ao clima pesado em casa, Sereno achou melhor sair e viver sozinho. Isso com 12 anos! “Eu tinha 14 anos quando levantei minha primeira casa, um rancho de três metros e 80 de frente por seis de fundos. A madeira eu buscava na praia Grande. Ia de canoa pelo rio Acaraí até o Casqueiro e pegava a madeira que ficava jogada na praia.” Nos fundos da atual propriedade, atrás do engenho de farinha, Sereno reproduziu o rancho, onde chegou a morar dois anos, antes de construir a casa onde vive hoje. “Eu dormia ali, na tarimba”, conta, apontando para uma tosca cama de madeira, que forrava com o que estivesse a mão para substituir o colchão.\r\n\r\n\r\n\r\nEngenho. Cireno planta e colhe a mandioca que vai transformar em farinha\r\n\r\n\r\n\r\nMoendo mandiocaSereno tinha 20 anos quando adquiriu o engenho de farinha de mandioca. “Eu tinha antes um comércio, do tipo venda e bar. Mas fechei, porque não suportava os bêbados. Já bastava o padrasto. Tuuuuudo bem...”\r\nCaminhando pela plantação, Sereno esclarece uma dúvida do repórter: “Aqui, na esquerda, é mandioca, que tem a folha mais larga e serve pra fazer farinha. A plantação da direita é de aipim, aquele que a gente cozinha ou frita para acompanhar a refeição”. A porta do rancho onde funciona o engenho parece um portal do tempo, conduzindo a cinco ou seis décadas no passado. Toda a equipagem é original, desde as rudimentares bacias de madeira, passando pela prensa e chegando a um legítimo pilão para moer grãos. Em 2004, conforme anotado no caderno, o engenho foi transferido do local original pra onde está agora, a um custo de R$ 1.658,00.\r\nDentro do rancho também se destaca um monte de flores de macela, colhidas na ótima safra recente, e secando para virar recheio de travesseiros. Sereno explica tudo direitinho, manuseando as plantas e espalhando pequenas pétalas pelo ambiente (aliás, mesmo dentro de casa as florzinhas preenchem o ar, como se fosse cinza ou o revoar de minúsculos insetos; sorte do repórter, do fotógrafo e do seu Pascoal, que acompanha a visita, que nenhum tem alergia): “As flores servem para tratar dor de cabeça e desentupir tudo. As folhas, no chá ou inaladas, são boas para o fígado. Tenho 16 travesseiros pra entregar”. Como a confirmar, algumas palmas batidas no portão indicam que chegou mais um freguês. “Tuuuuudo bem. Pode passar segunda-feira pra pegar os travesseiros.”\r\n\r\nResponso a Santo AntônioReligioso, Sereno demonstra sua devoção a Santo Antônio em várias imagens espalhadas pela casa. No caderno, algumas orações que merecem nova explicação: “Quando se pede uma graça a Santo Antônio, tem o responso, a petição e a resposta do responso”. E o santo tem correspondido? “Tuuuudo bem, ele sempre ajuda.”\r\nNo caso de Sereno, a ajuda do santo foi bem peculiar. “Eu me casei por correspondência, pelos anúncios da revista Sétimo Céu. Minha mulher, Maria Jorge Costa Silva, era do Maranhão. Ela veio pra cá, depois que casamos. A única filha que teríamos ela abortou há 36 anos. Faz uns vinte anos que voltou ao Maranhão.” Mesmo sem filhos naturais, Sereno diz ter criado 45 filhos. “Só três viraram vasilha”, complementa, referindo-se aos que não conseguiu encaminhar devidamente na vida.\r\n\r\nDefensor da TaperaAlém dos bem cuidados cadernos, Sereno guarda na memória a história da Tapera, “que começa do Tabuleiro pra cá; da ponte até o marco é o Acaraí”. Perto de sua casa ergue-se a igreja de São José do Acaraí. “Ela foi construída quando eu tinha 2 anos. A rua também se chamava São José do Acaraí; não sei por que mudaram para Binot de Goneville”, reclama, acrescentando que a estrada precisa melhorar, de preferência com asfalto, para facilitar a vida dos cerca de 600 habitantes da Tapera.\r\nVoltando ao caderno – com o escudo do Flamengo na capa, ainda que Sereno seja vascaíno –, ele também faz uma espécie de inventário de todos os seus bens, desde a fiel geladeira – ali sim, com um distintivo do Vasco grudado na porta – até o velho televisor, onde se mantém atualizado com o mundo lá fora.\r\nNas anotações sobre o cotidiano da Tapera, uma curiosidade: o primeiro telefone do bairro foi instalado no dia 30 de março de 2000, na residência de Isidoro Pereira – coincidentemente, o falecido mais recente anotado no caderno, dia 16 de março.\r\n\r\nUm chá de pitangaAssim, moendo mandioca para a farinha ou produzindo travesseiros de marcela, Sereno vai levando a vida e mantendo a tradição de arauto da Tapera. “Tiro meu sustento da terra, e as pessoas boas sempre dão alguma ajuda.” Para retribuir, Sereno distribui seus medicamentos caseiros, feitos do que tem no quintal, da macela ao chá de pitanga. “O remédio pra bronquite eu cobro, mas para os rins faço de graça.”";
	}
}
