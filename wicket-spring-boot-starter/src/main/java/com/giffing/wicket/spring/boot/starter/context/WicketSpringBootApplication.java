package com.giffing.wicket.spring.boot.starter.context;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;

/**
 * Annotation to simplify the configuration effort by clients.
 * The client {@link WicketBootWebApplication} should be marked with this
 * annotation to support enable Spring Boots auto configuration
 * 
 * @author Marc Giffing
 */
@SpringBootApplication
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WicketSpringBootApplication {
	
	String value() default "wicketBootWebApplication";
	
}
