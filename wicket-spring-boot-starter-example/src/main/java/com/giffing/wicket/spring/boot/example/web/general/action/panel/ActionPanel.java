package com.giffing.wicket.spring.boot.example.web.general.action.panel;

import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.AbstrractActionItem;
import de.agilecoders.wicket.core.markup.html.bootstrap.list.BootstrapListView;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.List;

public class ActionPanel extends Panel {

    public ActionPanel(String id, List<AbstrractActionItem> items) {
        super(id);
        add(new BootstrapListView<>("items", items) {

            @Override
            protected void populateItem(ListItem<AbstrractActionItem> item) {
                item.add(item.getModel().getObject());
            }

        });
    }


}
