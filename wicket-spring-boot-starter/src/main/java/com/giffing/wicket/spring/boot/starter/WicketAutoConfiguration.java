package com.giffing.wicket.spring.boot.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.giffing.wicket.spring.boot.starter.app.classscanner.ClassCandidateScanner;
import com.giffing.wicket.spring.boot.starter.configuration.CustomAnnotationBeanNameGenerator;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.WicketExtensionLocation;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.general.GeneralSettingsProperties;
import com.giffing.wicket.spring.boot.starter.web.WicketWebInitializer;

/**
 * The main starter configuration class which will be called by spring.
 * The class is configured in META-INF/spring.factories
 * 
 * @author Marc Giffing
 *
 */
@Configuration
@Import({ 
			ClassCandidateScanner.class,		
			WicketBootWebApplicationAutoConfiguration.class, 
			WicketWebInitializer.class 
		})
@EnableConfigurationProperties({ GeneralSettingsProperties.class })
@ComponentScan(basePackageClasses = WicketExtensionLocation.class, nameGenerator = CustomAnnotationBeanNameGenerator.class)
public class WicketAutoConfiguration {
	
}
