package com.giffing.wicket.spring.boot.example.web.pages.errors;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.wicket.request.http.WebResponse;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.context.scan.WicketAccessDeniedPage;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;

@MountPath("problem")
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
