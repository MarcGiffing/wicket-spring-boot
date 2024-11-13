package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.springboot.devtools;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.devtools.autoconfigure.LocalDevToolsAutoConfiguration;
import org.springframework.boot.devtools.restart.ConditionalOnInitializedRestarter;

/**
 * The Wicket serializer does not working with Spring Boot Devtools so we have to provide a custom {@link SpringDevToolsSerializer}.
 * It should be active by default if the following conditions matches.
 * <p>
 * 1. The {@link LocalDevToolsAutoConfiguration} class is in the classpath. This means that the Spring Boot Devtools is available in the classpath.
 * <p>
 * 2. The property "spring.devtools.restart.enabled" is set to true. (default is true). There is maybe a better option to check
 * <p>
 * 3. Restarter condition is active {@link ConditionalOnInitializedRestarter}.
 *
 * @author Marc Giffing
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = SpringDevToolsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(LocalDevToolsAutoConfiguration.class)
@EnableConfigurationProperties({SpringDevToolsProperties.class})
@ConditionalOnInitializedRestarter
@RequiredArgsConstructor
public class SpringDevtoolsSerializerConfig implements WicketApplicationInitConfiguration {

    private final SpringDevToolsProperties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        webApplication.getFrameworkSettings().setSerializer(new SpringDevToolsSerializer());
        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());
    }
}
