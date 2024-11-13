package com.giffing.wicket.spring.boot.example.web.general.action.panel.items.links;

import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.AbstrractActionItem;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import org.apache.wicket.markup.html.link.AbstractLink;

public class ActionItemLink extends AbstrractActionItem {

    public ActionItemLink(IconType iconType, AbstractLink link) {
        add(link);
        link.add(new Icon("icon", iconType));
    }

}
