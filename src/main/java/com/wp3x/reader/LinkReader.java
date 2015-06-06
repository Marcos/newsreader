package com.wp3x.reader;

import java.io.IOException;
import java.net.URL;

import com.wp3x.content.Link;
import com.wp3x.exceptions.ReaderException;
import com.wp3x.pattern.LinkPattern;
import com.wp3x.pattern.SourceType;

public class LinkReader {

	private LinkPattern link;
	private Reader reader;

	public LinkReader(LinkPattern pattern, URL linkUrl) throws ReaderException,
			IOException {
		this.link = pattern;
		this.reader = new Reader( linkUrl, pattern.getSource(), SourceType.HTML,
				pattern.getCharset() );
	}

	public Link getLinkEntry() throws ReaderException {
		Link linkEntry = new Link();
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
