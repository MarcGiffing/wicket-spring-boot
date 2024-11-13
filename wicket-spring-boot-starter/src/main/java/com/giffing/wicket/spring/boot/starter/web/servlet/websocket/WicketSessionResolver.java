package com.giffing.wicket.spring.boot.starter.web.servlet.websocket;

import java.util.List;

public interface WicketSessionResolver {

	/**
	 * Retrieve the session id from the principle object.
	 * 
	 * @param identifier
	 * @return
	 */
	List<String> resolve(Object identifier);
	
}
