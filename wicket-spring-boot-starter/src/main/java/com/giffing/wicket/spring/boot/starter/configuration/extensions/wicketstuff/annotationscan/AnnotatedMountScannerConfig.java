package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.annotationscan;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.giffing.wicket.spring.boot.starter.configuration.WicketApplicationInitConfiguration;

@Component
@ConditionalOnProperty(prefix = "wicket.wicketstuff.annotationscan", value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.wicketstuff.annotation.scan.AnnotatedMountScanner.class)
@EnableConfigurationProperties({ AnnotatedMountScannerProperties.class })
public class AnnotatedMountScannerConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private AnnotatedMountScannerProperties prop;

	@Override
	public void init(WebApplication webApplication) {
		String packagename = this.getClass().getPackage().getName();
		if (prop.getPackagename() != null) {
			packagename = prop.getPackagename();
		}

		new AnnotatedMountScanner().scanPackage(packagename).mount(webApplication);
	}

}
