package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.redis;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.pageStore.IDataStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.datastores.common.SessionQuotaManagingDataStore;
import org.wicketstuff.datastores.redis.IRedisSettings;
import org.wicketstuff.datastores.redis.RedisDataStore;
import org.wicketstuff.datastores.redis.RedisSettings;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.datastore.TypeParser;

import redis.clients.jedis.Jedis;


@ApplicationInitExtension
@ConditionalOnProperty(prefix = DataStoreRedisProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(Jedis.class)
@EnableConfigurationProperties({ DataStoreRedisProperties.class })
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class DataStoreRedisConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private DataStoreRedisProperties prop;
	
	@Override
	public void init(WebApplication webApplication) {
		
		webApplication.setPageManagerProvider(new DefaultPageManagerProvider(webApplication) {
			protected IDataStore newDataStore() {
				IRedisSettings settings = new RedisSettings();
				settings.setHostname(prop.getHostname());
				settings.setPort(prop.getPort());
				settings.setRecordTtl(TypeParser.parse(prop.getRecordTtl(), prop.getRecordTtlUnit()));
				
				RedisDataStore redisDataStore = new RedisDataStore(settings );
				return new SessionQuotaManagingDataStore(redisDataStore, TypeParser.parse(prop.getSessionSize(), prop.getSessionUnit()));
			}
		});

	}

}
