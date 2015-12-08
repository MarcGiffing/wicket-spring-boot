package com.giffing.wicket.spring.boot.context.exceptions;

/**
 * General extension from which all exception should extend
 * 
 * @author Marc Giffing
 *
 */
public class WicketSpringBootException extends RuntimeException{

	public WicketSpringBootException() {
		super();
	}

	public WicketSpringBootException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WicketSpringBootException(String message, Throwable cause) {
		super(message, cause);
	}

	public WicketSpringBootException(String message) {
		super(message);
	}

	public WicketSpringBootException(Throwable cause) {
		super(cause);
	}

}
