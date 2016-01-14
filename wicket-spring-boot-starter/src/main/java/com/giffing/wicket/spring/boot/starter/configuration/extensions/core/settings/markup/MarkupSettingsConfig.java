package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.markup;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.MarkupSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

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
	private MarkupSettingsProperties markupSettingsProperties;
	
	@Override
	public void init(WebApplication webApplication) {
		MarkupSettings markupSettings = webApplication.getMarkupSettings();

		if(markupSettingsProperties.getDefaultMarkupEncoding() != null){
			markupSettings.setDefaultMarkupEncoding(markupSettingsProperties.getDefaultMarkupEncoding());
		}
		
		markupSettings.setAutomaticLinking(markupSettingsProperties.isAutomaticLinking());
		markupSettings.setCompressWhitespace(markupSettingsProperties.isCompressWhitespace());
		markupSettings.setStripComments(markupSettingsProperties.isStripComments());
		markupSettings.setStripWicketTags(markupSettingsProperties.isStripWicketTags());
		markupSettings.setThrowExceptionOnMissingXmlDeclaration(markupSettingsProperties.isThrowExceptionOnMissingXmlDeclaration());
		
	}

}
