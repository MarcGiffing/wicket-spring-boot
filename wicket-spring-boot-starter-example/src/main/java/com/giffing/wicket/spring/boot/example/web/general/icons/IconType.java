package com.giffing.wicket.spring.boot.example.web.general.icons;

public enum IconType {
	CREATE("plus"),
	EDIT("edit"),
	DELETE("remove");
	
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
