package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.htmlcompressor;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = HTMLCompressingProperties.PROPERTY_PREFIX)
@Getter
public class HTMLCompressingProperties {

    public static final String PROPERTY_PREFIX = "wicket.stuff.htmlcompressor";

    /**
     * Indicates if the HTML compression should be enabled. It is only enable if a
     * HTML compression library is present.
     *
     * @see HTMLCompressingConfig
     */
    private boolean enabled = true;

    /**
     * Sets additional features of the {@link HtmlCompressor} class. It uses
     * reflection to set boolean properties on public methods.
     * <p>
     * You can for example use compressCSS->true to invoke the public method setCompressCSS(true).
     * <p>
     * The main goal is to provide an API independent solution to configure the {@link HtmlCompressor}.
     * API changes can be handled over configuration changes
     */
    private final Map<String, Boolean> features = new HashMap<>();

    public HTMLCompressingProperties() {
        features.put("removeComments", Boolean.TRUE);
        features.put("removeMultiSpaces", Boolean.TRUE);
        features.put("removeIntertagSpaces", Boolean.TRUE);
        features.put("removeQuotes", Boolean.TRUE);
        features.put("compressJavaScript", Boolean.TRUE);
        features.put("compressCss", Boolean.TRUE);
        features.put("simpleDoctype", Boolean.TRUE);
        features.put("removeScriptAttributes", Boolean.FALSE);
        features.put("removeStyleAttributes", Boolean.FALSE);
        features.put("removeLinkAttributes", Boolean.FALSE);
        features.put("removeFormAttributes", Boolean.FALSE);
        features.put("removeInputAttributes", Boolean.FALSE);
        features.put("simpleBooleanAttributes", Boolean.FALSE);
        features.put("removeJavaScriptProtocol", Boolean.FALSE);
        features.put("removeHttpProtocol", Boolean.FALSE);
        features.put("removeHttpsProtocol", Boolean.FALSE);
        features.put("preserveLineBreaks", Boolean.FALSE);
    }


}
