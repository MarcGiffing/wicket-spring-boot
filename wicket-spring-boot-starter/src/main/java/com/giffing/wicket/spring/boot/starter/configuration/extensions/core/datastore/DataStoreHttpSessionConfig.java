package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.datastore;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.pageStore.IPageStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import org.wicketstuff.shiro.wicket.page.store.SessionPageStore;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = DataStoreHttpSessionProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@EnableConfigurationProperties({ DataStoreHttpSessionProperties.class })
@AutoConfigureAfter(SessionPageStore.class)
public class DataStoreHttpSessionConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private DataStoreHttpSessionProperties props;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;

	@Override
	public void init(WebApplication webApplication) {

		webApplication.setPageManagerProvider(new DefaultPageManagerProvider(webApplication) {
			@Override
			protected IPageStore newSessionStore(final IPageStore pageStore) {
				return new SessionPageStore(webApplication.getName(), props.getPagesNumber());
			}
		});
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());

	}

}
