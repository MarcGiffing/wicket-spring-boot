package com.giffing.wicket.spring.boot.example.web.html.form.button.events;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.util.io.IClusterable;

@FunctionalInterface
public interface OnSubmitEvent extends IClusterable {

	void apply(Button button);
	
}
