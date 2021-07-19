package com.springbank.aws.basics.exceptions;

public class AwsPropertiesNotSetException extends RuntimeException {

	private static final long serialVersionUID = 5599255590969143052L;

	public AwsPropertiesNotSetException(final String cause) {
		super(cause);
	}

	public AwsPropertiesNotSetException(final String cause, final Throwable throwable) {
		super(cause, throwable);
	}

}
