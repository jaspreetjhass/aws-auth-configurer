package com.springbank.aws.basics.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.springbank.aws.basics.exceptions.AwsPropertiesNotSetException;

@Configuration
@ConditionalOnProperty(value = "${aws.services.auto-configuration-enabled}", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(AWSProperties.class)
public class AwsCredentialAutoConfiguration {

	@Autowired
	private AWSProperties awsProperties;

	@ConditionalOnMissingBean(value = { AWSCredentials.class })
	@Bean
	public AWSCredentials awsCredentials() {
		validateProperties();
		return new BasicAWSCredentials(awsProperties.getCredentials().getAccessKey(),
				awsProperties.getCredentials().getSecretKey());
	}

	private void validateProperties() {
		boolean isValid = true;
		final String accessKey = awsProperties.getCredentials().getAccessKey();
		final String secretKey = awsProperties.getCredentials().getSecretKey();
		final String region = awsProperties.getCredentials().getRegion();

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
