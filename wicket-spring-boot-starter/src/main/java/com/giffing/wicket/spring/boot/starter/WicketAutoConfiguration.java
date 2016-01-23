package com.giffing.wicket.spring.boot.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.giffing.wicket.spring.boot.starter.configuration.CustomAnnotationBeanNameGenerator;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.WicketExtensionLocation;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.general.GeneralSettingsProperties;
import com.giffing.wicket.spring.boot.starter.web.WicketWebInitializer;

@Configuration
@EnableConfigurationProperties({ GeneralSettingsProperties.class })
@ConditionalOnWebApplication
@Import({ WicketWebInitializer.class, WebSecurityConfig.class })
@ComponentScan(basePackageClasses = WicketExtensionLocation.class, nameGenerator=CustomAnnotationBeanNameGenerator.class)
public class WicketAutoConfiguration {

}
