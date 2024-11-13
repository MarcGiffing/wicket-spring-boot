package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.requrestcycle;

import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.settings.RequestCycleSettings.RenderStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.types.DurationUnit;

@ConfigurationProperties(prefix = RequestCycleSettingsProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class RequestCycleSettingsProperties {

    public static final String PROPERTY_PREFIX = "wicket.core.settings.requestcycle";

    private RenderStrategy renderStrategy = RenderStrategy.REDIRECT_TO_BUFFER;

    private boolean bufferResponse = true;

    private boolean gatherExtendedBrowserInfo = false;

    private String responseRequestEncoding = "UTF-8";

    private Long timeoutSize = 1L;

    private DurationUnit timeoutUnit = DurationUnit.MINUTES;

    private int exceptionRetryCount = 10;

}
