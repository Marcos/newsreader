package com.wp3x.repository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.wp3x.reader.Pictures;

public class TestPictures {

	Pictures pictureRepository = new Pictures();

	@Test
	public void getImageNameHappy() {
		String imgPath = "http://www.ndonline.com.br/uploads/global/materias/2013/05/22-05-2013-11-01-50-reuniao-subprefeitura.jpg";
		String imgName = pictureRepository.getImageName(imgPath, "test", "nuveo");
		String expectedImgName = "test_nuveo_22-05-2013-11-01-50-reuniao-subprefeitura";
		assertEquals(expectedImgName, imgName);
	}

	@Test
	public void getImageNameWithoutPath() {
		String imgPath = "22-05-2013-11-01-50-reuniao-subprefeitura.jpg";
		String imgName = pictureRepository.getImageName(imgPath, "test", "nuveo");
		String expectedImgName = "test_nuveo_22-05-2013-11-01-50-reuniao-subprefeitura";
		assertEquals(expectedImgName, imgName);
	}

	@Test
	public void getImageNameWithoutExtension() {
		String imgPath = "22-05-2013-11-01-50-reuniao-subprefeitura";
		String imgName = pictureRepository.getImageName(imgPath, "test", "nuveo");
		String expectedImgName = "test_nuveo_22-05-2013-11-01-50-reuniao-subprefeitura";
		assertEquals(expectedImgName, imgName);
	}

	@Test
	public void getImageTypeHappy() {
		String imgPath = "http://www.ndonline.com.br/uploads/global/materias/2013/05/22-05-2013-11-01-50-reuniao-subprefeitura.jpg";
		String imgName = pictureRepository.getImageType(imgPath);
		String expectedImgName = "jpg";
		assertEquals(expectedImgName, imgName);
	}

	@Test
	public void getImageTypeWithoutExtension() {
		String imgPath = "http://www.ndonline.com.br/uploads/global/materias/2013/05/22-05-2013-11-01-50-reuniao-subprefeitura";
		String imgName = pictureRepository.getImageType(imgPath);
		assertNull(imgName);
	}

	@Test
	public void testDimensionCalc1() {
		Integer width = 100;
		Integer height = 100;
		Integer newWidth = 50;

		Integer newHeight = pictureRepository.getProportionalHeight(width, height, newWidth);
		Integer expectedHeight = 50;
		assertEquals(expectedHeight, newHeight);
	}

	@Test
	public void testDimensionCalc2() {
		Integer width = 100;
		Integer height = 75;
		Integer newWidth = 50;

		Integer newHeight = pictureRepository.getProportionalHeight(width, height, newWidth);
		Integer expectedHeight = 37;
		assertEquals(expectedHeight, newHeight);
	}

	@Test
	public void testResizeImagePNG() throws IOException {
		InputStream originalStream = TestPictures.class.getClassLoader().getResourceAsStream("nuveo.png");
		BufferedImage originalImage = ImageIO.read(originalStream);
		BufferedImage newImage = pictureRepository.resizeImage(originalImage, "png", 40, 40);
		assertEquals(40, newImage.getWidth());
		assertEquals(40, newImage.getHeight());
		
		ImageIO.write(newImage, "png", new File("target/teste_resize_nuveo.png"));
	}
	
	@Test
	public void testResizeImageJPG() throws IOException {
		InputStream originalStream = TestPictures.class.getClassLoader().getResourceAsStream("nuveo.jpg");
		BufferedImage originalImage = ImageIO.read(originalStream);
		BufferedImage newImage = pictureRepository.resizeImage(originalImage, "jpg", 40, 40);
		assertEquals(40, newImage.getWidth());
		assertEquals(40, newImage.getHeight());
		
		ImageIO.write(newImage, "jpg", new File("target/teste_resize_nuveo.jpg"));
	}
	
	@Test
	public void testGetResizedStream() throws IOException{
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("prefeitura-img1.jpg");
		BufferedImage resizedStream = pictureRepository.getPictureStream(inputStream, "jpg");
		ImageIO.write(resizedStream, "jpg", new File("target/prefeitura-img1.jpg"));		
	}
	
	
}
