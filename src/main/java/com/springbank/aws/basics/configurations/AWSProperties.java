package com.springbank.aws.basics.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ConfigurationProperties(prefix = "aws")
public class AWSProperties {

	private Credentials credentials;
	private Services services;

	@Data
	public static class Credentials {
		private String accessKey;
		private String secretKey;
		private String region;
	}

	@Data
	static class CommonProperties {
		boolean enabled;
	}

	@Data
	public static class Services {

		private boolean autoConfigurationEnabled;
		private S3 s3;
		private RDS rds;
		private SNS sns;
		private SQS sqs;

		@Data
		@EqualsAndHashCode(callSuper = true)
		public static class S3 extends CommonProperties {
		}

		@Data
		@EqualsAndHashCode(callSuper = true)
		public static class RDS extends CommonProperties {
		}

		@Data
		@EqualsAndHashCode(callSuper = true)
		public static class SNS extends CommonProperties {
			private String subject;
			private String message;
			private SNSTopic snsTopic;
		}

		@Data
		@EqualsAndHashCode(callSuper = true)
		public static class SQS extends CommonProperties {
			private String queueUrl;
			private boolean AsyncEnabled;
		}

		@Data
		public static class SNSTopic {
			private String topicArn;
		}
	}

}
