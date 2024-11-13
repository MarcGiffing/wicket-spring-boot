package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.wicketsource;

import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import com.github.jennybrown8.wicketsource.WicketSource;

/**
 * Enables wicket-source support if the following two condition matches:
 * <p>
 * 1. The WicketSource is in the classpath.
 * <p>
 * 2. The property wicket.wicketsource.enabled is true (default = true)
 *
 * @author Marc Giffing
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = WicketSourceProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = com.github.jennybrown8.wicketsource.WicketSource.class)
@EnableConfigurationProperties({WicketSourceProperties.class})
@RequiredArgsConstructor
public class WicketSourceConfig implements WicketApplicationInitConfiguration {

    private final WicketSourceProperties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        WicketSource.configure(webApplication);

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());

    }

}
