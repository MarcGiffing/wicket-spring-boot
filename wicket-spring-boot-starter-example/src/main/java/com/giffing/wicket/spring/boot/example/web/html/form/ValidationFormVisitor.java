package com.giffing.wicket.spring.boot.example.web.html.form;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import com.giffing.wicket.spring.boot.example.web.html.border.behavior.ValidationMsgBehavior;

public class ValidationFormVisitor<T, R> implements IVisitor<Component, R>, IClusterable {

	private static final long serialVersionUID = 1L;

	Set<FormComponent> visited = new HashSet<>();

	@Override
	public void component(Component c, IVisit<R> visit) {
		if (!visited.contains(c)) {
			if(c instanceof FormComponent){
				FormComponent fc = (FormComponent) c;
				c.add(new ValidationMsgBehavior());
//				c.add(new PropertyValidator<>());
				visited.add(fc);
			}
		}
	}

}
