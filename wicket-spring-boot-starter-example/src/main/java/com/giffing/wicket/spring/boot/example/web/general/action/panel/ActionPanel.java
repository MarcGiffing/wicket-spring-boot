package com.giffing.wicket.spring.boot.example.web.general.action.panel;

import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.AbstrractActionItem;

public class ActionPanel extends Panel {
	
	
	public ActionPanel(String id, List<AbstrractActionItem> items) {
		super(id);
		ListView<AbstrractActionItem> listItems = new ListView<AbstrractActionItem>("items", items) {

			@Override
			protected void populateItem(ListItem<AbstrractActionItem> item) {
				item.add(item.getModel().getObject());
			}


		};
		add(listItems);
	}
	

}
