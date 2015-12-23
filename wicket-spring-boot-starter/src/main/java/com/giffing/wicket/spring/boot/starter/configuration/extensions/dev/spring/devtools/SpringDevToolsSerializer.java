package com.giffing.wicket.spring.boot.starter.configuration.extensions.dev.spring.devtools;

import org.apache.wicket.serialize.ISerializer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

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
