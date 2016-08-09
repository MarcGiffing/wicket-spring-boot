package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.pagestore;

import java.io.File;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.StoreSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.types.TypeParser;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketEndpointRepositoryDefault;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = StoreSettingsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@EnableConfigurationProperties({ StoreSettingsProperties.class })
public class StoreSettingsConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private StoreSettingsProperties props;

	@Autowired
	private WicketEndpointRepositoryDefault wicketEndpointRepository;
	
	@Override
	public void init(WebApplication webApplication) {
		StoreSettings storeSettings = webApplication.getStoreSettings();
		if (props.getAsynchronous() != null) {
			storeSettings.setAsynchronous(props.getAsynchronous());
		}
		if (props.getAsynchronousQueueCapacity() != null) {
			storeSettings.setAsynchronousQueueCapacity(props.getAsynchronousQueueCapacity());
		}
		if (props.getFileStoreFolder() != null) {
			storeSettings.setFileStoreFolder(new File(props.getFileStoreFolder()));
		}
		if (props.getInmemoryCacheSize() != null) {
			storeSettings.setInmemoryCacheSize(props.getInmemoryCacheSize());
		}
		storeSettings.setMaxSizePerSession(TypeParser.parse(props.getSessionSize(), props.getSessionUnit()));
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());
	}

}
