package com.wp3x;

import java.net.URL;
import java.util.Locale;

import lombok.Data;

import com.wp3x.reader.SourceType;

@Data
public class SourcePattern {

	private URL sourceURL;
	private SourceType sourceType = SourceType.HTML;
	private String charset = "UTF-8";
	private String dateTextPattern;
	private String dateFormat = "dd/MM/yyyy";
	private String titleXPath;
	private String dateXPath;
	private String urlXPath;
	private String textXPath;
	private String linkTextXPath;
	private String imgXPath;
	private String source;
	private String sourceLabel;
	private String urlResource;
	private String titlePrefixPattern;
	private String titleSufixPattern;
	private Boolean titleNormalize;
	private String linkTextPrefixPattern;
	private String linkTextSufixPattern;
	private String urlPattern;
	private Integer maxResult = 10;
	private Integer minResult = 1;
	private Integer interval = 1;
	private Locale locale = new Locale("pt", "BR");
	private Boolean enabled = true;

}
