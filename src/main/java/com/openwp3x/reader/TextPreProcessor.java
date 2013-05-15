package com.openwp3x.reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.text.WordUtils;


public class TextPreProcessor {

	public String normalizeWordWrap(String text) {
		String normalizedText = text; 
		normalizedText = normalizedText.replaceAll("\r", "");
		normalizedText = normalizedText.replaceAll("\n[ ]*", "\n");
		normalizedText = normalizedText.replaceAll("\n+", "\n");
		normalizedText = normalizedText.replaceAll("^\n", "");
		normalizedText = normalizedText.replaceAll("\n$", "");
		return normalizedText.trim();
	}
	
	public String treatSufixPattern(String text, String sufixPattern) {
    	if(sufixPattern!=null){
    		Pattern pattern = Pattern.compile(sufixPattern);
    		Matcher matcher = pattern.matcher(text);
    		if(matcher.find()){    			
    			Integer endPosition = matcher.start();
				text = text.substring(0, endPosition);
    		}
    	}
    	return text;
	}
    
    public String treatPrefixPattern(String text, String prefixPattern) {
    	if(prefixPattern!=null){
    		Pattern pattern = Pattern.compile(prefixPattern);
    		Matcher matcher = pattern.matcher(text);
    		if(matcher.find()){    			
    			Integer startPosition = matcher.end();
				text = text.substring(startPosition);
    		}
    	}
    	return text;
	}

	public String normalize(String title) {
		return WordUtils.capitalizeFully(title);
	}

	public String getToken(String dirtText, String regex) {
		final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(dirtText);
        matcher.find();
        return matcher.group();
	}

}
