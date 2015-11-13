package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.htmlcompressor;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory;

import com.giffing.wicket.spring.boot.starter.configuration.WicketApplicationInitConfiguration;

/**
 * Enables the HTML markup compression if the following two conditions matches:
 * 
 * 1. The {@link HtmlCompressingMarkupFactory} class is present
 * 
 * 2. The {@link WicketStuffProperties}.compressHTMLEnabled is set to true
 * (default is true).
 * 
 * @author Marc Giffing
 *
 */
@Component
@ConditionalOnProperty(prefix = "wicket.wicketstuff.htmlcompressor", value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory.class)
@EnableConfigurationProperties({ HTMLCompressingProperties.class })
public class HTMLCompressingConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private HTMLCompressingProperties props;

	@Override
	public void init(WebApplication webApplication) {
		webApplication.getMarkupSettings().setMarkupFactory(new HtmlCompressingMarkupFactory());
	}
}
