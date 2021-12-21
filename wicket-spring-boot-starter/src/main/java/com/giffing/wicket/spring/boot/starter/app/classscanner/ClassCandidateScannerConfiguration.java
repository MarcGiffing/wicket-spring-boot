package com.giffing.wicket.spring.boot.starter.app.classscanner;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;

@Configuration
public class ClassCandidateScannerConfiguration {

	@Autowired
	private Environment environment;
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private BeanFactory beanFactory;
	
	@Bean
	public WicketClassCandidatesHolder pageCandidates() {
		WicketClassCandidatesHolder result = new WicketClassCandidatesHolder();
		new ClassCandidateScanner(environment, resourceLoader, beanFactory, getClass().getClassLoader(), result).postConstruct();;
		return result;
	}
	
}
