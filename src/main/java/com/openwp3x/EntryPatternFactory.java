package com.openwp3x;

import java.util.Locale;

public class EntryPatternFactory {

    private static final Locale PT_BR = new Locale("pt", "BR");

    public static EntryPattern getSociescPattern() {
        final EntryPattern entryPattern = new EntryPattern();
        entryPattern.setLocale(EntryPatternFactory.PT_BR);
        entryPattern.setDateXPath("//*[@id=\"listBusca\"]/ul/li[{_counter}]/a/span[1]");
        entryPattern.setTitleXPath("//*[@id=\"listBusca\"]/ul/li[{_counter}]/a/span[2]");
        entryPattern.setUrlXPath("//*[@id=\"listBusca\"]/ul/li[{_counter}]/a/@onclick");
        entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
        entryPattern.setSource("sociesc");
        entryPattern.setUrlResource("http://www.sociesc.org.br/pt/noticias/ajax_news_view.php?id=");
        entryPattern.setUrlPattern("\\d+");
        entryPattern.setDateFormat("dd/MM/yyyy");
        entryPattern.setSourceLabel("SOCIESC");
        return entryPattern;
    }

    public static EntryPattern getUdescPattern() {
        final EntryPattern entryPattern = new EntryPattern();
        entryPattern.setLocale(EntryPatternFactory.PT_BR);
        entryPattern.setDateXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[2]/table/tbody/tr[{_counter}]/td[1]");
        entryPattern.setTitleXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[2]/table/tbody/tr[{_counter}]/td[2]/a");
        entryPattern.setUrlXPath("//*[@id=\"wrapper\"]/table[1]/tbody/tr/td[1]/div[2]/table/tbody/tr[{_counter}]/td[2]/a//@href");
        entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
        entryPattern.setSource("udesc");
        entryPattern.setUrlResource("http://www.joinville.udesc.br/portal/noticias/");
        entryPattern.setMaxResult(20);
        entryPattern.setDateFormat("dd/MM/yyyy");
        entryPattern.setSourceLabel("UDESC");
        return entryPattern;
    }

    public static EntryPattern getUnivillePattern() {
        final EntryPattern entryPattern = new EntryPattern();
        entryPattern.setLocale(EntryPatternFactory.PT_BR);
        entryPattern.setDateXPath("//*[@id=\"columnCenter\"]/div[6]/ul/li[{_counter}]/p");
        entryPattern.setTitleXPath("//*[@id=\"columnCenter\"]/div[6]/ul/li[{_counter}]/a");
        entryPattern.setUrlXPath("//*[@id=\"columnCenter\"]/div[6]/ul/li[{_counter}]/a/@href");
        entryPattern.setDateTextPattern("\\d{2}\\.\\d{2}\\.\\d{4}");
        entryPattern.setSource("univille");
        entryPattern.setDateFormat("dd.MM.yyyy");
        entryPattern.setSourceLabel("Univille");
        return entryPattern;
    }

    public static EntryPattern getPrefeituraPattern() {
        final EntryPattern entryPattern = new EntryPattern();
        entryPattern.setLocale(EntryPatternFactory.PT_BR);
        entryPattern.setDateXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/span[1]");
        entryPattern.setTitleXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]//h3");
        entryPattern.setUrlXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/a/@href");
        entryPattern.setDateTextPattern("\\d{2}\\s\\|\\s\\w{3}\\s\\|\\s\\d{4}");
        entryPattern.setSource("prefeitura");
        entryPattern.setUrlResource("http://www.joinville.sc.gov.br");
        entryPattern.setDateFormat("dd | MMM | yyyy");
        entryPattern.setSourceLabel("Prefeitura de Joinville");
        return entryPattern;
    }

    /**
     * @return
     */
    public static EntryPattern getAjorpemePattern() {
        final EntryPattern entryPattern = new EntryPattern();
        entryPattern.setDateXPath("//*[@id=\"content\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table[{_counter}]/tbody/tr/td/a");
        entryPattern.setTitleXPath("//*[@id=\"content\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table[{_counter}]/tbody/tr/td/a");
        entryPattern.setUrlXPath("//*[@id=\"content\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table[{_counter}]/tbody/tr/td/a/@href");
        entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{2}");
        entryPattern.setSource("ajorpeme");
        entryPattern.setUrlResource("http://www.joinville.sc.gov.br");
        entryPattern.setMinResult(1);
        entryPattern.setInterval(2);
        entryPattern.setMaxResult(20);
        entryPattern.setDateFormat("dd/MM/yy");
        entryPattern.setSourceLabel("Ajorpeme");
        return entryPattern;
    }

    /**
     * @return
     */
    public static EntryPattern getAcijPattern() {
        final EntryPattern entryPattern = new EntryPattern();
        entryPattern.setDateXPath("/html/body/div/div[2]/div[1]/div[2]/ul/li[2]/span/a");
        entryPattern.setTitleXPath("/html/body/div/div[2]/div[1]/div[2]/ul/li[2]/span/a");
        entryPattern.setUrlXPath("/html/body/div/div[2]/div[1]/div[2]/ul/li[2]/span/a/@href");
        //entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
        entryPattern.setSource("acij");
        entryPattern.setUrlResource("www.acij.com.br");
        //entryPattern.setDateFormat("dd/MM/yyyy");
        entryPattern.setSourceLabel("ACIJ");
        return entryPattern;
    }

    /**
     * @return
     */
    public static EntryPattern getCDLPattern() {
        final EntryPattern entryPattern = new EntryPattern();
        entryPattern.setDateXPath("/html/body/center/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/p[2]/i");
        entryPattern.setTitleXPath("/html/body/center/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/p[2]/b");
        entryPattern.setUrlXPath("/html/body/center/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/p[2]/nobr/a/@href");
        entryPattern.setDateTextPattern("\\d{2}/\\d{2}/\\d{4}");
        entryPattern.setSource("cdl_joinville");
        entryPattern.setUrlResource("http://cdljoinville.com.br/");
        entryPattern.setDateFormat("dd/MM/yyyy");
        entryPattern.setSourceLabel("CDL Joinville");
        return entryPattern;
    }

    /**
     * @return
     */
    public static EntryPattern getDefesaCivilPattern() {
        final EntryPattern entryPattern = new EntryPattern();
        entryPattern.setDateXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/span[1]");
        entryPattern.setTitleXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]//h3");
        entryPattern.setUrlXPath("//*[@id=\"main\"]/div/div/div[1]/ul[1]/li[{_counter}]/a/@href");
        entryPattern.setDateTextPattern("\\d{2}\\s\\|\\s\\w{3}\\s\\|\\s\\d{4}");
        entryPattern.setSource("defesa_civil");
        entryPattern.setUrlResource("http://cdljoinville.com.br/");
        entryPattern.setDateFormat("dd/MM/yyyy");
        entryPattern.setSourceLabel("Prefeitura de Joinville");
        return entryPattern;
    }
}
