package com.betahikaru.app.usecase.aws;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.betahikaru.app.config.aws.AwsApiConfig;
import com.betahikaru.app.pojo.aws.S3Status;
import com.betahikaru.app.usecase.Monitor;

@Component
public class S3Monitor implements Monitor {

	@Autowired
	private AwsApiConfig apiConfig;

	private AmazonS3 createClient(AmazonS3ClientBuilder builder) {
		return builder.build();
	}

	private AmazonS3 createClient() {
		Regions region = apiConfig.getRegion();
		AWSCredentialsProvider credentialsProvider = apiConfig.getCredentialsProvider();
		AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard().withRegion(region)
				.withCredentials(credentialsProvider);
		return createClient(builder);
	}

	public S3Status monitorStatus() {
		AmazonS3 s3Client = createClient();
		List<Bucket> bucketList = s3Client.listBuckets();
		Timestamp createdAt = new Timestamp(System.currentTimeMillis());
		int countBuckets = bucketList.size();
		List<String> bucketNames = new ArrayList<>();
		for (Bucket bucket : bucketList) {
			bucketNames.add(bucket.getName());
		}
		S3Status status = new S3Status(countBuckets, bucketNames, createdAt);
		return status;
	}
}
