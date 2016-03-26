package com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.AbstractFilter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter.events.OnCreateTextFieldComponent;

public class AbstractTextFieldFilter<T> extends AbstractFilter{

	private static final long serialVersionUID = 1L;
	
	private final TextField<T> filter;
	
	private OnCreateTextFieldComponent<T> onCreateTextField;

	public AbstractTextFieldFilter(final String id, final IModel<T> model, final FilterForm<?> form)
	{
		super(id, form);
		filter =  createTextFieldComponent("filter", model);
		enableFocusTracking(filter);
		add(filter);
	}

	public TextField<T> createTextFieldComponent(String componentId, final IModel<T> model) {
		if(onCreateTextField != null){
			return onCreateTextField.createTextFieldComponent(componentId, model);
		} else {
			return new TextField<>(componentId, model);
		}
	}
	

	/**
	 * @return underlying {@link TextField} form component that represents this filter
	 */
	public final TextField<T> getFilter()
	{
		return filter;
	}
	
	public OnCreateTextFieldComponent<T> setOnCreateTextField(OnCreateTextFieldComponent<T> component) {
		return this.onCreateTextField = component;
	}
	
	

}
