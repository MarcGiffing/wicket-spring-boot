package com.giffing.wicket.spring.boot.example.web.pages.customers.filter;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.AbstractFilter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

public class AbstractCheckBoxFilter extends AbstractFilter{

	private static final long serialVersionUID = 1L;
	
	private final CheckBox filter;

	public AbstractCheckBoxFilter(final String id, final IModel<Boolean> model, final FilterForm<?> form)
	{
		super(id, form);
		filter =  createTextFieldComponent("filter", model);
		enableFocusTracking(filter);
		add(filter);
	}

	public CheckBox createTextFieldComponent(String componentId, final IModel<Boolean> model) {
		CheckBox checkBox = new CheckBox("filter", model){

			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				return true;
			}
			
		};
		return checkBox;
	}

	/**
	 * @return underlying {@link TextField} form component that represents this filter
	 */
	public final CheckBox getFilter()
	{
		return filter;
	}
	

}
