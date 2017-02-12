package com.betahikaru.app.pojo.aws;

import java.util.List;

import com.betahikaru.app.pojo.Status;

public class S3Status implements Status {
	private final int countBuckets;

	private final List<String> bucketNames;

	public S3Status(int countBuckets, List<String> bucketNames) {
		this.countBuckets = countBuckets;
		this.bucketNames = bucketNames;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	public int getCountBuckets() {
		return countBuckets;
	}

	public List<String> getBucketNames() {
		return bucketNames;
	}
}
