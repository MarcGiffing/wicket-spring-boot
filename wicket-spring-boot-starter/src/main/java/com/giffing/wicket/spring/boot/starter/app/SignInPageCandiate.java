package com.giffing.wicket.spring.boot.starter.app;

import org.apache.wicket.markup.html.WebPage;

public class SignInPageCandiate {

	private Class<WebPage> candiate;
	
	public SignInPageCandiate(Class<WebPage> candiate) {
		this.setCandiate(candiate);
	}

	public Class<WebPage> getCandiate() {
		return candiate;
	}

	public void setCandiate(Class<WebPage> candiate) {
		this.candiate = candiate;
	}

}
