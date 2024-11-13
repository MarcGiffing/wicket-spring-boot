package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.pagestore;

import java.io.File;

import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.StoreSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import com.giffing.wicket.spring.boot.context.extensions.types.TypeParser;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = StoreSettingsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@EnableConfigurationProperties({StoreSettingsProperties.class})
@RequiredArgsConstructor
public class StoreSettingsConfig implements WicketApplicationInitConfiguration {

    private final StoreSettingsProperties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        var storeSettings = webApplication.getStoreSettings();
        if (props.getAsynchronous() != null) {
            storeSettings.setAsynchronous(props.getAsynchronous());
        }
        if (props.getAsynchronousQueueCapacity() != null) {
            storeSettings.setAsynchronousQueueCapacity(props.getAsynchronousQueueCapacity());
        }
        if (props.getFileStoreFolder() != null) {
            storeSettings.setFileStoreFolder(new File(props.getFileStoreFolder()));
        }
        storeSettings.setMaxSizePerSession(TypeParser.parse(props.getSessionSize(), props.getSessionUnit()));

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());
    }

}
