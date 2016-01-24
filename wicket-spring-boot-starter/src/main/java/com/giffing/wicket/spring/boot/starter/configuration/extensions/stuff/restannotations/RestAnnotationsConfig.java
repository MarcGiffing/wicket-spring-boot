package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.restannotations;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.rest.utils.mounting.PackageScanner;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = RestAnnotationsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = PackageScanner.class)
@EnableConfigurationProperties({ RestAnnotationsProperties.class })
public class RestAnnotationsConfig implements WicketApplicationInitConfiguration {
	
	@Autowired
	private RestAnnotationsProperties prop;
	
	@Override
	public void init(WebApplication webApplication) {
		String packagename = webApplication.getClass().getPackage().getName();
		if (prop.getPackagename() != null) {
			packagename = prop.getPackagename();
		}
		
		PackageScanner.scanPackage(webApplication, packagename);
	}

}
