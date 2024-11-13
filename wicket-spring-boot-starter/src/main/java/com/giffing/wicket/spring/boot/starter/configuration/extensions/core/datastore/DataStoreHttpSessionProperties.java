package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.datastore;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = DataStoreHttpSessionProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class DataStoreHttpSessionProperties {

    public static final String PROPERTY_PREFIX = "wicket.core.datastore.httpsession";

    private boolean enabled = true;

    /**
     * the maximum number of pages the data store can hold
     */
    private int pagesNumber = 20;

}
