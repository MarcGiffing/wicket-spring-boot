package com.giffing.wicket.spring.boot.example.web.html.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.visit.IVisitor;

public class ValidationForm<T> extends BootstrapForm<T> {

    private static final long serialVersionUID = 1L;

    private IVisitor visitor = new ValidationFormVisitor<>();

    public ValidationForm(String id, IModel<T> model) {
        super(id, model);
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        visitChildren(visitor);
    }

}
