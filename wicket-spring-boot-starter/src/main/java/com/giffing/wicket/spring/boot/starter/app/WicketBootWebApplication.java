package com.giffing.wicket.spring.boot.starter.app;

import org.apache.wicket.protocol.http.WebApplication;

import com.giffing.wicket.spring.boot.starter.web.WicketWebInitializer;

/**
 * All Wicket Spring Boot Starter {@link WebApplication} classes should
 * implement this interface to access the current configures
 * {@link WebApplication} bean within spring.
 * <p>
 * Look at {@link WicketWebInitializer} to see the dynamical retrieval of the
 * bean name with use of this interface.
 *
 * @author Marc Giffing
 */
public interface WicketBootWebApplication {

}
