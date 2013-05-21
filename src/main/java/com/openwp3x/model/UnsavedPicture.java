package com.openwp3x.model;

import java.io.InputStream;

public class UnsavedPicture {

	private InputStream inputStream;

	private String imageName;

	private String imageType;

	public UnsavedPicture(InputStream inputStream, String imageName, String imageType) {
		this.inputStream = inputStream;
		this.imageName = imageName;
		this.imageType = imageType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}


}