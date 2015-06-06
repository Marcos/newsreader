package com.wp3x.pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkPattern {
	private String source;
	private String charset = "UTF-8";
	private String img;
	private String text;
}
