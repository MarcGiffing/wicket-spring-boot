package com.giffing.wicket.spring.boot.example.web.pages;

import com.giffing.wicket.spring.boot.example.web.assets.base.FixBootstrapStylesCssResourceReference;
import com.giffing.wicket.spring.boot.example.web.general.notify.NotyJSReference;
import com.giffing.wicket.spring.boot.example.web.general.notify.NotyPackagedJSReference;
import com.giffing.wicket.spring.boot.example.web.general.notify.NotyThemeBootstrapJSReference;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.IeEdgeMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MobileViewportMetaTag;
import de.agilecoders.wicket.core.markup.html.references.BootlintHeaderItem;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6CssReference;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BasePage extends GenericWebPage<Void> {

    private MarkupContainer defaultModal;

    public BasePage(final PageParameters parameters) {
        super(parameters);

        defaultModal = new EmptyPanel("defaultModal");
        defaultModal.setMarkupId("defaultModal");
        defaultModal.setOutputMarkupId(true);
        queue(defaultModal);

        //add(new HtmlTag("html", WebSession.get().getLocale()));
        MobileViewportMetaTag mvt = new MobileViewportMetaTag("viewport");
        mvt.setWidth("device-width");
        mvt.setInitialScale("1");
        add(mvt);
        add(new IeEdgeMetaTag("ie-edge"));
        add(new MetaTag("description", Model.of("description"), Model.of("Wicket")));

        add(new Code("code-internal"));
        add(new HeaderResponseContainer("footer-container", "footer-container"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(FixBootstrapStylesCssResourceReference.INSTANCE));
        response.render(CssHeaderItem.forReference(FontAwesome6CssReference.instance()));
        response.render(JavaScriptHeaderItem.forReference(NotyJSReference.INSTANCE));
        response.render(JavaScriptHeaderItem.forReference(NotyPackagedJSReference.INSTANCE));
        response.render(JavaScriptHeaderItem.forReference(NotyThemeBootstrapJSReference.INSTANCE));

        if (!getRequest().getRequestParameters().getParameterValue("bootlint").isNull()) {
            response.render(BootlintHeaderItem.INSTANCE);
        }
    }

    public MarkupContainer getDefaultModal() {
        return defaultModal;
    }

    public void replaceDefaultModal(MarkupContainer newModal, AjaxRequestTarget target) {
        newModal.setMarkupId(defaultModal.getMarkupId());
        newModal.setOutputMarkupId(true);
        defaultModal.replaceWith(newModal);
        target.add(newModal);
        defaultModal = newModal;
    }

    protected FormGroup queueFormComponent(FormComponent formComponent) {
        var formGroup = new FormGroup(formComponent.getId() + "Border");
        formGroup.useFormComponentLabel(true);
        formGroup.add(formComponent);
        queue(formGroup);
        return formGroup;
    }

}
