package com.wp3x.reader;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Pictures {

	private static final Integer MAX_WIDTH = 400;

	public BufferedImage getPictureStream(InputStream originalStream,
			String imageType) throws IOException {
		BufferedImage originalImage = ImageIO.read(originalStream);
		Integer width = originalImage.getWidth();
		Integer height = originalImage.getHeight();

		BufferedImage pictureStream = originalImage;
		if (width > MAX_WIDTH) {
			Integer maxHeight = getProportionalHeight(width, height, MAX_WIDTH);
			pictureStream = resizeImage(originalImage, imageType, MAX_WIDTH,
					maxHeight);
		}
		return pictureStream;
	}

	public String getImageType(String imgPath) {
		Integer nameStart = imgPath.lastIndexOf("/");
		Integer start = 0;
		Integer typeStart = imgPath.lastIndexOf(".");
		if (typeStart != null && typeStart > -1 && typeStart > nameStart) {
			start = typeStart + 1;
			return imgPath.substring(start);
		}
		return null;
	}

	public String getImageName(String imgPath, String source, String shortLink) {
		Integer start = 0;
		Integer end = imgPath.length();
		Integer nameStart = imgPath.lastIndexOf("/");
		Integer nameEnd = imgPath.lastIndexOf(".");
		if (nameStart != null && nameStart > -1) {
			start = nameStart + 1;
		}
		if (nameEnd != null && nameEnd > -1) {
			end = nameEnd;
		}

		return source + "_" + shortLink + "_" + imgPath.substring(start, end);
	}

	public Integer getProportionalHeight(Integer width, Integer height,
			Integer newWidth) {
		Integer newHeight = (height * newWidth) / width;
		return newHeight;
	}

	public BufferedImage resizeImage(BufferedImage originalImage,
			String imageType, Integer width, Integer height) throws IOException {
		Integer type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
				: originalImage.getType();
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();

		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}

}