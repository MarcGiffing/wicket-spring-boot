package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.shiro;

import org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;
import org.wicketstuff.shiro.authz.ShiroUnauthorizedComponentListener;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.scan.WicketAccessDeniedPage;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;

//TODO check if property shiro.enabled is true
@ApplicationInitExtension
@ConditionalOnProperty(prefix = ShiroSecurityProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = {
		AnnotationsShiroAuthorizationStrategy.class,
		ShiroAutoConfiguration.class,
})
@EnableConfigurationProperties({ ShiroSecurityProperties.class })

public class ShiroSecurityConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private WicketClassCandidatesHolder classCandidates;

	@Bean
	ShiroFilterChainDefinition shiroFilterChainDefinition() {
		return new DefaultShiroFilterChainDefinition();
	}

	@Override
	public void init(WebApplication webApplication) {
		AnnotationsShiroAuthorizationStrategy authz = new AnnotationsShiroAuthorizationStrategy();
		webApplication.getSecuritySettings().setAuthorizationStrategy( authz );

		if ( classCandidates.getSignInPageCandidates().size() <= 0 ) {
			throw new IllegalStateException( "Couln't find sign in page - please annotated the sign in page with @" + WicketSignInPage.class.getName() );
		}
		if ( classCandidates.getAccessDeniedPageCandidates().size() <= 0 ) {
			throw new IllegalStateException( "Couln't find access denied in page - please annotated the sign in page with @" + WicketAccessDeniedPage.class.getName() );
		}
		Class<WebPage> signInPage = classCandidates.getSignInPageCandidates().iterator().next().getCandidate();
		Class<Page> accessDeniedPage = classCandidates.getAccessDeniedPageCandidates().iterator().next().getCandidate();
		webApplication.getSecuritySettings()
				.setUnauthorizedComponentInstantiationListener( new ShiroUnauthorizedComponentListener( signInPage, accessDeniedPage, authz ) );

	}

}