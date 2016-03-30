package com.giffing.wicket.spring.boot.starter.app.classscanner;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.giffing.wicket.spring.boot.context.scan.WicketAccessDeniedPage;
import com.giffing.wicket.spring.boot.context.scan.WicketExpiredPage;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.giffing.wicket.spring.boot.context.scan.WicketInternalErrorPage;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidate;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;

/**
 * The candidate class scanner is used to find class with special annotations.
 * 
 * @author Marc Giffing
 *
 */
@Configuration
public class ClassCandidateScanner implements BeanClassLoaderAware {
	
	@Autowired
	private Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private BeanFactory beanFactory;
	
	private ClassLoader classLoader;

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
	
	@Bean
	public WicketClassCandidatesHolder pageCandidates() {
		return new WicketClassCandidatesHolder();
	}
	
	@PostConstruct
	public void postConstruct() {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
				false);
		scanner.setEnvironment(this.environment);
		scanner.setResourceLoader(this.resourceLoader);
		scanner.addIncludeFilter(new AnnotationTypeFilter(WicketHomePage.class));
		scanner.addIncludeFilter(new AnnotationTypeFilter(WicketSignInPage.class));
	    scanner.addIncludeFilter(new AnnotationTypeFilter(WicketAccessDeniedPage.class));
	    scanner.addIncludeFilter(new AnnotationTypeFilter(WicketExpiredPage.class));
	    scanner.addIncludeFilter(new AnnotationTypeFilter(WicketInternalErrorPage.class));
	    for (String basePackage : getMappingBasePackages(beanFactory)) {
			if (StringUtils.hasText(basePackage)) {
				pageCandidates().getBasePackages().add(basePackage);
				Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(basePackage);
				for (BeanDefinition beanDefinition : beanDefinitions) {
					Class<?> beanClass;
					try {
						beanClass = ClassUtils.forName(beanDefinition.getBeanClassName(), classLoader);
					} catch (ClassNotFoundException e) {
						throw new IllegalStateException(e);
					}
					if(beanClass.isAnnotationPresent(WicketHomePage.class)){
						pageCandidates().getHomePageCandidates().add(new WicketClassCandidate<Page>(new WeakReference<>((Class<Page>) beanClass)));
					}
					if(beanClass.isAnnotationPresent(WicketSignInPage.class)){
						pageCandidates().getSignInPageCandidates().add(new WicketClassCandidate<WebPage>(new WeakReference<>((Class<WebPage>) beanClass)));
					}
					if(beanClass.isAnnotationPresent(WicketAccessDeniedPage.class)){
						pageCandidates().getAccessDeniedPageCandidates().add(new WicketClassCandidate<Page>(new WeakReference<>((Class<Page>) beanClass)));
					}
					if(beanClass.isAnnotationPresent(WicketExpiredPage.class)){
						pageCandidates().getExpiredPageCandidates().add(new WicketClassCandidate<Page>(new WeakReference<>((Class<Page>) beanClass)));
					}
					if(beanClass.isAnnotationPresent(WicketInternalErrorPage.class)){
						pageCandidates().getInternalErrorPageCandidates().add(new WicketClassCandidate<Page>(new WeakReference<>((Class<Page>) beanClass)));
					}
				}
				
			}
	    }
	}
	
	private static Collection<String> getMappingBasePackages(BeanFactory beanFactory) {
		try {
			return AutoConfigurationPackages.get(beanFactory);
		}
		catch (IllegalStateException ex) {
			// no auto-configuration package registered yet
			return Collections.emptyList();
		}
	}
	
}