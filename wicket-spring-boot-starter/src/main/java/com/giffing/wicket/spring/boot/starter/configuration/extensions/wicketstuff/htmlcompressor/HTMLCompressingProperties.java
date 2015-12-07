package com.giffing.wicket.spring.boot.starter.configuration.extensions.wicketstuff.htmlcompressor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

@ConfigurationProperties(prefix = HTMLCompressingProperties.PROPERTY_PREFIX)
public class HTMLCompressingProperties {

	public static final String PROPERTY_PREFIX = "wicket.wicketstuff.htmlcompressor";
	
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
	 * 
	 * You can for example use compressCSS->true to invoke the public method setCompressCSS(true).
	 * 
	 * The main goal is to provide an API independent solution to configure the {@link HtmlCompressor}.
	 * API changes can be handled over configuration changes 
	 */
	private Map<String, Boolean> features = new HashMap<String, Boolean>(){{
        put("removeComments", Boolean.TRUE);
		put("removeMultiSpaces", Boolean.TRUE);
        put("removeIntertagSpaces", Boolean.TRUE);
        put("removeQuotes", Boolean.TRUE);
        put("compressJavaScript", Boolean.TRUE);
        put("compressCss", Boolean.TRUE);
        put("simpleDoctype", Boolean.TRUE);
        put("removeScriptAttributes", Boolean.FALSE);
        put("removeStyleAttributes", Boolean.FALSE);
        put("removeLinkAttributes", Boolean.FALSE);
        put("removeFormAttributes", Boolean.FALSE);
        put("removeInputAttributes", Boolean.FALSE);
        put("simpleBooleanAttributes", Boolean.FALSE);
        put("removeJavaScriptProtocol", Boolean.FALSE);
        put("removeHttpProtocol", Boolean.FALSE);
        put("removeHttpsProtocol", Boolean.FALSE);
        put("preserveLineBreaks", Boolean.FALSE);
    }};

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Map<String, Boolean> getFeatures() {
		return features;
	}

	public void setFeatures(Map<String, Boolean> features) {
		this.features = features;
	}



}
