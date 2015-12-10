package com.giffing.wicket.spring.boot.example.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class SecondPage extends WebPage{

	public SecondPage(){
		queue(new BookmarkablePageLink<String>("firstPage", FirstPage.class));
	}
	
}
