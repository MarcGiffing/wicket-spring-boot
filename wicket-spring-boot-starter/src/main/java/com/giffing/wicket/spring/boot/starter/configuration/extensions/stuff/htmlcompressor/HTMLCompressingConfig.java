package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.htmlcompressor;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.protocol.http.WebApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketEndpointRepositoryDefault;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

/**
 * Enables the HTML markup compression if the following two conditions matches:
 * 
 * 1. The {@link HtmlCompressingMarkupFactory} class is present
 * 
 * 2. The {@link HTMLCompressingProperties}.compressHTMLEnabled is set to true
 * (default is true).
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = HTMLCompressingProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory.class)
@EnableConfigurationProperties({ HTMLCompressingProperties.class })
public class HTMLCompressingConfig implements WicketApplicationInitConfiguration {
	
	private Logger logger = LoggerFactory.getLogger(HTMLCompressingConfig.class);
	
	@Autowired
	private HTMLCompressingProperties props;

	@Autowired
	private WicketEndpointRepositoryDefault wicketEndpointRepository;
	
	@Override
	public void init(WebApplication webApplication) {
		
		HtmlCompressor compressor = new HtmlCompressor();
		setFeatureConfiguration(compressor);
		webApplication.getMarkupSettings().setMarkupFactory(new HtmlCompressingMarkupFactory(compressor));
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());
	}

	private void setFeatureConfiguration(HtmlCompressor compressor) {
		for(Entry<String, Boolean> entrySet : props.getFeatures().entrySet()){
			Method method = null;
			String capitalizedKey = StringUtils.capitalize(entrySet.getKey());
			String methodname = "set" + capitalizedKey;
			try {
				method = compressor.getClass().getMethod(methodname, boolean.class);
				method.setAccessible(true);
				ReflectionUtils.invokeMethod(method, compressor, entrySet.getValue());
			} catch (Exception e) {
				logger.warn("failed to invoke method: {} with value {}. Exception: {}", methodname, entrySet.getValue(), e.getMessage());
			}
		}
	}
}
