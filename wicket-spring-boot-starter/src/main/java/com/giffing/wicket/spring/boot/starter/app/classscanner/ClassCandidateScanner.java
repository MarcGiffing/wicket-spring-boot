package com.giffing.wicket.spring.boot.starter.app.classscanner;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.HomePageCandidate;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.SignInPageCandidates;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.SpringBootApplicationCandidates;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;

/**
 * The candidate class scanner is used to find class with special annotations.
 * 
 * You can't create beans of Wicket pages cause they have to be created in a Wicket
 * context. You will get errors if. Therefore the {@link AutowiredAnnotationBeanPostProcessor}
 * is used to prevent the creation of the beans and get only the classes instead.
 * 
 * @author Marc Giffing
 *
 */
@Configuration
public class ClassCandidateScanner extends AutowiredAnnotationBeanPostProcessor {
	
	private List<HomePageCandidate> homePageCandidates = new ArrayList<>();
	
	private List<SignInPageCandidates> signInPageCandidates = new ArrayList<>();
	
	private List<SpringBootApplicationCandidates> springBootApplicationCandidates  = new ArrayList<>();
	
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if(beanClass.isAnnotationPresent(WicketHomePage.class)){
			homePageCandidates.add(new HomePageCandidate((Class<Page>) beanClass));
			return new Object();
		}
		if(beanClass.isAnnotationPresent(WicketSignInPage.class)){
			signInPageCandidates.add(new SignInPageCandidates((Class<WebPage>) beanClass));
			return new Object();
		}
		if(beanClass.isAnnotationPresent(SpringBootApplication.class)){
			springBootApplicationCandidates.add(new SpringBootApplicationCandidates(beanClass));
		}
		
		return super.postProcessBeforeInstantiation(beanClass, beanName);
	}
	
	@Bean
	public WicketClassCandidatesHolder pageCandidates() {
		return new WicketClassCandidatesHolder(homePageCandidates, signInPageCandidates, springBootApplicationCandidates);
	}
	
}