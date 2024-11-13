package com.giffing.wicket.spring.boot.starter.app.classscanner.candidates;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;

/**
 * Holds class candidates which should be configured in Wicket.
 * It holds e.g. home page classes which should be configured in Wicket.
 * 
 * @author Marc Giffing
 *
 */
@Getter
@Setter
public class WicketClassCandidatesHolder {
	
	private Set<String> basePackages = new HashSet<>();
	
	private Class<?> springBootMainClass = null;
	
	private Set<WicketClassCandidate<Page>> homePageCandidates = new HashSet<>();
	
	private Set<WicketClassCandidate<WebPage>> signInPageCandidates = new HashSet<>();
	
	private Set<WicketClassCandidate<Page>> accessDeniedPageCandidates = new HashSet<>();
	
	private Set<WicketClassCandidate<Page>> expiredPageCandidates = new HashSet<>();
	
	private Set<WicketClassCandidate<Page>> internalErrorPageCandidates = new HashSet<>();

}
