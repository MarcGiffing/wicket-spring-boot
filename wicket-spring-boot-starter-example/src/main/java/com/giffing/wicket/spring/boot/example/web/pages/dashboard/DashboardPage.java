package com.giffing.wicket.spring.boot.example.web.pages.dashboard;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.dashboard.Dashboard;
import org.wicketstuff.dashboard.web.DashboardPanel;

import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.dashboard.DashboardConfigHolder;

@MountPath("dashboard")
@AuthorizeInstantiation("USER")
public class DashboardPage extends BasePage {

	@SpringBean
	private DashboardConfigHolder dashboardConfigHolder;
	
	public DashboardPage() {
		add(new BookmarkablePageLink<Void>("addWidget", AddWidgetPage.class));
		add(new DashboardPanel("dashboard", new Model<Dashboard>(dashboardConfigHolder.getDashboard())));
	}
	
}
