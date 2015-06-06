package com.wp3x.pattern;

import java.net.URL;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SourcePattern {

	private URL sourceURL;
	private SourceType sourceType = SourceType.HTML;
	private String charset = "UTF-8";
	private String source;
	private String sourceLabel;

}
