package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.monitoring.jamon;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.jamon.request.cycle.JamonAwareRequestCycleListener;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = JamonProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = JamonAwareRequestCycleListener.class)
@EnableConfigurationProperties({JamonProperties.class})
@RequiredArgsConstructor
public class JamonConfig implements WicketApplicationInitConfiguration {

    private final JamonProperties prop;

    @Override
    public void init(WebApplication webApplication) {
        webApplication.getRequestCycleListeners().add(new JamonAwareRequestCycleListener(webApplication, prop.isIncludeSourceNameInMonitorLabel()));
        webApplication.mountPage(prop.getMountPage(), BootJamonAdminPage.class);
    }

}
