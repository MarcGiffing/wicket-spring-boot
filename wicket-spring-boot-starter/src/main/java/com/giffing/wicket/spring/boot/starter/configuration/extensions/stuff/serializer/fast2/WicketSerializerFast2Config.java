package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.serializer.fast2;

import com.giffing.wicket.spring.boot.context.exceptions.extensions.ExtensionMisconfigurationException;
import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.springboot.devtools.SpringDevtoolsSerializerConfig;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.serialize.ISerializer;
import org.apache.wicket.serialize.java.JavaSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.pageserializer.fast2.Fast2WicketSerializer;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = WicketSerializerFast2Properties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.wicketstuff.pageserializer.fast2.Fast2WicketSerializer.class)
@EnableConfigurationProperties({WicketSerializerFast2Properties.class})
@ConditionalOnMissingBean(SpringDevtoolsSerializerConfig.class)
@RequiredArgsConstructor
public class WicketSerializerFast2Config implements WicketApplicationInitConfiguration {

    private final WicketSerializerFast2Properties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        ISerializer currentSerializer = webApplication.getFrameworkSettings().getSerializer();
        if (currentSerializer.getClass().equals(JavaSerializer.class)) {
            webApplication.getFrameworkSettings().setSerializer(new Fast2WicketSerializer());
        } else {
            throw new ExtensionMisconfigurationException("Fast2Config: There is already another serializer configured " + currentSerializer);
        }

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());

    }

}
