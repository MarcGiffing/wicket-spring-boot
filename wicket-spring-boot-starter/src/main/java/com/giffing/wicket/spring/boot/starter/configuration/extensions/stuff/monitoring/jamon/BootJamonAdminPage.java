package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.monitoring.jamon;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.wicketstuff.jamon.component.JamonAdminPage;

public class BootJamonAdminPage extends JamonAdminPage {

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(JamonCssResourceReference.INSTANCE));
    }

}
