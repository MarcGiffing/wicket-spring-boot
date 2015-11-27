package com.giffing.wicket.spring.boot.starter.configuration.extensions.devutils.diskstorebrowser;

import org.apache.wicket.devutils.diskstore.DebugPageManagerProvider;
import org.apache.wicket.devutils.diskstore.DiskStoreBrowserPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

/**
 * Mounts the {@link DiskStoreBrowserPage} if the following condition matches
 * 
 * 1. The {@link DiskStoreBrowserPage} is in the classpath
 * 
 * 2. The disk store browser page is enabled in the property (default=false)
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = "wicket.devutils.diskstorebrowser", value = "enabled", matchIfMissing = false)
@ConditionalOnClass(value = org.apache.wicket.devutils.diskstore.DiskStoreBrowserPage.class)
@EnableConfigurationProperties({ DiskStoreBrowserProperties.class })
public class DiskStoreBrowserConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private DiskStoreBrowserProperties properties;
	
	@Override
	public void init(WebApplication webApplication) {
		DebugPageManagerProvider pageManager = new DebugPageManagerProvider(webApplication);
		webApplication.setPageManagerProvider(pageManager);
		webApplication.mountPage(properties.getMountPage(), DiskStoreBrowserPage.class);
	}

}
