package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.datastore.memcached;

import com.giffing.wicket.spring.boot.context.extensions.types.DurationUnit;
import com.giffing.wicket.spring.boot.context.extensions.types.SessionUnit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = DataStoreMemcachedProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class DataStoreMemcachedProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.datastore.memcached";

	private boolean enabled = true;

	private Long sessionSize = 2L;

	private SessionUnit sessionUnit = SessionUnit.MEGABYTES;
	
	private int expirationTime  = 30;
	
	private int port = 11211;
	
	private String serverNames;
	
	private long shutdownTimeout = 30;
	
	private DurationUnit shutdownTimeoutUnit  = DurationUnit.MINUTES;

}
