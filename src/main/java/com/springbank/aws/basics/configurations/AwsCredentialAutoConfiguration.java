package com.springbank.aws.basics.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.springbank.aws.basics.exceptions.AwsPropertiesNotSetException;

@ConditionalOnClass(value = BasicAWSCredentials.class)
@EnableConfigurationProperties(AWSProperties.class)
public class AwsCredentialAutoConfiguration {

	@Autowired
	private AWSProperties awsProperties;

	@ConditionalOnMissingBean(value = { AWSCredentials.class })
	@Bean
	public AWSCredentials awsCredentials() {
		validateProperties();
		return new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey());
	}

	private void validateProperties() {
		boolean isValid = true;
		final String accessKey = awsProperties.getAccessKey();
		final String secretKey = awsProperties.getSecretKey();
		final String region = awsProperties.getRegion();

		if (!StringUtils.hasLength(accessKey))
			isValid = false;
		if (isValid && !StringUtils.hasLength(secretKey))
			isValid = false;
		if (isValid && !StringUtils.hasLength(region))
			isValid = false;

		if (!isValid) {
			throw new AwsPropertiesNotSetException("aws properties are not set.");
		}
	}

}
