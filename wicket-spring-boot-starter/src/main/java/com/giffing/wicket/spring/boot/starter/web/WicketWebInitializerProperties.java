package com.giffing.wicket.spring.boot.starter.web;

import jakarta.servlet.DispatcherType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = WicketWebInitializerProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class WicketWebInitializerProperties {

    public static final String PROPERTY_PREFIX = "wicket.web.servlet";

    private String filterMappingParam = "/*";

    // Adds possibility to add init parameters dynamically
    private Map<String, String> initParameters = new HashMap<>();

    private List<DispatcherType> dispatcherTypes = List.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);

    private boolean filterMatchAfter;

}
