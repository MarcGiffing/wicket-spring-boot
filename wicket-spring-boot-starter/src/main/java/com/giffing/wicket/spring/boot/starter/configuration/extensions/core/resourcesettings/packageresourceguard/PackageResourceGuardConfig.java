package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.resourcesettings.packageresourceguard;

import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;

@ApplicationInitExtension
@EnableConfigurationProperties({ PackageResourceGuardProperties.class })
public class PackageResourceGuardConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private PackageResourceGuardProperties props;
	
	@Autowired
	private WicketEndpointRepository wicketEndpointRepository;
	
	@Override
	public void init(WebApplication webApplication) {
		IPackageResourceGuard packageResourceGuard = webApplication.getResourceSettings().getPackageResourceGuard();
		if (packageResourceGuard instanceof SecurePackageResourceGuard) {
			SecurePackageResourceGuard guard = (SecurePackageResourceGuard) packageResourceGuard;
			for(String pattern : props.getPattern()){
				guard.addPattern(pattern);
			}
		}
		
		wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
				.withDetail("properties", props)
				.build());
		
	}

}
