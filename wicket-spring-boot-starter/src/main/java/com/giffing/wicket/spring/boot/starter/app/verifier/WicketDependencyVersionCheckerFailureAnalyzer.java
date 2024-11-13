package com.giffing.wicket.spring.boot.starter.app.verifier;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

import com.giffing.wicket.spring.boot.starter.app.verifier.WicketDependencyVersionChecker.MavenDependency;

public class WicketDependencyVersionCheckerFailureAnalyzer extends AbstractFailureAnalyzer<WicketDependencyMismatchDetectedException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, WicketDependencyMismatchDetectedException cause) {
        var descriptionMessage = new StringBuilder("One or more Wicket dependencies (jars) doesn't match the wicket-core dependency.\n\r"
                + "Wicket Core Version: %s%s".formatted(cause.getWicketCoreVersion(), System.lineSeparator()));
        for (MavenDependency dependency : cause.getDependencies()) {
            descriptionMessage.append("\t").append(dependency).append(System.lineSeparator());
        }

        String actionMessage = """
                Please check the Wicket versions configured in your dependency management system (Maven, Gradle, ...)
                You can disable this check via the property:
                \twicket.verifier.dependencies.enabled=false.
                You can prevent throwing the exception but still log the detected problems via the property:
                \twicket.verifier.dependencies.throw-exception-on-dependency-version-mismatch=false""";

        return new FailureAnalysis(descriptionMessage.toString(), actionMessage, cause);
    }

}
