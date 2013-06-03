package com.wp3x.reader;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.wp3x.model.Picture;

public class PictureRepository  {

	private static final Integer MAX_WIDTH = 400;

	Logger logger = Logger.getLogger(this.getClass());

	String accessKey = "AKIAINKM2ZOEPMP4P26A";
	String secretKey = "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";
	String bucketName = "nuveo";
	AWSCredentials picturesCredential = new BasicAWSCredentials(accessKey, secretKey);
	AmazonS3 s3 = new AmazonS3Client(picturesCredential);
	


	public Picture savePicture(final InputStream originalStream, String imageName, String imageType, Integer thumbWidth, Integer thumbHeight) throws IOException {
		String originalImageName = imageName + "." + imageType;
		
		BufferedImage bufferedImage = getPictureStream(originalStream, imageType);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, imageType, os);
		InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
		
		putInS3(inputStream, originalImageName);
		
		if(logger.isDebugEnabled()){
			ImageIO.write(bufferedImage, imageType, new File("target"+File.separator+originalImageName));
		}

		Picture picture = new Picture();
		picture.setName(originalImageName);
		return picture;
	}

	public BufferedImage getPictureStream(InputStream originalStream, String imageType) throws IOException {
		BufferedImage originalImage = ImageIO.read(originalStream);
		Integer width = originalImage.getWidth();
		Integer height = originalImage.getHeight();
		
		BufferedImage pictureStream = originalImage;
		if(width>MAX_WIDTH){
			Integer maxHeight = getProportionalHeight(width, height, MAX_WIDTH);
			pictureStream = resizeImage(originalImage, imageType, MAX_WIDTH, maxHeight);			
		}
		return pictureStream;
	}

	public void putInS3(InputStream pictureStream, String imageName) throws IOException {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(pictureStream.available());

		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, imageName, pictureStream, objectMetadata);
		s3.putObject(putObjectRequest);
	}

	public Picture getImportedImg(String imgPath, String source, String shortLink) {
		try{			
			URL url = new URL(imgPath);
			InputStream inputStream = url.openStream();
			return savePicture(inputStream, getImageName(imgPath, source, shortLink), getImageType(imgPath), 200, null);
		}catch (Exception e) {
			logger.warn("Can't get image from " + source +  " at " + shortLink + " img "+ imgPath, e);
		}
		return null;
	}

	public String getImageType(String imgPath) {
		Integer nameStart = imgPath.lastIndexOf("/");
		Integer start = 0;
		Integer typeStart = imgPath.lastIndexOf(".");
		if(typeStart!=null && typeStart>-1 && typeStart>nameStart){
			start = typeStart+1;
			return imgPath.substring(start);
		}
		return null;
	}

	public String getImageName(String imgPath, String source, String shortLink) {
		Integer start = 0;
		Integer end = imgPath.length();
		Integer nameStart = imgPath.lastIndexOf("/");
		Integer nameEnd = imgPath.lastIndexOf(".");
		if(nameStart!=null && nameStart>-1){
			start = nameStart+1;
		}
		if(nameEnd!=null && nameEnd>-1){
			end = nameEnd;
		}
		
		return source + "_" + shortLink + "_" +  imgPath.substring(start, end);
	}

	public Integer getProportionalHeight(Integer width, Integer height, Integer newWidth) {
		Integer newHeight = (height * newWidth)/ width;
		return newHeight;
	}
	
	public BufferedImage resizeImage(BufferedImage originalImage, String imageType, Integer width, Integer height) throws IOException{   
		Integer type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
	 	BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();

		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}
	
	
}