package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.datastore;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = DataStoreHttpSessionProperties.PROPERTY_PREFIX)
public class DataStoreHttpSessionProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.datastore.httpsession";
	
	private boolean enabled = true;
	
	/**
	 * the maximum number of pages the data store can hold
	 */
	private int pagesNumber = 20;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getPagesNumber() {
		return pagesNumber;
	}

	public void setPagesNumber(int pagesNumber) {
		this.pagesNumber = pagesNumber;
	}

	


}
