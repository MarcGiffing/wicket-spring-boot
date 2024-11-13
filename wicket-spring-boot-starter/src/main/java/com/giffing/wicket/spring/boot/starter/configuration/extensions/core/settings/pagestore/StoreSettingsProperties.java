package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.pagestore;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.types.SessionUnit;

@ConfigurationProperties(StoreSettingsProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class StoreSettingsProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.settings.pagestore";
	
	/**
	 * Toggle debug settings
	 */
	private boolean enabled;
	
	private Long sessionSize = 2L;

	private SessionUnit sessionUnit = SessionUnit.MEGABYTES;
	
	private Boolean asynchronous;
	
	private Integer asynchronousQueueCapacity;
	
	private String fileStoreFolder;
	
}
