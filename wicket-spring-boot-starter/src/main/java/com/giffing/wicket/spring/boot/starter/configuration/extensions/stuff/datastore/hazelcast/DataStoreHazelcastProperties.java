package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.datastore.hazelcast;

import com.giffing.wicket.spring.boot.context.extensions.types.SessionUnit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = DataStoreHazelcastProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class DataStoreHazelcastProperties {

	public static final String PROPERTY_PREFIX = "wicket.stuff.datastore.hazelcast";

	private boolean enabled = true;

	private Long sessionSize = 2L;

	private SessionUnit sessionUnit = SessionUnit.MEGABYTES;

}
