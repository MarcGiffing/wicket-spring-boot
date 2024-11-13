package com.giffing.wicket.spring.boot.example.web.pages.errors;

import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.wicket.request.http.WebResponse;

import com.giffing.wicket.spring.boot.context.scan.WicketExpiredPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@WicketExpiredPage
public class ExpiredPage extends BasePage {

    public ExpiredPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void setHeaders(final WebResponse response) {
        response.setStatus(HttpServletResponse.SC_GONE);
    }

}
