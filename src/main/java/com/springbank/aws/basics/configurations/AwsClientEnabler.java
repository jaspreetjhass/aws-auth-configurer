package com.springbank.aws.basics.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@ConditionalOnBean(value = AwsCredentialAutoConfiguration.class)
@Configuration
public class AwsClientEnabler {

	@Autowired
	private AWSCredentials awsCredentials;
	@Autowired
	private AWSProperties awsProperties;

	@ConditionalOnProperty(value = "${aws.services.s3.enabled}", havingValue = "true", matchIfMissing = true)
	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(awsProperties.getCredentials().getRegion()).build();
	}

	@ConditionalOnProperty(value = "${aws.services.rds.enabled}", havingValue = "true", matchIfMissing = true)
	@Bean
	public AmazonRDS amazonRDS() {
		return AmazonRDSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(awsProperties.getCredentials().getRegion()).build();
	}

	@ConditionalOnProperty(value = "${aws.services.sns.enabled}", havingValue = "true", matchIfMissing = true)
	@Bean
	public AmazonSNS amazonSns() {
		return AmazonSNSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(awsProperties.getCredentials().getRegion()).build();
	}

	@ConditionalOnProperty(value = "${aws.services.sqs.enabled}", havingValue = "true", matchIfMissing = false)
	@Bean
	public AmazonSQS amazonSqs() {
		return AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(awsProperties.getCredentials().getRegion()).build();
	}

}
