package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.markup;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.MarkupSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketEndpointRepository;

/**
 * Configuration for the markup settings.
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@EnableConfigurationProperties({ MarkupSettingsProperties.class })
public class MarkupSettingsConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private MarkupSettingsProperties props;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;
	
	@Override
	public void init(WebApplication webApplication) {
		MarkupSettings markupSettings = webApplication.getMarkupSettings();

		if(props.getDefaultMarkupEncoding() != null){
			markupSettings.setDefaultMarkupEncoding(props.getDefaultMarkupEncoding());
		}
		
		markupSettings.setAutomaticLinking(props.isAutomaticLinking());
		markupSettings.setCompressWhitespace(props.isCompressWhitespace());
		markupSettings.setStripComments(props.isStripComments());
		markupSettings.setStripWicketTags(props.isStripWicketTags());
		markupSettings.setThrowExceptionOnMissingXmlDeclaration(props.isThrowExceptionOnMissingXmlDeclaration());
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());
		
	}

}
