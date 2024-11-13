package com.giffing.wicket.spring.boot.context.extensions.boot.actuator;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
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
			this.details = new LinkedHashMap<>();
		}

		public Builder(Class<?> configurationClass) {
			Assert.notNull(configurationClass, "ConfigurationClass must not be null");
			this.configurationClass = configurationClass;
			this.details = new LinkedHashMap<>();
		}

		public Builder(Class<?> configurationClass, Map<String, ?> details) {
			Assert.notNull(configurationClass, "ConfigurationClass must not be null");
			Assert.notNull(details, "Details must not be null");
			this.configurationClass = configurationClass;
			this.details = new LinkedHashMap<>(details);
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
	
}
