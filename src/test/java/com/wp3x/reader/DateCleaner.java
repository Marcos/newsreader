/**
 * 
 */
package com.wp3x.reader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

/**
 * @author marcos.ferreira
 * 
 */
public class DateCleaner {

    @Test
    public void testDateCleaner() throws ParseException {
        Locale.setDefault(new Locale("pt", "BR"));

        final SimpleDateFormat sample = new SimpleDateFormat("dd | MMM | yyyy");
        System.out.println(sample.format(new Date(System.currentTimeMillis())));

        final String dirtDate = "17 | ABR | 2013";
        final Date date = sample.parse(dirtDate);
        System.out.println(date);
    }

}
