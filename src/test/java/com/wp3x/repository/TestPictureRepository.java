package com.wp3x.repository;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import junit.framework.Assert;

import org.junit.Test;

import com.wp3x.reader.PictureRepository;

public class TestPictureRepository {

	PictureRepository pictureRepository = new PictureRepository();

	@Test
	public void getImageNameHappy() {
		String imgPath = "http://www.ndonline.com.br/uploads/global/materias/2013/05/22-05-2013-11-01-50-reuniao-subprefeitura.jpg";
		String imgName = pictureRepository.getImageName(imgPath, "test", "nuveo");
		String expectedImgName = "test_nuveo_22-05-2013-11-01-50-reuniao-subprefeitura";
		Assert.assertEquals(expectedImgName, imgName);
	}

	@Test
	public void getImageNameWithoutPath() {
		String imgPath = "22-05-2013-11-01-50-reuniao-subprefeitura.jpg";
		String imgName = pictureRepository.getImageName(imgPath, "test", "nuveo");
		String expectedImgName = "test_nuveo_22-05-2013-11-01-50-reuniao-subprefeitura";
		Assert.assertEquals(expectedImgName, imgName);
	}

	@Test
	public void getImageNameWithoutExtension() {
		String imgPath = "22-05-2013-11-01-50-reuniao-subprefeitura";
		String imgName = pictureRepository.getImageName(imgPath, "test", "nuveo");
		String expectedImgName = "test_nuveo_22-05-2013-11-01-50-reuniao-subprefeitura";
		Assert.assertEquals(expectedImgName, imgName);
	}

	@Test
	public void getImageTypeHappy() {
		String imgPath = "http://www.ndonline.com.br/uploads/global/materias/2013/05/22-05-2013-11-01-50-reuniao-subprefeitura.jpg";
		String imgName = pictureRepository.getImageType(imgPath);
		String expectedImgName = "jpg";
		Assert.assertEquals(expectedImgName, imgName);
	}

	@Test
	public void getImageTypeWithoutExtension() {
		String imgPath = "http://www.ndonline.com.br/uploads/global/materias/2013/05/22-05-2013-11-01-50-reuniao-subprefeitura";
		String imgName = pictureRepository.getImageType(imgPath);
		Assert.assertNull(imgName);
	}

	@Test
	public void testPutS3() throws IOException {
		InputStream imageStream = TestPictureRepository.class.getClassLoader().getResourceAsStream("nuveo.png");
		pictureRepository.putInS3(imageStream, "nuveo.png");
	}

	@Test
	public void testDimensionCalc1() {
		Integer width = 100;
		Integer height = 100;
		Integer newWidth = 50;

		Integer newHeight = pictureRepository.getProportionalHeight(width, height, newWidth);
		Integer expectedHeight = 50;
		Assert.assertEquals(expectedHeight, newHeight);
	}

	@Test
	public void testDimensionCalc2() {
		Integer width = 100;
		Integer height = 75;
		Integer newWidth = 50;

		Integer newHeight = pictureRepository.getProportionalHeight(width, height, newWidth);
		Integer expectedHeight = 37;
		Assert.assertEquals(expectedHeight, newHeight);
	}

	@Test
	public void testResizeImage() throws IOException {
		InputStream originalStream = TestPictureRepository.class.getClassLoader().getResourceAsStream("nuveo.png");
		BufferedImage originalImage = ImageIO.read(originalStream);
		InputStream newInputStream = pictureRepository.resizeImage(originalImage, "png", 40, 40);
		BufferedImage newImage = ImageIO.read(newInputStream);
		Assert.assertEquals(40, newImage.getWidth());
		Assert.assertEquals(40, newImage.getHeight());
		
		saveToFile(newInputStream, "target/teste_resize_nuveo.png");
	}

	private void saveToFile(InputStream originalStream, String dest) {
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			inputStream = originalStream;
			outputStream = new FileOutputStream(new File(dest));

			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}
}
