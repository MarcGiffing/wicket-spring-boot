package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.devutils.inspector;

import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.devutils.inspector.LiveSessionsPage;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = InspectorProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class InspectorProperties {

    public static final String PROPERTY_PREFIX = "wicket.external.development.devutils.interceptor";

    /**
     * Enables or disabled the mounting of the {@link LiveSessionsPage}.
     */
    private boolean enableLiveSessionsPage;

    /**
     * The relative address on which the {@link LiveSessionsPage} should be mounted
     */
    private String liveSessionPageMount = "devutils/inspector/live-session-page";

}
