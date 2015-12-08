package com.giffing.wicket.spring.boot.context.exceptions.extensions;

import com.giffing.wicket.spring.boot.context.exceptions.WicketSpringBootException;

/**
 * This exception should be thrown if a extension misconfiguration is detected.
 * 
 * It can e.g. thrown if you detect an extension which configures the same aspect
 * like two serializers
 * 
 * @author Marc Giffing
 *
 */
public class ExtensionMisconfigurationException extends WicketSpringBootException {

	public ExtensionMisconfigurationException(String message) {
		super(message);
	}

}
