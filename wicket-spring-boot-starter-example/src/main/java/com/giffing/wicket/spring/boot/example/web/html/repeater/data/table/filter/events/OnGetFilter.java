package com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter.events;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.util.io.IClusterable;

@FunctionalInterface
public interface OnGetFilter extends IClusterable {

	Component apply(String componentId, FilterForm<?> form);
	
}
