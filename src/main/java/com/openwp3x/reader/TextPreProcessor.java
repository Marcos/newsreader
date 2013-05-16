package com.openwp3x.reader;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.apache.commons.lang3.text.WordUtils;

import antlr.StringUtils;

public class TextPreProcessor {

	public static String normalizeWordWrap(String text) {
		String normalizedText = text;
		normalizedText = normalizedText.replaceAll("\r\\s+|\n\\s+", "\n");
		normalizedText = normalizedText.replaceAll("\n\\s*", "\n");
		normalizedText = normalizedText.replaceAll("\n+", "\n");
		normalizedText = normalizedText.replaceAll("^\n", "");
		normalizedText = normalizedText.replaceAll("\n$", "");
		return normalizedText.trim();
	}

	public static void main(String[] args) {
		String text = "First Line\n  \n SecondLine Park\n  \n \n Third line ";
		System.out.println(text.length());
		text = text.replaceAll("\n\\s{1,}", "\n");
		System.out.println(text.length());
		System.out.println(text);
		System.out.println("First Line\n\nSecondLine Park\n\n\nThird line ".equals(text));
	}

//	public static void main2(String[] args) {
//		String text = "1\n  \n 2.\n  \n \n 3 ";
//		char[] chars = text.toCharArray();
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < chars.length; i++) {
//			if (!Character.isLetterOrDigit(chars[i]) && !Character.isLetterOrDigit(chars[i - 1])) {
//				continue;
//			}
//			sb.append(chars[i]);
//		}
//		System.out.println(sb.toString());
//	}

	public String treatSufixPattern(String text, String sufixPattern) {
		if (sufixPattern != null) {
			Pattern pattern = Pattern.compile(sufixPattern);
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
				Integer endPosition = matcher.start();
				text = text.substring(0, endPosition);
			}
		}
		return text;
	}

	public String treatPrefixPattern(String text, String prefixPattern) {
		if (prefixPattern != null) {
			Pattern pattern = Pattern.compile(prefixPattern);
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
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
