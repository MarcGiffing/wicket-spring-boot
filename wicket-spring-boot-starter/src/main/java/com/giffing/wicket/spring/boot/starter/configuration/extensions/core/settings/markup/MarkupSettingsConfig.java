package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.markup;

import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.MarkupSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;

/**
 * Configuration for the markup settings.
 *
 * @author Marc Giffing
 */
@ApplicationInitExtension
@EnableConfigurationProperties({MarkupSettingsProperties.class})
@RequiredArgsConstructor
public class MarkupSettingsConfig implements WicketApplicationInitConfiguration {

    private final MarkupSettingsProperties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        var markupSettings = webApplication.getMarkupSettings();

        if (props.getDefaultMarkupEncoding() != null) {
            markupSettings.setDefaultMarkupEncoding(props.getDefaultMarkupEncoding());
        }

        markupSettings.setAutomaticLinking(props.isAutomaticLinking());
        markupSettings.setCompressWhitespace(props.isCompressWhitespace());
        markupSettings.setStripComments(props.isStripComments());
        markupSettings.setStripWicketTags(props.isStripWicketTags());
        markupSettings.setThrowExceptionOnMissingXmlDeclaration(props.isThrowExceptionOnMissingXmlDeclaration());

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());

    }

}
