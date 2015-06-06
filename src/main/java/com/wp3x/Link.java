package com.wp3x;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Link {
	private String source;
	private String charset = "UTF-8";
	private String img;
	private String text;
}
