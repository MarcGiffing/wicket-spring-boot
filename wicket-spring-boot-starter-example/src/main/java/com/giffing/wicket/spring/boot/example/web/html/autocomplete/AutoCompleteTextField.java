package com.giffing.wicket.spring.boot.example.web.html.autocomplete;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;

public abstract class AutoCompleteTextField
        extends org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField<String> {

    protected AutoCompleteTextField(String id, AutoCompleteSettings settings) {
        super(id, settings);
    }

    protected AutoCompleteTextField(String id, IModel<String> model, AutoCompleteSettings settings) {
        super(id, model, settings);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(new CssResourceReference(
                AutoCompleteTextField.class, "AutoCompleteTextField.css")));
    }


}
