package com.giffing.wicket.spring.boot.example.web.html.modal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class YesNoPanel extends Panel {


    protected YesNoPanel(String id) {
        super(id);

        add(new AjaxLink<>("yes") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                YesNoPanel.this.yesClicked(target);
            }
        });

        add(new AjaxLink<>("no") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                YesNoPanel.this.noClicked(target);
            }
        });

    }

    protected abstract void noClicked(AjaxRequestTarget target);

    protected abstract void yesClicked(AjaxRequestTarget target);

}
