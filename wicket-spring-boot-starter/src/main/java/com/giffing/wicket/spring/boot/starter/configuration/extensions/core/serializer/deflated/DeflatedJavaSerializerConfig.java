package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.serializer.deflated;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.serialize.ISerializer;
import org.apache.wicket.serialize.java.DeflatedJavaSerializer;
import org.apache.wicket.serialize.java.JavaSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.exceptions.extensions.ExtensionMisconfigurationException;
import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator.WicketEndpointRepository;


/**
 * Enables the deflated java serializer if the following condition matches.
 * 
 * 1. The property {@link DeflatedJavaSerializerProperties#PROPERTY_PREFIX}.enabled has to be true (default = false)
 *
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = DeflatedJavaSerializerProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@EnableConfigurationProperties({ DeflatedJavaSerializerProperties.class })
public class DeflatedJavaSerializerConfig implements WicketApplicationInitConfiguration {
	
	@Autowired
	private DeflatedJavaSerializerProperties props;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;
	
	@Override
	public void init(WebApplication webApplication) {
		ISerializer currentSerializer = webApplication.getFrameworkSettings().getSerializer();
		if (currentSerializer instanceof JavaSerializer) {
			webApplication.getFrameworkSettings().setSerializer(new DeflatedJavaSerializer(webApplication.getApplicationKey()));
		} else {
			throw new ExtensionMisconfigurationException("DeflatedJavaSerializer: There is already another serializer configured " + currentSerializer);
		}
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());
	}

}
