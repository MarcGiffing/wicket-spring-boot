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
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidate;
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
	
	private List<WicketClassCandidate<Page>> homePageCandidates = new ArrayList<>();
	
	private List<WicketClassCandidate<WebPage>> signInPageCandidates = new ArrayList<>();
	
	private List<WicketClassCandidate> springBootApplicationCandidates  = new ArrayList<>();
	
	private List<WicketClassCandidate<Page>> accessDeniedPageCandidates = new ArrayList<>();
	
	private List<WicketClassCandidate<Page>> expiredPageCandidates = new ArrayList<>();
	
	private List<WicketClassCandidate<Page>> internalErrorPageCandidates = new ArrayList<>();
	
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		boolean isWicketBean = false;
		if(beanClass.isAnnotationPresent(WicketHomePage.class)){
			homePageCandidates.add(new WicketClassCandidate<Page>((Class<Page>) beanClass));
			isWicketBean = true;
		}
		
		if(beanClass.isAnnotationPresent(WicketSignInPage.class)){
			signInPageCandidates.add(new WicketClassCandidate<WebPage>((Class<WebPage>) beanClass));
			isWicketBean = true;
		}
		
		if(isWicketBean) {
			return new Object();
		}
		
		if(beanClass.isAnnotationPresent(SpringBootApplication.class)){
			springBootApplicationCandidates.add(new WicketClassCandidate(beanClass));
		}
		
		return super.postProcessBeforeInstantiation(beanClass, beanName);
	}
	
	@Bean
	public WicketClassCandidatesHolder pageCandidates() {
		return new WicketClassCandidatesHolder(
				homePageCandidates, 
				signInPageCandidates, 
				springBootApplicationCandidates);
	}
	
}