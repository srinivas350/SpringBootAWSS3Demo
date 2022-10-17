package com.example.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class AwsS3Service {
	private String Bucket_name="springboots3demo";
	@Autowired
	AmazonS3Client awsS3Client;
	
	public String uploadFile(MultipartFile file)
	{
		String fileKey=System.currentTimeMillis()+"_"+file.getOriginalFilename();
		File newFile=createFile(file);
		awsS3Client.putObject(Bucket_name,fileKey,newFile);
		newFile.delete();
		return fileKey+" File Uploaded to AWS S3.";
	}
	
	private File createFile(MultipartFile file)
	{
		File newFile=new File(file.getOriginalFilename());
		try(FileOutputStream fos=new FileOutputStream(newFile)){
			
			fos.write(file.getBytes());
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return newFile;
		
	}
	
	public byte[] downloadFile(String fileName)
	{
		S3Object sobj=awsS3Client.getObject(Bucket_name, fileName);
		S3ObjectInputStream s3os=sobj.getObjectContent();
		try
		{
			byte[] b=IOUtils.toByteArray(s3os);
			return b;
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteFile(String fileName)
	{
		awsS3Client.deleteObject(Bucket_name, fileName);
		return fileName+" Deleted from AWS Bucket.";
	}
	

}
