package com.giffing.wicket.spring.boot.example.web.html.modal;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class YesNoPanel extends Panel {


    public YesNoPanel(String id) {
        super(id);

        add(new AjaxLink<>("yes") {

            @Override
            public void onClick(AjaxRequestTarget target) {
                YesNoPanel.this.yesClicked(target);
            }

            @Override
            public MarkupContainer setDefaultModel(IModel model) {
                return super.setDefaultModel(model);
            }

        });

        add(new AjaxLink<>("no") {

            @Override
            public void onClick(AjaxRequestTarget target) {
                YesNoPanel.this.noClicked(target);
            }

            @Override
            public MarkupContainer setDefaultModel(IModel model) {
                return super.setDefaultModel(model);
            }


        });

    }

    protected abstract void noClicked(AjaxRequestTarget target);

    protected abstract void yesClicked(AjaxRequestTarget target);


}
