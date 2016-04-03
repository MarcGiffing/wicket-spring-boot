package com.giffing.wicket.spring.boot.example.web.pages.errors;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.http.WebResponse;

import com.giffing.wicket.spring.boot.context.scan.WicketAccessDeniedPage;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;

@WicketAccessDeniedPage
public class AccessDeniedPage extends BasePage {

	
	@Override
	protected void setHeaders(final WebResponse response)
	{
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}
	
	@Override
	public boolean isErrorPage()
	{
		return true;
	}

	@Override
	public boolean isVersioned()
	{
		return false;
	}
	
}
