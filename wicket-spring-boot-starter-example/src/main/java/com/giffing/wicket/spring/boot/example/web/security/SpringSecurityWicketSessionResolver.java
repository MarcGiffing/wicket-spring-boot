package com.giffing.wicket.spring.boot.example.web.security;

import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WicketSessionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpringSecurityWicketSessionResolver implements WicketSessionResolver {
	
	@Autowired
	private FindByIndexNameSessionRepository<? extends Session> sessions;
	
	@Override
	public List<String> resolve(Object identifier) {
		Map<String, ? extends Session> findByPrincipalName = sessions.findByPrincipalName(identifier.toString());
		return new ArrayList<>(findByPrincipalName.keySet());
	}

}
