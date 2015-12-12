package com.giffing.wicket.spring.boot.example.web.html.border;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;

public class BaseBorder<T> extends Border{

	private static final long serialVersionUID = 1L;

	public BaseBorder(String label, FormComponent<T> container) {
		super(container.getId());
		addToBorder(new Label("label", label));
		add(container);
	}

}
