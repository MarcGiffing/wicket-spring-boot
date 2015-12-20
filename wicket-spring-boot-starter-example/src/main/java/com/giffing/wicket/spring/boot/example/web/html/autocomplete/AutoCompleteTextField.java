package com.giffing.wicket.spring.boot.example.web.html.autocomplete;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;

public abstract class AutoCompleteTextField
		extends org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField<String> {

	public AutoCompleteTextField(String id, AutoCompleteSettings setMinInputLength) {
		super(id, setMinInputLength);
	}

	public AutoCompleteTextField(String componentId, IModel<String> model) {
		super(componentId, model);
	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);

		response.render(CssHeaderItem.forReference(new CssResourceReference(
				AutoCompleteTextField.class, "AutoCompleteTextField.css")));
	}



}
