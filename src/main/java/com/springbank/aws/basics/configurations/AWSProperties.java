package com.springbank.aws.basics.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "aws.credentials")
public class AWSProperties {

	private String accessKey;
	private String secretKey;
	private String region;

}
