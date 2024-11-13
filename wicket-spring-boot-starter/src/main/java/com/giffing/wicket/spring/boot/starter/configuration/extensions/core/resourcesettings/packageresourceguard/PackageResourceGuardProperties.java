package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.resourcesettings.packageresourceguard;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(PackageResourceGuardProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class PackageResourceGuardProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.resourcesettings.packageresourceguard";
	
	private List<String> pattern = new ArrayList<>();

}
