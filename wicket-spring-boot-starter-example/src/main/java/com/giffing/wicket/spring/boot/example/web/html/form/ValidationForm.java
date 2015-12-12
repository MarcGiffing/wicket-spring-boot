package com.giffing.wicket.spring.boot.example.web.html.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.visit.IVisitor;

public class ValidationForm<T> extends Form<T>{

	private static final long serialVersionUID = 1L;

	IVisitor visitor = new ValidationFormVisitor<>();
	
	
	
	public ValidationForm(String id) {
		super(id);
	}
	
	public ValidationForm(String id, IModel<T> model) {
		super(id, model);
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		visitChildren(visitor);
	}

}
