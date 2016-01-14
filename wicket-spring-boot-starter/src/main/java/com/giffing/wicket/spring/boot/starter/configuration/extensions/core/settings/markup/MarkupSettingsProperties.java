package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.markup;

import org.apache.wicket.markup.MarkupFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = MarkupSettingsProperties.PROPERTY_PREFIX)
public class MarkupSettingsProperties {

	static final String PROPERTY_PREFIX = "wicket.core.settings.markup";
	
	/** Application default for automatically resolving hrefs */
	private boolean automaticLinking = false;

	/** True if multiple tabs/spaces should be compressed to a single space */
	private boolean compressWhitespace = false;

	/** Default markup encoding. If null, the OS default will be used */
	private String defaultMarkupEncoding;

	/** Factory for creating markup parsers */
	private MarkupFactory markupFactory;

	/** if true than throw an exception if the xml declaration is missing from the markup file */
	private boolean throwExceptionOnMissingXmlDeclaration = false;

	/** Should HTML comments be stripped during rendering? */
	private boolean stripComments = false;

	/**
	 * If true, wicket tags ( <wicket: ..>) and wicket:id attributes we be removed from output
	 */
	private boolean stripWicketTags = false;
	
	public String getDefaultMarkupEncoding() {
		return defaultMarkupEncoding;
	}

	public void setDefaultMarkupEncoding(String defaultMarkupEncoding) {
		this.defaultMarkupEncoding = defaultMarkupEncoding;
	}

	public boolean isAutomaticLinking() {
		return automaticLinking;
	}

	public void setAutomaticLinking(boolean automaticLinking) {
		this.automaticLinking = automaticLinking;
	}

	public boolean isCompressWhitespace() {
		return compressWhitespace;
	}

	public void setCompressWhitespace(boolean compressWhitespace) {
		this.compressWhitespace = compressWhitespace;
	}

	public MarkupFactory getMarkupFactory() {
		return markupFactory;
	}

	public void setMarkupFactory(MarkupFactory markupFactory) {
		this.markupFactory = markupFactory;
	}

	public boolean isThrowExceptionOnMissingXmlDeclaration() {
		return throwExceptionOnMissingXmlDeclaration;
	}

	public void setThrowExceptionOnMissingXmlDeclaration(boolean throwExceptionOnMissingXmlDeclaration) {
		this.throwExceptionOnMissingXmlDeclaration = throwExceptionOnMissingXmlDeclaration;
	}

	public boolean isStripComments() {
		return stripComments;
	}

	public void setStripComments(boolean stripComments) {
		this.stripComments = stripComments;
	}

	public boolean isStripWicketTags() {
		return stripWicketTags;
	}

	public void setStripWicketTags(boolean stripWicketTags) {
		this.stripWicketTags = stripWicketTags;
	}

}
