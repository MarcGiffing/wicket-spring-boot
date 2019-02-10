package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.resources;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.resource.loader.ClassStringResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;

@ApplicationInitExtension
public class SpringBootMainClassResourceRegistration implements WicketApplicationInitConfiguration {

	@Autowired
	private WicketClassCandidatesHolder classCandidates;
	
	@Override
	public void init(WebApplication webApplication) {
		if(classCandidates.getSpringBootMainClass() != null) {
			webApplication.getResourceSettings().getStringResourceLoaders().add( new ClassStringResourceLoader( classCandidates.getSpringBootMainClass() ) ); 
		}
		
	}

}
