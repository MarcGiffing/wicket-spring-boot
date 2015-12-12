package com.giffing.wicket.spring.boot.example.web.wicket.dataprovider;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.giffing.wicket.spring.boot.example.repository.Filter;
import com.giffing.wicket.spring.boot.example.repository.FilterService;

public abstract class DefaultDataProvider<T, S extends FilterService<T, F>, F extends Filter> implements IDataProvider<T>{
	
	
	public abstract S getFilterService();
	
	public abstract F getFilter();
	
	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends T> iterator(long first, long count) {
		//TODO quick and dirty check again 
		long page = first / count;
		List<T> customers = getFilterService().findAll(page, count, this.getFilter());
		System.out.println(customers);
		return customers.iterator();
	}

	@Override
	public long size() {
		long count = getFilterService().count(this.getFilter());
		System.out.println("count: " + count);
		return count;
	}

	@Override
	public IModel<T> model(T object) {
		return new CompoundPropertyModel<>(new LoadableDetachableModel<T>() {

			@Override
			protected T load() {
				return object;
			}
		});
	}

}
