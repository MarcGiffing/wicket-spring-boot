package com.giffing.wicket.spring.boot.example.web.wicket.dataprovider;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.giffing.wicket.spring.boot.example.repository.Filter;
import com.giffing.wicket.spring.boot.example.repository.FilterService;
import com.giffing.wicket.spring.boot.example.repository.Sort;

public abstract class DefaultDataProvider<MODEL, SERVICE extends FilterService<MODEL, FILTER_MODEL>, FILTER_MODEL extends Filter, SORT> implements ISortableDataProvider<MODEL, SORT>{
	
	public abstract SERVICE getFilterService();
	
	public abstract FILTER_MODEL getFilter();
	
	private SingleSortState<SORT> singleSortState = new SingleSortState<SORT>();
	
	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends MODEL> iterator(long first, long count) {
		if(singleSortState.getSort() != null){
			Sort property = (Sort) singleSortState.getSort().getProperty();
			boolean ascending = singleSortState.getSort().isAscending();
			getFilter().setSort(property, ascending);
			
		}
		//TODO quick and dirty check again 
		long page = first / count;
		List<MODEL> customers = getFilterService().findAll(page, count, this.getFilter());
		return customers.iterator();
	}

	@Override
	public long size() {
		long count = getFilterService().count(this.getFilter());
		return count;
	}

	@Override
	public IModel<MODEL> model(MODEL object) {
		return new CompoundPropertyModel<>(new LoadableDetachableModel<MODEL>() {

			@Override
			protected MODEL load() {
				return object;
			}
		});
	}
	
	@Override
	public ISortState<SORT> getSortState() {
		return singleSortState;
	}

}
