package com.wp3x.reader;

import java.io.IOException;
import java.net.URL;

import com.wp3x.Link;

public class LinkReader {

	private Link link;
	private Reader reader;

	public LinkReader(Link pattern, URL linkUrl) throws ReaderException,
			IOException {
		this.link = pattern;
		this.reader = new Reader( linkUrl, pattern.getSource(), SourceType.HTML,
				pattern.getCharset() );
	}

	public LinkEntry getLinkEntry() throws ReaderException {
		LinkEntry linkEntry = new LinkEntry();
		String linkText = readText();
		linkEntry.setText( linkText );

		String imgPath = readImage();
		linkEntry.setImgPath( imgPath );
		return linkEntry;
	}

	private String readImage() throws ReaderException {
		return this.reader.getTextContent( link.getImg() );
	}

	private String readText() throws ReaderException {
		return this.reader.getTextContent( link.getText() );
	}

}
