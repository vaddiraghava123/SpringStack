package com.example.springawss3lambda;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class LambdaHandler implements RequestHandler<S3Event, String> {

	private static final String region_code ="us-east-2";
	
	AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region_code))
			.withCredentials(new DefaultAWSCredentialsProviderChain()).build();
	
	public String handleRequest(S3Event input, Context context) {
		
		String bucketName = input.getRecords().get(0).getS3().getBucket().getName();
		String fileName = input.getRecords().get(0).getS3().getObject().getKey();
		
		context.getLogger().log("Bucket Name :"+bucketName);
		context.getLogger().log("File Name :"+ fileName);
		
		try {
			InputStream in = s3Client.getObject(bucketName, fileName).getObjectContent();
			String fileContent = IOUtils.toString(in, StandardCharsets.UTF_8);
			
			context.getLogger().log("file content is :"+ fileContent);
		} catch(Exception e) {
			return "Failed to retrieve"+e.getMessage();
	}
		return "successfully file content retrieve from S3";
	}

}
