package com.openwp3x.reader;

import org.junit.Test;

import junit.framework.Assert;

public class TestTextPreProcessor {

	TextPreProcessor textPreProcessor = new TextPreProcessor();
	
	@Test
	public void testNormalizeLineWrap(){
		String text = "\r\n\r\n\r\nFirst line, a title without period\n\r\n\r\n\r\n\nOther line, now with period.\r\n  \r\n Another line, with space between \\n";
		
		String normalizedTex = textPreProcessor.normalizeWordWrap(text);
		Assert.assertEquals(getNormalizedWrapText(), normalizedTex);
	}
	
	private String getNormalizedWrapText() {
		return "First line, a title without period\nOther line, now with period.\nAnother line, with space between \\n";
	}

	@Test
    public void testCleanerStart() {
        final String dirtText = "\n   18/04/13 - Esta semana tem Café & Negócios na Ajorpeme";
        String treatPrefixPattern = "\\.*\\s-\\s";
        final String cleanedText = textPreProcessor.treatPrefixPattern(dirtText, treatPrefixPattern);
        Assert.assertEquals(cleanedText, "Esta semana tem Café & Negócios na Ajorpeme");
    }
	
	

}
