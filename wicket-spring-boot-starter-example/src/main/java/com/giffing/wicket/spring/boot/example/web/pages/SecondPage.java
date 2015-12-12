package com.giffing.wicket.spring.boot.example.web.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("second-page")
public class SecondPage extends WebPage{

	public SecondPage(){
		queue(new BookmarkablePageLink<String>("firstPage", FirstPage.class));
	}
	
}
