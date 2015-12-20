package com.giffing.wicket.spring.boot.example.web.html.form.focus;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

public class FocusBehaviour extends Behavior{

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);
		OnDomReadyHeaderItem focusComponentHeaderItem = OnDomReadyHeaderItem.forScript("document.getElementById('"
				    + component.getMarkupId() + "').focus();");
		response.render(focusComponentHeaderItem);
	}

}
