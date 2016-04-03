package com.giffing.wicket.spring.boot.context.scan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.wicket.Page;
import org.apache.wicket.settings.ApplicationSettings;

/**
 * This annotation can be used to mark a {@link Page} which will automatically configured
 * as the access denied page in the {@link ApplicationSettings}.
 * 
 * @author Marc Giffing
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface WicketAccessDeniedPage {

}
