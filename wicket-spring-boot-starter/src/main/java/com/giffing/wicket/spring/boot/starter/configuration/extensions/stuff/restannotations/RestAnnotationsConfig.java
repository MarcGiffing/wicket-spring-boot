package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.restannotations;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.rest.utils.mounting.PackageScanner;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = RestAnnotationsProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = PackageScanner.class)
@EnableConfigurationProperties({RestAnnotationsProperties.class})
@RequiredArgsConstructor
public class RestAnnotationsConfig implements WicketApplicationInitConfiguration {

    private final RestAnnotationsProperties prop;

    private final WicketClassCandidatesHolder candidates;

    @Override
    public void init(WebApplication webApplication) {
        var packagename = webApplication.getClass().getPackage().getName();
        if (prop.getPackagename() != null) {
            packagename = prop.getPackagename();
        }

        PackageScanner.scanPackage(webApplication, packagename);
        for (String basePackage : candidates.getBasePackages()) {
            PackageScanner.scanPackage(webApplication, basePackage);
        }
    }
}
