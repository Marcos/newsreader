package com.wp3x.reader;

import java.net.URL;

public class Tests {

	public static URL getURL(String file) {
		return Tests.class.getClassLoader().getResource(file);
	}
}
