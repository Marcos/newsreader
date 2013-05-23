package com.wp3x.reader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.wp3x.model.Picture;
import com.wp3x.model.UnsavedPicture;

public class PictureRepository  {

	Logger logger = Logger.getLogger(this.getClass());

	String accessKey = "AKIAINKM2ZOEPMP4P26A";
	String secretKey = "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";
	String bucketName = "nuveo";
	AWSCredentials picturesCredential = new BasicAWSCredentials(accessKey, secretKey);

	AmazonS3 s3 = new AmazonS3Client(picturesCredential);
	


	public Picture savePicture(final InputStream pictureStream, String imageName, String imageType, Integer thumbWidth, Integer thumbHeight) throws IOException {
		String originalImageName = imageName + "_o"+ "."+imageType;
		String thumbnailName =  imageName + "_t"+ "."+imageType;
		InputStream thumbNailStream = ImageUtils.resizeImage(pictureStream, imageType, thumbWidth, thumbHeight);		

		putInS3(pictureStream, originalImageName);		
		putInS3(thumbNailStream, thumbnailName);

		Picture picture = new Picture();
		picture.setOriginalId(originalImageName);
		picture.setThumbnailId(thumbnailName);
		return picture;
	}

	public void putInS3(InputStream pictureStream, String imageName) throws IOException {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(pictureStream.available());

		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, imageName, pictureStream, objectMetadata);
		s3.putObject(putObjectRequest);
	}
	
	public Picture savePicture(UnsavedPicture unsavedPicture, Integer thumbWidth, Integer thumbHeight) throws IOException {
		return savePicture(unsavedPicture.getInputStream(), unsavedPicture.getImageName(), unsavedPicture.getImageType(), thumbWidth, thumbHeight);
	}

	public Picture getImportedImg(String imgPath) {
		try{			
			URL url = new URL(imgPath);
			InputStream inputStream = url.openStream();
			return savePicture(inputStream, getImageName(imgPath), getImageType(imgPath), 200, null);
		}catch (Exception e) {
			logger.warn("Can't get image from " + imgPath);
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

	public String getImageName(String imgPath) {
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
		
		return imgPath.substring(start, end);
	}

}