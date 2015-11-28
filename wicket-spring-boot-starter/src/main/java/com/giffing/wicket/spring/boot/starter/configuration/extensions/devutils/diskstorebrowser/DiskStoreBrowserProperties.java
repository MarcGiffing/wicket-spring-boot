package com.giffing.wicket.spring.boot.starter.configuration.extensions.devutils.diskstorebrowser;

import org.apache.wicket.devutils.diskstore.DiskStoreBrowserPage;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = DiskStoreBrowserProperties.PROPERTY_PREFIX)
public class DiskStoreBrowserProperties {
	
	public static final String PROPERTY_PREFIX = "wicket.devutils.diskstorebrowser";
	
	/**
	 * If enabled the {@link DiskStoreBrowserPage} should be mounted test page.
	 * 
	 * It is required that the deployment configuration is set to DEVELOPMENT.
	 */
	private boolean enabled;
	
	/**
	 * The default mount page for the disk store browser.
	 */
	private String mountPage = "devutils/diskstore/browser";
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getMountPage() {
		return mountPage;
	}

	public void setMountPage(String mountPage) {
		this.mountPage = mountPage;
	}
	
}
