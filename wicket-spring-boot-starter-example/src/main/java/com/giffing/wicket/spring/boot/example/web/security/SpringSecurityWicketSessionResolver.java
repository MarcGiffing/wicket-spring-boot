package com.giffing.wicket.spring.boot.example.web.security;

import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WicketSessionResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class SpringSecurityWicketSessionResolver implements WicketSessionResolver {
	
	private final FindByIndexNameSessionRepository<? extends Session> sessions;
	
	@Override
	public List<String> resolve(Object identifier) {
		Map<String, ? extends Session> findByPrincipalName = sessions.findByPrincipalName(identifier.toString());
		return new ArrayList<>(findByPrincipalName.keySet());
	}

}
