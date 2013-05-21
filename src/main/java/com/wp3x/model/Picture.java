package com.wp3x.model;

import javax.persistence.Id;

@javax.persistence.Entity
public class Picture {

	public static final Integer DEFAULT_THUMB_WIDTH = 147;

	public static final Integer DEFAULT_THUMB_HEIGHT = 110;

	@Id
	private String originalId;

	private String thumbnailId;

	public String getOriginalId() {
		return this.originalId;
	}

	public void setOriginalId(final String fileName) {
		this.originalId = fileName;
	}

	public String getThumbnailId() {
		return thumbnailId;
	}

	public void setThumbnailId(String thumbnail) {
		this.thumbnailId = thumbnail;
	}

}