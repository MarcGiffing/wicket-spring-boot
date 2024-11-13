package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.annotationscan;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;

/**
 * Auto configuration for the {@link AnnotatedMountScanner}.
 * <p>
 * It uses the user defined {@link WebApplication} as the default package scan
 * root directory.
 * <p>
 * Enables annotate mount scanner if the following two condition matches:
 * <p>
 * 1. The {@link AnnotatedMountScanner} is in the classpath.
 * <p>
 * 2. The property {@link AnnotatedMountScannerProperties#PROPERTY_PREFIX}
 * .enabled is true (default = true)
 *
 * @author Marc Giffing
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = AnnotatedMountScannerProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.wicketstuff.annotation.scan.AnnotatedMountScanner.class)
@EnableConfigurationProperties({AnnotatedMountScannerProperties.class})
@Order(ApplicationInitExtension.DEFAULT_PRECEDENCE + 50)
@RequiredArgsConstructor
public class AnnotatedMountScannerConfig implements WicketApplicationInitConfiguration {

    private final AnnotatedMountScannerProperties prop;

    private final WicketClassCandidatesHolder candidates;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        List<String> packagesToScan = new ArrayList<>();
        var annotatedMountScanner = new AnnotatedMountScanner();

        var packagename = webApplication.getClass().getPackage().getName();
        if (prop.getPackagename() != null) {
            packagename = prop.getPackagename();
        }

        packagesToScan.add(packagename);
        annotatedMountScanner.scanPackage(packagename).mount(webApplication);

        if (!candidates.getBasePackages().isEmpty()) {
            packagesToScan.addAll(candidates.getBasePackages());
            for (String basePackage : candidates.getBasePackages()) {
                annotatedMountScanner.scanPackage(basePackage).mount(webApplication);
            }
        }

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", prop)
                .withDetail("packagesToScan", packagesToScan)
                .build());

    }

}
