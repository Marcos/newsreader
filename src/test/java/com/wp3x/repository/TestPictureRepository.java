package com.wp3x.repository;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import junit.framework.Assert;

import com.wp3x.reader.PictureRepository;

public class TestPictureRepository {
	
	PictureRepository pictureRepository = new PictureRepository();
	
	@Test
	public void getImageNameHappy(){
		String imgPath = "http://www.ndonline.com.br/uploads/global/materias/2013/05/22-05-2013-11-01-50-reuniao-subprefeitura.jpg";
		String imgName = pictureRepository.getImageName(imgPath);
		String expectedImgName = "22-05-2013-11-01-50-reuniao-subprefeitura";
		Assert.assertEquals(expectedImgName, imgName);
	}
	
	@Test
	public void getImageNameWithoutPath(){
		String imgPath = "22-05-2013-11-01-50-reuniao-subprefeitura.jpg";
		String imgName = pictureRepository.getImageName(imgPath);
		String expectedImgName = "22-05-2013-11-01-50-reuniao-subprefeitura";
		Assert.assertEquals(expectedImgName, imgName);
	}
	
	@Test
	public void getImageNameWithoutExtension(){
		String imgPath = "22-05-2013-11-01-50-reuniao-subprefeitura";
		String imgName = pictureRepository.getImageName(imgPath);
		String expectedImgName = "22-05-2013-11-01-50-reuniao-subprefeitura";
		Assert.assertEquals(expectedImgName, imgName);
	}
	
	@Test
	public void getImageTypeHappy(){
		String imgPath = "http://www.ndonline.com.br/uploads/global/materias/2013/05/22-05-2013-11-01-50-reuniao-subprefeitura.jpg";
		String imgName = pictureRepository.getImageType(imgPath);
		String expectedImgName = "jpg";
		Assert.assertEquals(expectedImgName, imgName);
	}
	
	@Test
	public void getImageTypeWithoutExtension(){
		String imgPath = "http://www.ndonline.com.br/uploads/global/materias/2013/05/22-05-2013-11-01-50-reuniao-subprefeitura";
		String imgName = pictureRepository.getImageType(imgPath);
		Assert.assertNull(imgName);
	}
	
	@Test
	public void testPutS3() throws IOException{
		InputStream imageStream = TestPictureRepository.class.getClassLoader().getResourceAsStream("nuveo.png");
		pictureRepository.putInS3(imageStream, "nuveo.png");
	}

}
