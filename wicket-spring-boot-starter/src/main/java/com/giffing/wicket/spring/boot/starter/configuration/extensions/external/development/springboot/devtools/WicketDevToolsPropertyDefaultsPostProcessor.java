package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.springboot.devtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.Restarter;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.LOWEST_PRECEDENCE)
//TODO add feature request in the Spring Boot project for adding custom development properties 
public class WicketDevToolsPropertyDefaultsPostProcessor implements EnvironmentPostProcessor {

    private static final Map<String, Object> PROPERTIES;

    static {
        Map<String, Object> properties = new HashMap<>();
        properties.put("wicket.core.settings.general.configuration-type", "development");
        properties.put("wicket.core.settings.markup.strip-wicket-tags", "false");
        properties.put("wicket.core.settings.debug.component-use-check", "true");
        properties.put("wicket.core.settings.debug.development-utilities-enabled", "true");
        properties.put("wicket.core.settings.debug.ajax-debug-mode-enabled", "true");


        PROPERTIES = Collections.unmodifiableMap(properties);
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if (Boolean.TRUE.equals(isWicketDevToolsEnabled(environment)
                && isLocalApplication(environment))
                && canAddProperties(environment)) {
            var propertySource = new MapPropertySource("wicketrefresh", PROPERTIES);
            environment.getPropertySources().addLast(propertySource);
        }
    }

    private Boolean isWicketDevToolsEnabled(ConfigurableEnvironment environment) {
        var devToolsEnabled = Boolean.TRUE;
        var devToolsEnabledString = environment.getProperty("wicket." + SpringDevToolsProperties.PROPERTY_PREFIX + ".enabled");
        if (devToolsEnabledString != null) {
            devToolsEnabled = devToolsEnabledString.equalsIgnoreCase("true");
        }
        return devToolsEnabled;
    }

    private boolean isLocalApplication(ConfigurableEnvironment environment) {
        return environment.getPropertySources().get("remoteUrl") == null;
    }

    private boolean canAddProperties(Environment environment) {
        return isRestarterInitialized() || isRemoteRestartEnabled(environment);
    }

    private boolean isRestarterInitialized() {

        try {
            Restarter restarter = Restarter.getInstance();
            return (restarter != null && restarter.getInitialUrls() != null);
        } catch (NoClassDefFoundError | Exception ex) {
            return false;
        }
    }

    private boolean isRemoteRestartEnabled(Environment environment) {
        return environment.containsProperty("spring.devtools.remote.secret");
    }

}
