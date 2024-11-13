package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.serializer.fast2;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = WicketSerializerFast2Properties.PROPERTY_PREFIX)
public class WicketSerializerFast2Properties {

    public static final String PROPERTY_PREFIX = "wicket.stuff.serializer.fast2";

}
