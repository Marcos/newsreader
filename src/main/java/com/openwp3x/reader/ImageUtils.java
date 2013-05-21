package com.openwp3x.reader;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtils {

	 public static InputStream resizeImage(InputStream inputStream, String imageType, Integer width, Integer height) throws IOException{
		 	BufferedImage originalImage = ImageIO.read(inputStream);
		 	inputStream.reset();
		 	Integer type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		 	BufferedImage resizedImage = new BufferedImage(width, height, type);
			Graphics2D g = resizedImage.createGraphics();

			g.drawImage(originalImage, 0, 0, width, height, null);
			g.dispose();
			g.setComposite(AlphaComposite.Src);
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(resizedImage, imageType, os);
			InputStream resizedInputStream = new ByteArrayInputStream(os.toByteArray());
			return resizedInputStream;
	 }

}