package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.datastore;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.pageStore.IPageStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.wicketstuff.shiro.wicket.page.store.SessionPageStore;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = DataStoreHttpSessionProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = false)
@EnableConfigurationProperties({DataStoreHttpSessionProperties.class})
@RequiredArgsConstructor
public class DataStoreHttpSessionConfig implements WicketApplicationInitConfiguration {

    private final DataStoreHttpSessionProperties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {

        webApplication.setPageManagerProvider(new DefaultPageManagerProvider(webApplication) {
            @Override
            protected IPageStore newPersistentStore() {
                return new SessionPageStore(webApplication.getName(), props.getPagesNumber());
            }
        });

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());

    }

}
