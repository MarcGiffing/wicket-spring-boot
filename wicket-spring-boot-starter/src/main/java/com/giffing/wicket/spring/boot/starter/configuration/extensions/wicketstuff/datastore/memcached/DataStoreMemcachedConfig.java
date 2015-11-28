package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.memcached;

import java.io.IOException;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.pageStore.IDataStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.datastores.common.SessionQuotaManagingDataStore;
import org.wicketstuff.datastores.memcached.IMemcachedSettings;
import org.wicketstuff.datastores.memcached.MemcachedDataStore;
import org.wicketstuff.datastores.memcached.MemcachedSettings;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.TypeParser;
import com.giffing.wicket.spring.boot.starter.exception.WicketSpringBootException;

import net.spy.memcached.MemcachedClient;


@ApplicationInitExtension
@ConditionalOnProperty(prefix = DataStoreMemcachedProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(MemcachedClient.class)
@EnableConfigurationProperties({ DataStoreMemcachedProperties.class })
public class DataStoreMemcachedConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private DataStoreMemcachedProperties prop;
	

	@Override
	public void init(WebApplication webApplication) {
		
		webApplication.setPageManagerProvider(new DefaultPageManagerProvider(webApplication) {
			protected IDataStore newDataStore() {
				IMemcachedSettings settings = new MemcachedSettings();
				settings.setExpirationTime(prop.getExpirationTime());
				settings.setPort(prop.getPort());
				settings.setServerNames(prop.getServerNames());
				settings.setShutdownTimeout(TypeParser.parse(prop.getShutdownTimeout(), prop.getShutdownTimeoutUnit()));
				MemcachedDataStore memcachedDataStore;
				
				try {
					memcachedDataStore = new MemcachedDataStore(settings);
				} catch (IOException e) {
					throw new WicketSpringBootException(e);
				}
				
				return new SessionQuotaManagingDataStore(memcachedDataStore, TypeParser.parse(prop.getSessionSize(), prop.getSessionUnit()));
			}
		});

	}

}
