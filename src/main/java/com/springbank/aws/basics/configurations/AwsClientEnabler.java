package com.springbank.aws.basics.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsClientEnabler {

	@Autowired
	private AWSCredentials awsCredentials;
	@Autowired
	private AWSProperties awsProperties;

	@ConditionalOnProperty(value = "${aws.services.s3.client.enabled}", havingValue = "true", matchIfMissing = true)
	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(awsProperties.getRegion()).build();
	}

}
