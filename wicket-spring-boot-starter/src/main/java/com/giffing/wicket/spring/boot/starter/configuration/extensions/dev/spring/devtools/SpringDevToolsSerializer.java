package com.giffing.wicket.spring.boot.starter.configuration.extensions.dev.spring.devtools;

import org.apache.wicket.serialize.ISerializer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

/**
 * A custom serializer is needed to support Spring Boot Devtools. Spring Boot Devtools
 * has some limitation with support of serialisation/deserialization support. So we have to
 * provide a custom Wicket {@link ISerializer}.
 * 
 * <p>
 * <b>20.2.6 Known limitations</b> <br/>
 * Restart functionality does not work well with objects that are deserialized using a 
 * standard ObjectInputStream. If you need to deserialize data, you may need to use Spring’s 
 * ConfigurableObjectInputStream in combination with Thread.currentThread().getContextClassLoader().
 * Unfortunately, several third-party libraries deserialize without considering the context classloader. 
 * If you find such a problem, you will need to request a fix with the original authors.
 * </p>
 * 
 * @see <a href="https://github.com/spring-projects/spring-boot/issues/3805">Spring Boot Devtools Serializer Issue</a>
 * @author Marc Giffing
 *
 */
public class SpringDevToolsSerializer implements ISerializer {

	private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer;
	
    public SpringDevToolsSerializer(){
    	this.deserializer = new DeserializingConverter(new DefaultDeserializer(Thread.currentThread().getContextClassLoader()));
    }
	
	@Override
	public byte[] serialize(Object object) {
		return serializer.convert(object);
	}

	@Override
	public Object deserialize(byte[] data) {
		return deserializer.convert(data);
	}

}
