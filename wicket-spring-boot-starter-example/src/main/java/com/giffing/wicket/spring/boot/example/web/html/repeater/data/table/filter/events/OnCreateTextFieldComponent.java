package com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter.events;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;

@FunctionalInterface
public interface OnCreateTextFieldComponent<T> extends IClusterable {

	TextField<T> createTextFieldComponent(String componentId, IModel<T> model);
	
}
