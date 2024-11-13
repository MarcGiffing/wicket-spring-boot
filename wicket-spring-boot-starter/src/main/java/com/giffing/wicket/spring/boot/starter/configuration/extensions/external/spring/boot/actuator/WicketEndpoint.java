package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator;

import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;


@ConfigurationProperties(prefix = "endpoints.wicket")
@Endpoint(id = "wicket")
@RequiredArgsConstructor
public class WicketEndpoint {

    private final WicketEndpointRepository repository;

    @ReadOperation
    public Map<String, Object> wicketExtensions() {
        Map<String, Object> result = new HashMap<>();
        result.put("extensions", repository.getConfigs());
        return result;
    }

}
