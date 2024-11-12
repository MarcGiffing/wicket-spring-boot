package com.giffing.wicket.spring.boot.example.web.html.modal;

import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class YesNoModal extends Modal<Panel> {

    public YesNoModal(String id) {
        super(id);
        var yesNoPanel = new YesNoPanel("content") {

            @Override
            protected void yesClicked(AjaxRequestTarget target) {
                YesNoModal.this.yesClicked(target);
            }

            @Override
            protected void noClicked(AjaxRequestTarget target) {
                YesNoModal.this.noClicked(target);
            }

        };
        add(yesNoPanel);
    }

    protected void noClicked(AjaxRequestTarget target) {
        close(target);
    }

    protected abstract void yesClicked(AjaxRequestTarget target);

}
