package com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.requrestcycle;

import org.apache.wicket.settings.RequestCycleSettings.RenderStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.types.DurationUnit;

@ConfigurationProperties(prefix = RequestCycleSettingsProperties.PROPERTY_PREFIX)
public class RequestCycleSettingsProperties {

	public static final String PROPERTY_PREFIX = "wicket.core.settings.requestcycle";
	
	private RenderStrategy renderStrategy = RenderStrategy.REDIRECT_TO_BUFFER;
	
	private boolean bufferResponse = true;

	private boolean gatherExtendedBrowserInfo = false;

	private String responseRequestEncoding = "UTF-8";

	private Long timeoutSize = 1L;

	private DurationUnit timeoutUnit = DurationUnit.MINUTES;

	private int exceptionRetryCount = 10;
	
	public RenderStrategy getRenderStrategy() {
		return renderStrategy;
	}

	public void setRenderStrategy(RenderStrategy renderStrategy) {
		this.renderStrategy = renderStrategy;
	}

	public boolean isBufferResponse() {
		return bufferResponse;
	}

	public void setBufferResponse(boolean bufferResponse) {
		this.bufferResponse = bufferResponse;
	}

	public boolean isGatherExtendedBrowserInfo() {
		return gatherExtendedBrowserInfo;
	}

	public void setGatherExtendedBrowserInfo(boolean gatherExtendedBrowserInfo) {
		this.gatherExtendedBrowserInfo = gatherExtendedBrowserInfo;
	}

	public String getResponseRequestEncoding() {
		return responseRequestEncoding;
	}

	public void setResponseRequestEncoding(String responseRequestEncoding) {
		this.responseRequestEncoding = responseRequestEncoding;
	}

	public int getExceptionRetryCount() {
		return exceptionRetryCount;
	}

	public void setExceptionRetryCount(int exceptionRetryCount) {
		this.exceptionRetryCount = exceptionRetryCount;
	}

	public Long getTimeoutSize() {
		return timeoutSize;
	}

	public void setTimeoutSize(Long timeoutSize) {
		this.timeoutSize = timeoutSize;
	}

	public DurationUnit getTimeoutUnit() {
		return timeoutUnit;
	}

	public void setTimeoutUnit(DurationUnit timeoutUnit) {
		this.timeoutUnit = timeoutUnit;
	}
	
}
