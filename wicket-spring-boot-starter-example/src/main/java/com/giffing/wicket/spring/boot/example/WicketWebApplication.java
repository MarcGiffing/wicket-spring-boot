package com.giffing.wicket.spring.boot.example;

import org.springframework.stereotype.Component;

import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;

@Component
public class WicketWebApplication extends WicketBootSecuredWebApplication {
	@Override
	protected void init() {
		System.out.println("test");
		super.init();
	}
}
