package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.devutils.statelesschecker;

import lombok.RequiredArgsConstructor;
import org.apache.wicket.devutils.stateless.StatelessChecker;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;

/**
 * Enables the states checker from the Wicket devutils. Its only enabled if the
 * following condition matches
 * <p>
 * 1. The {@link StatelessChecker} is present in the classpath
 * <p>
 * 2. the {@link StatelessCheckerProperties}.statelessCheckerEnabled is set to true
 * (default=false)
 *
 * @author Marc Giffing
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = StatelessCheckerProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@ConditionalOnClass(value = org.apache.wicket.devutils.stateless.StatelessChecker.class)
@EnableConfigurationProperties({StatelessCheckerProperties.class})
@RequiredArgsConstructor
public class StatelessCheckerConfig implements WicketApplicationInitConfiguration {

    private final StatelessCheckerProperties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        webApplication.getComponentPostOnBeforeRenderListeners().add(new StatelessChecker());

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());
    }

}
