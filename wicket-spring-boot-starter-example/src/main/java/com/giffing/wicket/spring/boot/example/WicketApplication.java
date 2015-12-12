package com.giffing.wicket.spring.boot.example;

import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import com.giffing.wicket.spring.boot.starter.context.WicketSpringBootApplication;

@WicketSpringBootApplication
@ComponentScan(basePackages = "com.giffing.wicket.spring.boot.example")
public class WicketApplication extends WicketBootWebApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder().sources(WicketApplication.class).run(args);
	}

	@Override
	protected void init() {
		super.init();

		IPackageResourceGuard packageResourceGuard = this.getResourceSettings().getPackageResourceGuard();
		if (packageResourceGuard instanceof SecurePackageResourceGuard) {
			SecurePackageResourceGuard guard = (SecurePackageResourceGuard) packageResourceGuard;
			// Allow to access only to pdf files placed in the “public”
			// directory.
			guard.addPattern("+*.map");
		}
	}

}
