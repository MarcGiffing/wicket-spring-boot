package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.annotationscan;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = AnnotatedMountScannerProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class AnnotatedMountScannerProperties {

    public static final String PROPERTY_PREFIX = "wicket.stuff.annotationscan";

    /**
     * @see AnnotatedMountScannerConfig
     */
    private boolean enabled = true;

    /**
     * An alternative package name for scanning for mount path if the
     * WicketApplication should not used as the root scan package
     */
    private String packagename;

}
