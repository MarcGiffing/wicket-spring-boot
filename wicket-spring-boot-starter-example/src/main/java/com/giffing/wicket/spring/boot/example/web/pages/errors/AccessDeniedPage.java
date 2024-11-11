package com.giffing.wicket.spring.boot.example.web.pages.errors;

import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.context.scan.WicketAccessDeniedPage;

@MountPath("problem")
@WicketAccessDeniedPage
public class AccessDeniedPage extends BasePage {

	public AccessDeniedPage(PageParameters parameters) {
		super(parameters);
	}

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
