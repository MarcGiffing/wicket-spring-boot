package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.devutils.inspector;

import lombok.RequiredArgsConstructor;
import org.apache.wicket.devutils.inspector.LiveSessionsPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;

/**
 * Mounts pages from the devutils inspector package. Currently only the
 * {@link LiveSessionsPage} is supported and is mounted if the following
 * condition matches.
 * <p>
 * 1. The {@link LiveSessionsPage} is in the classpath.
 * <p>
 * 2. The property enableLiveSessionsPage is enabled
 *
 * @author Marc Giffing
 */
@ApplicationInitExtension
@ConditionalOnClass(value = {org.apache.wicket.devutils.inspector.LiveSessionsPage.class,})
@EnableConfigurationProperties({InspectorProperties.class})
@RequiredArgsConstructor
public class InspectorConfig implements WicketApplicationInitConfiguration {

    private final InspectorProperties properties;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        if (properties.isEnableLiveSessionsPage()) {
            webApplication.mountPage(properties.getLiveSessionPageMount(), LiveSessionsPage.class);
        }

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", properties)
                .build());
    }

}
