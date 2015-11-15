package com.giffing.wicket.spring.boot.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.giffing.wicket.spring.boot.starter.configuration.CustomAnnotationBeanNameGenerator;
import com.giffing.wicket.spring.boot.starter.configuration.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.web.WebInitializer;

@Configuration
@EnableConfigurationProperties({ WicketProperties.class })
@ConditionalOnWebApplication
@Import({ WebInitializer.class, WebSecurityConfig.class })
@ComponentScan(basePackageClasses = WicketApplicationInitConfiguration.class, nameGenerator=CustomAnnotationBeanNameGenerator.class)
public class WicketAutoConfiguration {

}
