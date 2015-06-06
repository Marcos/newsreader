package com.wp3x.reader;

import java.io.IOException;
import java.net.URL;

import com.wp3x.SourcePattern;

public class LinkReader {

	private SourcePattern entryPattern;
	private Reader reader;

	public LinkReader(SourcePattern entryPattern, URL linkUrl)
			throws ReaderException, IOException {
		this.entryPattern = entryPattern;
		this.reader = new Reader(linkUrl, entryPattern.getSource(),
				SourceType.HTML, entryPattern.getCharset());
	}

	public LinkEntry getLinkEntry() throws ReaderException {
		LinkEntry linkEntry = new LinkEntry();
		String linkText = this.reader.getTextContent(entryPattern
				.getLinkTextXPath());
		linkEntry.setText(linkText);

		String imgPath = this.reader.getTextContent(entryPattern.getImgXPath());
		linkEntry.setImgPath(imgPath);
		return linkEntry;
	}

}
