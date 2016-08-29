package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.wicketstuff.dashboard.Dashboard;
import org.wicketstuff.dashboard.DashboardContextInitializer;
import org.wicketstuff.dashboard.DefaultDashboard;
import org.wicketstuff.dashboard.WidgetDescriptor;
import org.wicketstuff.dashboard.WidgetRegistry;
import org.wicketstuff.dashboard.web.DashboardContext;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

@ApplicationInitExtension
@ConditionalOnProperty(prefix = DashboardProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = DashboardContext.class)
@EnableConfigurationProperties({ DashboardProperties.class })
@Order(ApplicationInitExtension.DEFAULT_PRECEDENCE + 50)
public class DashboardConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private DashboardProperties props;
	
	@Autowired
	private List<WidgetDescriptor> widgetDescriptors = new ArrayList<>();
	
	@Autowired
	private ApplicationContext context;
	
	@Override
	public void init(WebApplication webApplication) {
		DashboardContext dashboardContext = getDashboardContext(webApplication);
		
		WidgetRegistry widgetRegistry = dashboardContext.getWidgetRegistry();
		
		for (WidgetDescriptor widgetDescriptor : widgetDescriptors) {
			widgetRegistry.registerWidget(widgetDescriptor);
		}
		
		Dashboard dashboard = dashboard(dashboardContext); 
		dashboard.setColumnCount(props.getColumnCount());
		ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) context).getBeanFactory();
		beanFactory.registerSingleton("dashboardConfigHolder", new DashboardConfigHolder(dashboard));
		
		
	}
	
	private Dashboard dashboard(DashboardContext dashboardContext) {
		Dashboard dashboard = dashboardContext.getDashboardPersister().load();
    	if (dashboard == null) {
    		dashboard = new DefaultDashboard("default", "Default");
    	}
    	
    	return dashboard;
	}
	
	private DashboardContext getDashboardContext(WebApplication webApplication) {
		return webApplication.getMetaData(DashboardContextInitializer.DASHBOARD_CONTEXT_KEY);
	}
	

}
