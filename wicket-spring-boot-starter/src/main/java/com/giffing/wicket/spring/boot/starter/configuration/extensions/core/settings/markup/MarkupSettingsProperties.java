package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.markup;

import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.markup.MarkupFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = MarkupSettingsProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class MarkupSettingsProperties {

    public static final String PROPERTY_PREFIX = "wicket.core.settings.markup";

    /**
     * Application default for automatically resolving hrefs
     */
    private boolean automaticLinking = false;

    /**
     * True if multiple tabs/spaces should be compressed to a single space
     */
    private boolean compressWhitespace = false;

    /**
     * Default markup encoding. If null, the OS default will be used
     */
    private String defaultMarkupEncoding;

    /**
     * Factory for creating markup parsers
     */
    private MarkupFactory markupFactory;

    /**
     * if true than throw an exception if the xml declaration is missing from the markup file
     */
    private boolean throwExceptionOnMissingXmlDeclaration = false;

    /**
     * Should HTML comments be stripped during rendering?
     */
    private boolean stripComments = false;

    /**
     * If true, wicket tags ( <wicket: ..>) and wicket:id attributes we be removed from output
     */
    private boolean stripWicketTags = true;
}
