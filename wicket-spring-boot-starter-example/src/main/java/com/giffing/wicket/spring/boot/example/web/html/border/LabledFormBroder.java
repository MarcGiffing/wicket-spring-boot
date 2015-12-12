package com.giffing.wicket.spring.boot.example.web.html.border;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.model.Model;

public class LabledFormBroder<T> extends Border {

	private static final long serialVersionUID = 1L;

	private FormComponent<T> formComponent;

	public LabledFormBroder(String labelText, FormComponent<T> container) {
		super(container.getId() + "Border");
		this.formComponent = container;
		FormComponentLabel label = new FormComponentLabel("label", container);
		label.add(new AttributeModifier("class", "control-label"));
		Label somethingLabelSpan = new Label("labelText", labelText);
		somethingLabelSpan.setRenderBodyOnly(true);
		label.add(somethingLabelSpan);

		addToBorder(label);
		add(container);
	}

	@Override
	protected void onBeforeRender() {

		if (!formComponent.isValid()) {
			add(new AttributeModifier("class", Model.of("form-group has-error")));
		} else {
			add(new AttributeModifier("class", "form-group"));
		}

		super.onBeforeRender();
	}

}
