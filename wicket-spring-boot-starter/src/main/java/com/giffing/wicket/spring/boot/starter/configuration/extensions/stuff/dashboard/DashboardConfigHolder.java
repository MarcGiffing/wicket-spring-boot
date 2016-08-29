package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.dashboard;

import java.io.Serializable;

import org.wicketstuff.dashboard.Dashboard;

public class DashboardConfigHolder implements Serializable {

	private final Dashboard dashboard;
	
	public DashboardConfigHolder(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

}
