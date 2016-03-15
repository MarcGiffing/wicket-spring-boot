package com.giffing.wicket.spring.boot.starter;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import com.giffing.wicket.spring.boot.starter.app.HomePageCandiate;
import com.giffing.wicket.spring.boot.starter.app.PageCandiates;
import com.giffing.wicket.spring.boot.starter.app.SignInPageCandiate;
import com.giffing.wicket.spring.boot.starter.configuration.CustomAnnotationBeanNameGenerator;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.WicketExtensionLocation;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.general.GeneralSettingsProperties;
import com.giffing.wicket.spring.boot.starter.web.WicketWebInitializer;

@Configuration
@Import({ 
			WicketBootWebApplicationAutoConfiguration.class, 
			WicketWebInitializer.class 
		})
@EnableConfigurationProperties({ GeneralSettingsProperties.class })
@ComponentScan(basePackageClasses = WicketExtensionLocation.class, nameGenerator = CustomAnnotationBeanNameGenerator.class)
public class WicketAutoConfiguration extends AutowiredAnnotationBeanPostProcessor {
	
	private List<HomePageCandiate> homePageCandiates = new ArrayList<>();
	
	private List<SignInPageCandiate> signInPageCandiates = new ArrayList<>();
	
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if(beanClass.isAnnotationPresent(WicketHomePage.class)){
			homePageCandiates.add(new HomePageCandiate((Class<Page>) beanClass));
			return new Object();
		}
		if(beanClass.isAnnotationPresent(WicketSignInPage.class)){
			signInPageCandiates.add(new SignInPageCandiate((Class<WebPage>) beanClass));
			return new Object();
		}
		return super.postProcessBeforeInstantiation(beanClass, beanName);
	}
	
	@Bean
	public PageCandiates pageCandiates() {
		return new PageCandiates(homePageCandiates, signInPageCandiates);
	}
	
}
