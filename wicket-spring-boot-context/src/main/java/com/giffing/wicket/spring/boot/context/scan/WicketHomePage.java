package com.giffing.wicket.spring.boot.context.scan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.stereotype.Component;

/**
 * Should be used to mark the home page which should automatically configures in the {@link WebApplication}.
 * Only one {@link Page} should be marked with this annotation.
 * 
 * @author Marc Giffing
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
@Inherited
public @interface WicketHomePage {

}
