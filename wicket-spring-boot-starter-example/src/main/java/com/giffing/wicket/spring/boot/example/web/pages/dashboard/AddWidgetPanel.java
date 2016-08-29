package com.giffing.wicket.spring.boot.example.web.pages.dashboard;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.wicketstuff.dashboard.Dashboard;
import org.wicketstuff.dashboard.DashboardUtils;
import org.wicketstuff.dashboard.Widget;
import org.wicketstuff.dashboard.WidgetDescriptor;
import org.wicketstuff.dashboard.WidgetFactory;
import org.wicketstuff.dashboard.web.DashboardContext;
import org.wicketstuff.dashboard.web.DashboardContextAware;
import org.wicketstuff.dashboard.web.DashboardEvent;

public class AddWidgetPanel extends GenericPanel<Dashboard> implements DashboardContextAware {

	private static final long serialVersionUID = 1L;
	
	private transient DashboardContext dashboardContext;
	
	public AddWidgetPanel(String id, IModel<Dashboard> model) {
		super(id, model);		
		
    	add(new BookmarkablePageLink<Void>("backDashboard", DashboardPage.class));
    	
		List<WidgetDescriptor> widgetDescriptors = dashboardContext.getWidgetRegistry().getWidgetDescriptors();
		ListView<WidgetDescriptor> listView = new ListView<WidgetDescriptor>("widgetList", widgetDescriptors) {
			 
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<WidgetDescriptor> item) {
				item.add(new WidgetDescriptorPanel("widget", item.getModel()));
			}

		};
		listView.setRenderBodyOnly(true);
		add(listView);
	}
	
	public Dashboard getDashboard() {
		return getModelObject();
		
	}
	@Override
	public void setDashboardContext(DashboardContext dashboardContext) {
		this.dashboardContext = dashboardContext;
	}
	
	private String getUniqueWidgetTitle(String title, int count) {
		String uniqueTitle = title;
		if (count > 0) {
			uniqueTitle = title + " " + count;
		}
		
		List<Widget> widgets = getDashboard().getWidgets();
		for (Widget widget : widgets) {
			if (widget.getTitle().equals(uniqueTitle)) {
				uniqueTitle = getUniqueWidgetTitle(title, count + 1);
			}
		}
		 
		return uniqueTitle;
	}

	private class WidgetDescriptorPanel extends GenericPanel<WidgetDescriptor> {

		private static final long serialVersionUID = 1L;
		
		private String message = "";
		private int count;
		
		public WidgetDescriptorPanel(String id, final IModel<WidgetDescriptor> model) {
			super(id, model);
			
			add(new Label("name", model.getObject().getName()));
			add(new Label("provider", model.getObject().getProvider()));
			add(new Label("description", model.getObject().getDescription()));
			final Label label = new Label("message", new LoadableDetachableModel<String>() {

				private static final long serialVersionUID = 1L;

				@Override
				protected String load() {
					if (count == 1) {
						return message;
					}
					
					return message + " (" + count + ")";
				}
				
			});
			label.setOutputMarkupId(true);
			label.setOutputMarkupPlaceholderTag(true);
			label.setVisible(false);
			add(label);
			AjaxLink<WidgetDescriptor> addLink = new AjaxLink<WidgetDescriptor>("addWidget") {

				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					WidgetFactory widgetFactory = dashboardContext.getWidgetFactory();
					Widget widget = widgetFactory.createWidget(model.getObject());
					widget.setTitle(getUniqueWidgetTitle(widget.getTitle(), count));
					// DashboardPanel is on other page
//					send(getPage(), Broadcast.BREADTH, new DashboardEvent(target, DashboardEvent.EventType.WIDGET_ADDED, widget));
					Dashboard dashboard = getDashboard();
					DashboardUtils.updateWidgetLocations(dashboard, new DashboardEvent(target, DashboardEvent.EventType.WIDGET_ADDED, widget));
					dashboard.addWidget(widget);
					dashboardContext.getDashboardPersister().save(dashboard);
					message = "added";
					label.setVisible(true);
					target.add(label);
					count++;
				}
				
			};
			add(addLink);
		}
		
	}
	
}