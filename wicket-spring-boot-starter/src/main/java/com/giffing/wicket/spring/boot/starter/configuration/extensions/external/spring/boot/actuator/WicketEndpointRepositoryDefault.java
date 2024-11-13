package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator;

import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WicketEndpointRepositoryDefault implements WicketEndpointRepository {

    private final List<WicketAutoConfig> configs = new ArrayList<>();

    public void add(WicketAutoConfig autoconfig) {
        this.configs.add(autoconfig);
    }

}
