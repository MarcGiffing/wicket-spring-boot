package com.giffing.wicket.spring.boot.example.web.general.action.panel.items.yesno;

import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.AbstractActionItemLink;
import com.giffing.wicket.spring.boot.example.web.html.modal.YesNoModal;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.EmptyPanel;

public abstract class YesNoLink<T> extends AbstractActionItemLink<T> {

    protected YesNoLink(IconType iconType) {
        super(iconType);
    }

    @Override
    public void onClick(AjaxRequestTarget target) {
        YesNoModal yesNoModal = new YesNoModal("defaultModal") {

            @Override
            protected void yesClicked(AjaxRequestTarget target) {
                YesNoLink.this.yesClicked(target);
                close(target);
                ((BasePage) getPage()).replaceDefaultModal(new EmptyPanel("defaultModal"), target);
            }
        };
        ((BasePage) getPage()).replaceDefaultModal(yesNoModal, target);
        yesNoModal.appendShowDialogJavaScript(target);
    }

    protected abstract void yesClicked(AjaxRequestTarget target);

}
