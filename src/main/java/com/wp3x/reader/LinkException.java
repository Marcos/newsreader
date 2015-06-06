package com.wp3x.reader;


public class LinkException extends Exception {

	private static final long serialVersionUID = -607898844582185838L;

	public LinkException(String message, Exception e) {
		super(message, e);
	}

}
