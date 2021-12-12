package com.giffing.wicket.spring.boot.starter.app.classscanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;

@Configuration
@Import(ClassCandidateScanner.class)
public class ClassCandidateScannerConfiguration {

	@Bean
	public WicketClassCandidatesHolder pageCandidates() {
		return new WicketClassCandidatesHolder();
	}
	
}
