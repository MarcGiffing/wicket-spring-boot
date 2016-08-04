package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.boot.actuator;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class WicketAutoConfig implements Serializable {

	private final String key;

	private final Map<String, Object> details;

	private WicketAutoConfig(Builder builder) {
		Assert.notNull(builder, "Builder must not be null");
		this.key = builder.configurationClass != null ? builder.configurationClass.getSimpleName() : null;
		this.details = Collections.unmodifiableMap(builder.details);
	}

	public static class Builder {

		private final Class<?> configurationClass;

		private Map<String, Object> details;

		public Builder() {
			this.configurationClass = null;
			this.details = new LinkedHashMap<String, Object>();
		}

		public Builder(Class<?> configurationClass) {
			Assert.notNull(configurationClass, "ConfigurationClass must not be null");
			this.configurationClass = configurationClass;
			this.details = new LinkedHashMap<String, Object>();
		}

		public Builder(Class<?> configurationClass, Map<String, ?> details) {
			Assert.notNull(configurationClass, "ConfigurationClass must not be null");
			Assert.notNull(details, "Details must not be null");
			this.configurationClass = configurationClass;
			this.details = new LinkedHashMap<String, Object>(details);
		}

		public Builder withDetail(String key, Object value) {
			Assert.notNull(key, "Key must not be null");
			this.details.put(key, value);
			return this;
		}

		public WicketAutoConfig build() {
			return new WicketAutoConfig(this);
		}

	}
	
	public String getKey() {
		return key;
	}

	public Map<String, Object> getDetails() {
		return details;
	}

}
