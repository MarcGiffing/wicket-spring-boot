package com.giffing.wicket.spring.boot.starter.app;

import org.apache.wicket.Page;

public class HomePageCandiate {

	private Class<Page> candiate;
	
	public HomePageCandiate(Class<Page> candiate) {
		this.setCandiate(candiate);
	}

	public Class<Page> getCandiate() {
		return candiate;
	}

	public void setCandiate(Class<Page> candiate) {
		this.candiate = candiate;
	}

}
