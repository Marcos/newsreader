package com.wp3x.content;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {

	public String normalizeText(String text) {
		if (text == null)
			return text;
		String normalizedText = normalizeWhiteSpace( text );
		normalizedText = removeHTML( normalizedText );
		normalizedText = normalizedText.replaceAll( "\r", "" );
		normalizedText = normalizedText.replaceAll( "^\n", "" );
		normalizedText = normalizedText.replaceAll( "\n$", "" );
		return normalizedText.trim();
	}

	public String treatPrefixPattern(String text, String prefixPattern) {
		if (prefixPattern != null) {
			Pattern pattern = Pattern.compile( prefixPattern );
			Matcher matcher = pattern.matcher( text );
			if (matcher.find()) {
				Integer startPosition = matcher.end();
				text = text.substring( startPosition );
			}
		}
		return text;
	}

	public String getRegexToken(String dirtText, String regex) {
		final Pattern pattern = Pattern.compile( regex );
		final Matcher matcher = pattern.matcher( dirtText );
		matcher.find();
		return matcher.group();
	}

	public String normalizeWhiteSpace(String text) {
		StringBuilder newText = new StringBuilder();
		for (char c : text.toCharArray()) {
			if (c == 160) {
				newText.append( (char) 32 );
			} else {
				newText.append( c );
			}
		}
		return newText.toString();
	}

	public String removeHTML(String text) {
		String normalizedText = text.replaceAll( "<.*>", "\n" );
		return normalizedText;
	}

	public String getShortText(String text, int size) {
		if (text == null)
			return null;
		String normalizedText = normalizeText( text );
		normalizedText = normalizedText.replaceAll( "\n\\s*", "\n" );
		normalizedText = normalizedText.replaceAll( "\n\n*", "\n" );

		if (normalizedText.length() < size) {
			return normalizedText;
		}

		Pattern pattern = Pattern.compile( " " );
		Matcher matcher = pattern.matcher( normalizedText );

		Integer endLimit = 0;
		while (endLimit < size && matcher.find()) {
			endLimit = matcher.start();
		}
		return normalizedText.substring( 0, endLimit );
	}

}
