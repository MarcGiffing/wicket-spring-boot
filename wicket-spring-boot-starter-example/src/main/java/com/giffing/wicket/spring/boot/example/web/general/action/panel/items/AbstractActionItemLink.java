package com.giffing.wicket.spring.boot.example.web.general.action.panel.items;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

public abstract class AbstractActionItemLink<T> extends AbstrractActionItem {

    protected AbstractActionItemLink(IconType iconType) {
        AjaxLink<T> link = new AjaxLink<T>("link") {

            @Override
            public void onClick(AjaxRequestTarget target) {
                AbstractActionItemLink.this.onClick(target);

            }
        };
        add(link);
        link.add(new Icon("icon", iconType));
    }

    public abstract void onClick(AjaxRequestTarget target);
}
