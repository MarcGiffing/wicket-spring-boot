package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.serializer.kryo2;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = WicketSerializerKryo2Properties.PROPERTY_PREFIX)
public class WicketSerializerKryo2Properties {

    public static final String PROPERTY_PREFIX = "wicket.stuff.serializer.kryo2";

}