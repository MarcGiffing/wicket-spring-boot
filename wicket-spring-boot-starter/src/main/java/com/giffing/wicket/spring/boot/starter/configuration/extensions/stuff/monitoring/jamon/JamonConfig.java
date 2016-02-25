package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.monitoring.jamon;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.jamon.request.cycle.JamonAwareRequestCycleListener;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = JamonProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = JamonAwareRequestCycleListener.class)
@EnableConfigurationProperties({ JamonProperties.class })
public class JamonConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private JamonProperties prop;
	
	@Override
	public void init(WebApplication webApplication) {
		webApplication.getRequestCycleListeners().add(new JamonAwareRequestCycleListener(webApplication, prop.isIncludeSourceNameInMonitorLabel()));
		webApplication.mountPage(prop.getMountPage(), BootJamonAdminPage.class);
	}

}
