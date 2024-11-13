package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.datastore.memcached;

import lombok.RequiredArgsConstructor;
import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.pageStore.IPageStore;
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
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import com.giffing.wicket.spring.boot.context.extensions.types.TypeParser;

import net.spy.memcached.MemcachedClient;


/**
 * Data store auto configuration for the memcached database
 * <p>
 * Enables memcached data store if the following two condition matches:
 * <p>
 * 1. The {@link MemcachedClient} is in the classpath.
 * <p>
 * 2. The property {@link DataStoreMemcachedProperties#PROPERTY_PREFIX}.enabled
 * is true (default = true)
 *
 * @author Marc Giffing
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = DataStoreMemcachedProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass({MemcachedClient.class, MemcachedDataStore.class})
@EnableConfigurationProperties({DataStoreMemcachedProperties.class})
@RequiredArgsConstructor
public class DataStoreMemcachedConfig implements WicketApplicationInitConfiguration {

    private final DataStoreMemcachedProperties prop;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {

        webApplication.setPageManagerProvider(new DefaultPageManagerProvider(webApplication) {
            @Override
            protected IPageStore newPersistentStore() {
                var settings = new MemcachedSettings();
                settings.setExpirationTime(prop.getExpirationTime());
                settings.setPort(prop.getPort());
                settings.setServerNames(prop.getServerNames());
                settings.setShutdownTimeout(TypeParser.parse(prop.getShutdownTimeout(), prop.getShutdownTimeoutUnit()));
                try {
                    var memcachedDataStore = new MemcachedDataStore(webApplication.getName(), settings);
                    return new SessionQuotaManagingDataStore(memcachedDataStore, TypeParser.parse(prop.getSessionSize(), prop.getSessionUnit()));
                } catch (Exception e) {
                    throw new WicketSpringBootException(e);
                }
            }
        });

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", prop)
                .build());

    }
}
