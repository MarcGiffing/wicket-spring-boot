package com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilteredPropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter.events.OnGetFilter;
import com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter.events.OnPopulateItem;

public class XFilteredPropertyColumn<T, S> extends FilteredPropertyColumn<T, S> {
	
	private OnGetFilter filter;
	
	private OnPopulateItem<T> onPopulateItem;
	
	public XFilteredPropertyColumn(IModel<String> displayModel, S sortProperty, String propertyExpression) {
		super(displayModel, sortProperty, propertyExpression);
	}

	@Override
	public Component getFilter(String componentId, FilterForm<?> form) {
		return filter.apply(componentId, form);
	}
	
	public void setFilter(OnGetFilter filter){
		this.filter = filter;
	}

	@Override
	public void populateItem(Item<ICellPopulator<T>> item, String componentId, IModel<T> rowModel) {
		if(onPopulateItem != null){
			onPopulateItem.populateItem(item, componentId, getDataModel(rowModel));
		} else {
			super.populateItem(item, componentId, rowModel);
		}
	}
	
	public void setOnPopulateItem(OnPopulateItem<T> populateItemComponent){
		this.onPopulateItem = populateItemComponent;
	}

}
