package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.datastore.cassandra;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.pageStore.IDataStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.datastores.cassandra.CassandraDataStore;
import org.wicketstuff.datastores.cassandra.CassandraSettings;
import org.wicketstuff.datastores.cassandra.ICassandraSettings;
import org.wicketstuff.datastores.common.SessionQuotaManagingDataStore;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.types.TypeParser;

/**
 * Data store auto configuration for the cassandra database
 * 
 * Enables cassandra data store if the following two condition matches:
 * 
 * 1. The "com.datastax.driver.core.Session" is in the classpath.
 * 
 * 2. The property {@link DataStoreCassandraProperties#PROPERTY_PREFIX}.enabled
 * is true (default = true)
 * 
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = DataStoreCassandraProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(name = "com.datastax.driver.core.Session")
@EnableConfigurationProperties({ DataStoreCassandraProperties.class })
@AutoConfigureAfter(CassandraAutoConfiguration.class)
public class DataStoreCassandraConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private DataStoreCassandraProperties prop;

	@Override
	public void init(WebApplication webApplication) {
		final ICassandraSettings settings = new CassandraSettings();
		settings.getContactPoints().addAll(prop.getContactPoints());
		settings.setTableName(prop.getTableName());
		settings.setKeyspaceName(prop.getKeyspaceName());
		settings.setRecordTtl(TypeParser.parse(prop.getRecordTtl(), prop.getRecordTtlUnit()));

		webApplication.setPageManagerProvider(new DefaultPageManagerProvider(webApplication) {
			@Override
			protected IDataStore newDataStore() {
				return new SessionQuotaManagingDataStore(new CassandraDataStore(settings),
						TypeParser.parse(prop.getSessionSize(), prop.getSessionUnit()));
			}
		});

	}

}
