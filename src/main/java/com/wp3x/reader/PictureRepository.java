package com.wp3x.reader;

import java.io.IOException;
import java.io.InputStream;

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

	Logger log = Logger.getLogger(this.getClass());
	String accessKey = "AKIAINB4PEE2O6DPQ2OQ";
	String secretKey = "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";
	String mvstoreBucketName = "mvstore";
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

	private void putInS3(InputStream pictureStream, String imageName) throws IOException {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(pictureStream.available());

		PutObjectRequest putObjectRequest = new PutObjectRequest(mvstoreBucketName, imageName, pictureStream, objectMetadata);
		s3.putObject(putObjectRequest);
	}
	
	public Picture savePicture(UnsavedPicture unsavedPicture, Integer thumbWidth, Integer thumbHeight) throws IOException {
		return savePicture(unsavedPicture.getInputStream(), unsavedPicture.getImageName(), unsavedPicture.getImageType(), thumbWidth, thumbHeight);
	}

}