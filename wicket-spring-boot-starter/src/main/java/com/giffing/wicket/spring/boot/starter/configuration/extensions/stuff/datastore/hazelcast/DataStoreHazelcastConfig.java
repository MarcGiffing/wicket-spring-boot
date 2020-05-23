package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.datastore.hazelcast;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.pageStore.IPageStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.datastores.common.SessionQuotaManagingDataStore;
import org.wicketstuff.datastores.hazelcast.HazelcastDataStore;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import com.giffing.wicket.spring.boot.context.extensions.types.TypeParser;
import com.hazelcast.core.HazelcastInstance;

/**
 * Data store auto configuration for the hazelcast database
 * 
 * Enables hazelcast data store if the following two condition matches:
 * 
 * 1. The {@link HazelcastInstance} is in the classpath.
 * 
 * 2. The property {@link DataStoreHazelcastProperties#PROPERTY_PREFIX}.enabled
 * is true (default = true)
 * 
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = DataStoreHazelcastProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnBean(HazelcastInstance.class)
@ConditionalOnClass({HazelcastInstance.class, HazelcastDataStore.class})
@EnableConfigurationProperties({ DataStoreHazelcastProperties.class })
@AutoConfigureAfter(HazelcastAutoConfiguration.class)
public class DataStoreHazelcastConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private DataStoreHazelcastProperties prop;
	
	@Autowired
	private HazelcastInstance hazelcastInstance;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;

	@Override
	public void init(WebApplication webApplication) {
		
		webApplication.setPageManagerProvider(new DefaultPageManagerProvider(webApplication) {
			@Override
			protected IPageStore newPersistentStore() {
				HazelcastDataStore hazelcastDataStore = new HazelcastDataStore(webApplication.getName(), hazelcastInstance);
				return new SessionQuotaManagingDataStore(hazelcastDataStore, TypeParser.parse(prop.getSessionSize(), prop.getSessionUnit()));
			}
		});
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", prop)
				.build());

	}

}
