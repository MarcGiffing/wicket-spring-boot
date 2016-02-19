package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.datastore;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.page.DefaultPageManagerContext;
import org.apache.wicket.pageStore.IDataStore;
import org.apache.wicket.pageStore.memory.HttpSessionDataStore;
import org.apache.wicket.pageStore.memory.PageNumberEvictionStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = DataStoreHttpSessionProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@EnableConfigurationProperties({ DataStoreHttpSessionProperties.class })
@AutoConfigureAfter(HttpSessionDataStore.class)
public class DataStoreHttpSessionConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private DataStoreHttpSessionProperties prop;

	@Override
	public void init(WebApplication webApplication) {

		webApplication.setPageManagerProvider(new DefaultPageManagerProvider(webApplication) {
			@Override
			protected IDataStore newDataStore() {
				return new HttpSessionDataStore(new DefaultPageManagerContext(), new PageNumberEvictionStrategy(prop.getPagesNumber()));
			}
		});

	}

}
