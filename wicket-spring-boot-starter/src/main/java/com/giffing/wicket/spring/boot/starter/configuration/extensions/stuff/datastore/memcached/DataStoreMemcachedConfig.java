package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.datastore.memcached;

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

import com.giffing.wicket.spring.boot.context.exceptions.WicketSpringBootException;
import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.types.TypeParser;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketEndpointRepositoryDefault;

import net.spy.memcached.MemcachedClient;


/**
 * Data store auto configuration for the memcached database
 * 
 * Enables memcached data store if the following two condition matches:
 * 
 * 1. The {@link MemcachedClient} is in the classpath.
 * 
 * 2. The property {@link DataStoreMemcachedProperties#PROPERTY_PREFIX}.enabled
 * is true (default = true)
 * 
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = DataStoreMemcachedProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(MemcachedClient.class)
@EnableConfigurationProperties({ DataStoreMemcachedProperties.class })
public class DataStoreMemcachedConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private DataStoreMemcachedProperties prop;
	
	@Autowired
	private WicketEndpointRepositoryDefault wicketEndpointRepository;

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
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", prop)
				.build());

	}

}
