package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.csrf;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.FetchMetadataResourceIsolationPolicy;
import org.apache.wicket.protocol.http.OriginResourceIsolationPolicy;
import org.apache.wicket.protocol.http.ResourceIsolationRequestCycleListener;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * Enables CSRF protection if the following condition matches.
 * <p>
 * 1. The {@link ResourceIsolationRequestCycleListener} class is in the classpath.
 * <p>
 * 2. The property {@link CsrfAttacksPreventionProperties#PROPERTY_PREFIX}.enabled has to be true (default = true)
 * <p>
 * The protection should be enabled by default cause the {@link ResourceIsolationRequestCycleListener} is located
 * in Wickets core project.
 *
 * @author Marc Giffing
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = CsrfAttacksPreventionProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.apache.wicket.protocol.http.ResourceIsolationRequestCycleListener.class)
@EnableConfigurationProperties({CsrfAttacksPreventionProperties.class})
@RequiredArgsConstructor
public class CsrfAttacksPreventionConfig implements WicketApplicationInitConfiguration {

    private final CsrfAttacksPreventionProperties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        var originResourceIsolationPolicy = new OriginResourceIsolationPolicy();
        props.getAcceptedOrigins().forEach(originResourceIsolationPolicy::addAcceptedOrigin);

        var listener = new ResourceIsolationRequestCycleListener(
                new FetchMetadataResourceIsolationPolicy(),
                originResourceIsolationPolicy);
        listener.setUnknownOutcomeAction(props.getUnknownOutcomeAction());
        listener.setDisallowedOutcomeAction(props.getDisallowedOutcomeAction());
        listener.setErrorCode(props.getErrorCode());
        listener.setErrorMessage(props.getErrorMessage());
        webApplication.getRequestCycleListeners().add(listener);

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());
    }

}
