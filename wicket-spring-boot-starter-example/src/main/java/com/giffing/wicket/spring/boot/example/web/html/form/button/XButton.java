package com.giffing.wicket.spring.boot.example.web.html.form.button;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;

import com.giffing.wicket.spring.boot.example.web.html.form.button.events.OnSubmitEvent;

public class XButton extends Button {

	private OnSubmitEvent onSubmitEvent;
	
	public XButton(String id) {
		super(id);
	}
	
	public XButton(String id, IModel<String> model) {
		super(id, model);
	}

	@Override
	public void onSubmit() {
		onSubmitEvent.apply(this);
	}

	public OnSubmitEvent getOnSubmitEvent() {
		return onSubmitEvent;
	}

	public XButton setOnSubmitEvent(OnSubmitEvent onSubmitEvent) {
		this.onSubmitEvent = onSubmitEvent;
		return this;
	}

	
	
}
