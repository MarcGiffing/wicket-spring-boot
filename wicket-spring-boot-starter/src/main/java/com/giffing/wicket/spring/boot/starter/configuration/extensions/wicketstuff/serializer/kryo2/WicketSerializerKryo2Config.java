package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.serializer.kryo2;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.serialize.java.JavaSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.wicketstuff.pageserializer.kryo2.KryoSerializer;

import com.giffing.wicket.spring.boot.starter.configuration.WicketApplicationInitConfiguration;

@Component
@ConditionalOnProperty(prefix = "wicket.wicketstuff.serializer.kryo2", value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.wicketstuff.pageserializer.kryo2.KryoSerializer.class)
@EnableConfigurationProperties({ WicketSerializerKryo2Properties.class })
public class WicketSerializerKryo2Config implements WicketApplicationInitConfiguration {

	@Override
	public void init(WebApplication webApplication) {
		//only set the kryo serializer if its the default serialiter
		if(webApplication.getFrameworkSettings().getSerializer() instanceof JavaSerializer){
			webApplication.getFrameworkSettings().setSerializer(new KryoSerializer());
		}
	}

}
