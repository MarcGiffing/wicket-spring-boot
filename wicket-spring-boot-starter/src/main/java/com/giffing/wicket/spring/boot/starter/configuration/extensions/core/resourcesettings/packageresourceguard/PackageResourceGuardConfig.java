package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.resourcesettings.packageresourceguard;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ApplicationInitExtension
@EnableConfigurationProperties({PackageResourceGuardProperties.class})
@RequiredArgsConstructor
public class PackageResourceGuardConfig implements WicketApplicationInitConfiguration {

    private final PackageResourceGuardProperties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        var packageResourceGuard = webApplication.getResourceSettings().getPackageResourceGuard();
        if (packageResourceGuard instanceof SecurePackageResourceGuard guard) {
            for (String pattern : props.getPattern()) {
                guard.addPattern(pattern);
            }
        }

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());
    }

}