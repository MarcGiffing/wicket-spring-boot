package com.giffing.wicket.spring.boot.starter.app.verifier;

import com.giffing.wicket.spring.boot.starter.app.verifier.WicketDependencyVersionChecker.MavenDependency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Should  be thrown if the version of wicket dependencies doesn't match the core dependency version
 *
 * @author Marc Giffing
 */
@Getter
@RequiredArgsConstructor
public class WicketDependencyMismatchDetectedException extends RuntimeException {

    private final String wicketCoreVersion;

    private final List<MavenDependency> dependencies;

}
