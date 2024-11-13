package com.giffing.wicket.spring.boot.context.extensions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * To be independent of Springs annotation this annotation was introduced
 * which is a replacement for the {@link Component} annotation.
 * <p>
 * In the future, you may introduce different configuration options.
 *
 * @author Marc Giffing
 */
@Configuration
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Order(ApplicationInitExtension.DEFAULT_PRECEDENCE)
public @interface ApplicationInitExtension {

    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    int DEFAULT_PRECEDENCE = Integer.MAX_VALUE / 2;

    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    String value() default "";
}
