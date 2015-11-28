package com.giffing.wicket.spring.boot.context.extensions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * To be independent from Springs annotation this annotation was introduced which is
 * a replacement for the {@link Component} annotation. 
 * 
 * In future you may introduce different configuration options.
 * 
 * @author Marc Giffing
 *
 */
@Configuration
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ApplicationInitExtension {

	String value() default "";
	
}
