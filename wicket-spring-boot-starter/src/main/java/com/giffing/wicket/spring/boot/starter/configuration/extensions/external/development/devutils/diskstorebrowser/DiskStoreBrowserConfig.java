package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.devutils.diskstorebrowser;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.devutils.pagestore.PageStorePage;
import org.apache.wicket.markup.html.pages.BrowserInfoPage;
import org.apache.wicket.pageStore.DiskPageStore;
import org.apache.wicket.pageStore.IPageStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.StoreSettings;
import org.apache.wicket.util.lang.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;

import java.io.File;

/**
 * Mounts the {@link PageStorePage} if the following condition matches
 * 
 * 1. The {@link PageStorePage} is in the classpath
 * 
 * 2. The disk store browser page is enabled in the property (default=false)
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = DiskStoreBrowserProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@ConditionalOnClass(value = PageStorePage.class)
@EnableConfigurationProperties({ DiskStoreBrowserProperties.class })
public class DiskStoreBrowserConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private DiskStoreBrowserProperties properties;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;
	
	@Override
	public void init(WebApplication webApplication) {
		webApplication.setPageManagerProvider(new DefaultPageManagerProvider(webApplication) {
			@Override
			protected IPageStore newPersistentStore() {
				StoreSettings storeSettings = application.getStoreSettings();
				File fileStoreFolder = storeSettings.getFileStoreFolder();
				Bytes maxSizePerSession = storeSettings.getMaxSizePerSession();
				return new DiskPageStore(webApplication.getName(), fileStoreFolder, maxSizePerSession);
			}
		});
		webApplication.mountPage(properties.getMountPage(), BrowserInfoPage.class);
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", properties)
				.build());
	}

}
