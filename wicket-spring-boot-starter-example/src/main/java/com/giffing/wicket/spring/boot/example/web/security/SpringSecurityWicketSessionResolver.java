package com.giffing.wicket.spring.boot.example.web.security;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;

import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WicketSessionResolver;

public class SpringSecurityWicketSessionResolver implements WicketSessionResolver {
	
	@Autowired
	private FindByIndexNameSessionRepository<? extends Session> sessions;
	
	@Override
	public List<String> resolve(Object identifier) {
		Map<String, ? extends Session> findByPrincipalName = sessions.findByPrincipalName(identifier.toString());
		return findByPrincipalName.keySet().stream().collect(Collectors.toList());
	}

}
