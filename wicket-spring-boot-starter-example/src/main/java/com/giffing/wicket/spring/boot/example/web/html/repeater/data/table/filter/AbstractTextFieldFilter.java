package com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.AbstractFilter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

public class AbstractTextFieldFilter<T> extends AbstractFilter {

    private final TextField<T> filter;

    public AbstractTextFieldFilter(final String id, final IModel<T> model, final FilterForm<?> form) {
        super(id, form);
        filter = createTextFieldComponent("filter", model);
        enableFocusTracking(filter);
        add(filter);
    }

    public TextField<T> createTextFieldComponent(String componentId, final IModel<T> model) {
        return new TextField<>(componentId, model);
    }

    /**
     * @return underlying {@link TextField} form component that represents this filter
     */
    public final TextField<T> getFilter() {
        return filter;
    }


}
