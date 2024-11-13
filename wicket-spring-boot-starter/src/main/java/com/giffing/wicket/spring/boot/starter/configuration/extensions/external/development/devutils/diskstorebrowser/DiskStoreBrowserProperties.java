package com.giffing.wicket.spring.boot.starter.configuration.extensions.external.development.devutils.diskstorebrowser;

import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.devutils.pagestore.PageStorePage;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = DiskStoreBrowserProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class DiskStoreBrowserProperties {

    public static final String PROPERTY_PREFIX = "wicket.external.development.devutils.diskstorebrowser";

    /**
     * If enabled the {@link PageStorePage} should be mounted test page.
     * <p>
     * It is required that the deployment configuration is set to DEVELOPMENT.
     */
    private boolean enabled;

    /**
     * The default mount page for the disk store browser.
     */
    private String mountPage = "devutils/diskstore/browser";

}
