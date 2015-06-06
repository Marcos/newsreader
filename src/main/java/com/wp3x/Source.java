package com.wp3x;

import java.net.URL;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.wp3x.reader.SourceType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source {

	private URL sourceURL;
	private SourceType sourceType = SourceType.HTML;
	private String charset = "UTF-8";
	private String dateTextPattern;
	private String dateFormat = "dd/MM/yyyy";
	private String title;
	private String date;
	private String url;
	private String text;
	private String linkText;
	private String img;
	private String source;
	private String sourceLabel;
	private String urlResource;
	private String titlePrefixPattern;
	private String titleSufixPattern;
	private Boolean titleNormalize;
	private String urlPattern;
	private Integer maxResult = 10;
	private Integer minResult = 1;
	private Integer interval = 1;
	private Locale locale = new Locale( "pt", "BR" );
	private Boolean enabled = true;

}
