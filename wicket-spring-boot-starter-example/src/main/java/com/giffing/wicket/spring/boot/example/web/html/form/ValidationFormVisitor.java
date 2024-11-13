package com.giffing.wicket.spring.boot.example.web.html.form;

import com.giffing.wicket.spring.boot.example.web.html.border.behavior.ValidationMsgBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ValidationFormVisitor<R> implements IVisitor<Component, R>, IClusterable, Serializable {

    private static final long serialVersionUID = 1L;

    private Set<FormComponent<?>> visited = new HashSet<>();

    @Override
    public void component(Component c, IVisit<R> visit) {
        if (c instanceof FormComponent<?> fc && !visited.contains(c)) {
            c.add(new ValidationMsgBehavior());
            visited.add(fc);
        }
    }

}
