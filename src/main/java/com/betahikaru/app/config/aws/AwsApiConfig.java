package com.betahikaru.app.config.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;

public class AwsApiConfig {

	private Regions region = Regions.AP_NORTHEAST_1;

	private String profile = "default";

	private AWSCredentialsProvider credentialsProvider = new ProfileCredentialsProvider(profile);

	public AwsApiConfig() {
	}

	public AwsApiConfig withRegions(Regions region) {
		this.region = region;
		return this;
	}

	public Regions getRegion() {
		return region;
	}

	public AwsApiConfig withProfile(String profile) {
		this.profile = profile;
		this.credentialsProvider = new ProfileCredentialsProvider(this.profile);
		return this;
	}

	public String getProfile() {
		return profile;
	}

	public AwsApiConfig withCredentialsProvider(AWSCredentialsProvider credentialsProvider) {
		this.credentialsProvider = credentialsProvider;
		return this;
	}

	public AWSCredentialsProvider getCredentialsProvider() {
		return credentialsProvider;
	}
}
