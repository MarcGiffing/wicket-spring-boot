package com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter.events;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;

@FunctionalInterface
public interface OnPopulateItem<T> extends IClusterable {

	void populateItem(Item<ICellPopulator<T>> item, String componentId, IModel<?> rowModel);
	
}
