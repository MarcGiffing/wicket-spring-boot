package com.giffing.wicket.spring.boot.starter.context;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giffing.wicket.spring.boot.starter.app.WicketBootStandardWebApplication;

/**
 * Annotation to simplify the configuration effort by clients.
 * The client {@link WicketBootStandardWebApplication} should be marked with this
 * annotation to support enable Spring Boots auto configuration
 * 
 * @author Marc Giffing
 * 
 * @deprecated will be removed in the next releases. You will now only need {@link SpringBootApplication}
 * 
 * TODO - move to context project?
 */
@SpringBootApplication
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WicketSpringBootApplication {
	
	String value() default "wicketBootWebApplication";
	
}
