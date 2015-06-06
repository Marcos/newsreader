package com.wp3x.pattern;

import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntryPattern {

	private String dateTextPattern;
	private String dateFormat = "dd/MM/yyyy";
	private String title;
	private String date;
	private String url;
	private String text;
	private String linkText;
	private String img;
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
