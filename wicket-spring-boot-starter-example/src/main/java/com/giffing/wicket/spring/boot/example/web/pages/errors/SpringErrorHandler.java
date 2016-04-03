package com.giffing.wicket.spring.boot.example.web.pages.errors;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringErrorHandler implements ErrorController {

	@RequestMapping("error")
	public String error() {
		return "redirect:/problem";
	}

	@Override
	public String getErrorPath() {
		return "problem";
	}

}
