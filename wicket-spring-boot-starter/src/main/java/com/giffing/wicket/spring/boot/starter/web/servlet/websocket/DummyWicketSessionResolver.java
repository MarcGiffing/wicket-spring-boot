package com.giffing.wicket.spring.boot.starter.web.servlet.websocket;

import java.util.Arrays;
import java.util.List;

public class DummyWicketSessionResolver implements WicketSessionResolver {

	@Override
	public List<String> resolve(Object value) {
		return Arrays.asList();
	}

}
