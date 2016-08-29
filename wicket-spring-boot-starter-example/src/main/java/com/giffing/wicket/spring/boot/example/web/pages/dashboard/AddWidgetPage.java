package com.giffing.wicket.spring.boot.example.web.pages.dashboard;

import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.dashboard.Dashboard;

import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.dashboard.DashboardConfigHolder;

public class AddWidgetPage extends BasePage {

	@SpringBean
	private DashboardConfigHolder dashboardConfigHolder;
	
	public AddWidgetPage() {
    	add(new AddWidgetPanel("addWidgetPanel", new Model<Dashboard>(dashboardConfigHolder.getDashboard())));
	}
	
}
