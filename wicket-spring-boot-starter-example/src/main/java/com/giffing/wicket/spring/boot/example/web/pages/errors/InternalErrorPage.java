package com.giffing.wicket.spring.boot.example.web.pages.errors;

import com.giffing.wicket.spring.boot.context.scan.WicketInternalErrorPage;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@WicketInternalErrorPage
public class InternalErrorPage extends BasePage {
    public InternalErrorPage(PageParameters parameters) {
        super(parameters);
    }
}
