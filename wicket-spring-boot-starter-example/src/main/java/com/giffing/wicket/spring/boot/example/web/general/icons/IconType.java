package com.giffing.wicket.spring.boot.example.web.general.icons;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconType;

public enum IconType {
	CREATE(FontAwesome6IconType.plus_s.cssClassName()),
	EDIT(FontAwesome6IconType.pen_s.cssClassName()),
	DELETE(FontAwesome6IconType.minus_s.cssClassName());
	
	private String cssType;
	
	IconType(String cssType){
		this.setCssType(cssType);
	}

	public String getCssName() {
		return cssType;
	}

	public void setCssType(String cssType) {
		this.cssType = cssType;
	}
	
}
