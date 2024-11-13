package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.datastore.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.types.DurationUnit;
import com.giffing.wicket.spring.boot.context.extensions.types.SessionUnit;

@ConfigurationProperties(prefix = DataStoreRedisProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class DataStoreRedisProperties {

    public static final String PROPERTY_PREFIX = "wicket.stuff.datastore.redis";

    private boolean enabled = true;

    private Long sessionSize = 2L;

    private SessionUnit sessionUnit = SessionUnit.MEGABYTES;

    private String hostname = "127.0.0.1";

    private int port = 6379;

    private Long recordTtl = 30L;

    private DurationUnit recordTtlUnit = DurationUnit.MINUTES;

}
