package com.giffing.wicket.spring.boot.starter.configuration.extensions;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory;

import com.giffing.wicket.spring.boot.starter.WicketProperties;
import com.giffing.wicket.spring.boot.starter.configuration.WicketApplicationInitConfiguration;

/**
 * Enables the HTML markup compression if the following two conditions matches:
 * 
 * 1. The {@link HtmlCompressingMarkupFactory} class is present
 * 
 * 2. The {@link WicketProperties}.compressHTMLEnabled is set to true (default
 * is true).
 * 
 * @author Marc Giffing
 *
 */
@Component
@ConditionalOnClass(value = org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory.class)
public class HTMLCompressorConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private WicketProperties wicketProperties;

	@Override
	public void init(WebApplication webApplication) {
		if (wicketProperties.getCompressHTMLEnabled()) {
			webApplication.getMarkupSettings().setMarkupFactory(new HtmlCompressingMarkupFactory());
		}

	}
}
