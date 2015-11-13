package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.htmlcompressor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map.Entry;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory;

import com.giffing.wicket.spring.boot.starter.configuration.WicketApplicationInitConfiguration;
import com.googlecode.htmlcompressor.compressor.Compressor;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

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
		HtmlCompressor compressor = new HtmlCompressor();
		for(Entry<String, Boolean> entrySet : props.getProperties().entrySet()){
			Method method;
			try {
				String capitalizedKey = StringUtils.capitalize(entrySet.getKey());
				method = compressor.getClass().getMethod("set" + capitalizedKey, boolean.class);
				method.setAccessible(true);
			} catch (NoSuchMethodException e) {
				throw new IllegalStateException(e);
			}
			ReflectionUtils.invokeMethod(method, compressor, entrySet.getValue());
		}
		webApplication.getMarkupSettings().setMarkupFactory(new HtmlCompressingMarkupFactory(compressor));
	}
}
