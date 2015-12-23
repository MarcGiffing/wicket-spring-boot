package com.giffing.wicket.spring.boot.starter.configuration.extensions.dev.spring.devtools;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.devtools.autoconfigure.LocalDevToolsAutoConfiguration;
import org.springframework.boot.devtools.restart.ConditionalOnInitializedRestarter;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = "spring.devtools.restart", value = "enabled", matchIfMissing = true)
@ConditionalOnClass(LocalDevToolsAutoConfiguration.class)
@ConditionalOnInitializedRestarter
public class SpringDevtoolsSerializerConfig implements WicketApplicationInitConfiguration {

	@Override
	public void init(WebApplication webApplication) {
		webApplication.getFrameworkSettings().setSerializer(new SpringDevToolsSerializer());
	}
	
}
