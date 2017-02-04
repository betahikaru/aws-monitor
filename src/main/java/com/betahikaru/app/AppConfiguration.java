package com.betahikaru.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.regions.Regions;
import com.betahikaru.app.config.aws.AwsApiConfig;

@SpringBootApplication
public class AppConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(AppConfiguration.class, args);
	}

	@Bean
	AwsApiConfig defaultAwsApiConfig() {
		return new AwsApiConfig().withRegions(Regions.AP_NORTHEAST_1).withProfile("default");
	}
}
