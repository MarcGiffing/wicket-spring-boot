package com.giffing.wicket.spring.boot.example.web.pages.errors;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.http.WebResponse;

import com.giffing.wicket.spring.boot.context.scan.WicketExpiredPage;

@WicketExpiredPage
public class ExpiredPage extends WebPage{

	@Override
	protected void setHeaders(final WebResponse response)
	{
		response.setStatus(HttpServletResponse.SC_GONE);
	}
	
}
