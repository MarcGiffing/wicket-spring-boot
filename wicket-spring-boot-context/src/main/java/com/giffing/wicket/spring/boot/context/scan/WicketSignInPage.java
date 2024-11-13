package com.giffing.wicket.spring.boot.context.scan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Should be used to mark the sign-in page which should be automatically configured in the {@link WebApplication} class.
 * Only one {@link Page} should be marked with this annotation.
 *
 * @author Marc Giffing
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface WicketSignInPage {

}
