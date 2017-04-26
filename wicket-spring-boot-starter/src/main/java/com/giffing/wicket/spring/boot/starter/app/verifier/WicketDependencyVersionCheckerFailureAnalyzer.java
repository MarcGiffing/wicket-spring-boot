package com.giffing.wicket.spring.boot.starter.app.verifier;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

import com.giffing.wicket.spring.boot.starter.app.verifier.WicketDependencyVersionChecker.MavenDependency;

public class WicketDependencyVersionCheckerFailureAnalyzer extends AbstractFailureAnalyzer<WicketDependencyMismatchDetectedException>{

	public static String newline = System.getProperty("line.separator");
	
	@Override
	protected FailureAnalysis analyze(Throwable rootFailure, WicketDependencyMismatchDetectedException cause) {
		String descriptionMessage = "One ore more Wicket dependencies (jars) doesn't match the wicket-core dependency.\n\r" 
				+"Wicket Core Version: " + cause.getWicketCoreVersion() + newline;
		for(MavenDependency dependency :cause.getDependencies()) {
			descriptionMessage += "\t" + dependency + newline;
		}
		
		String actionMessage = "Please check the Wicket versions configured in your dependency management system (Maven, Gradle, ...) " + newline
				+ "You can disable this check via the property:" + newline
				+ "\twicket.verifier.dependencies.enabled=false." + newline
				+ "You can prevent throwing the exception but still log the detected problems via the property:" + newline
				+ "\twicket.verifier.dependencies.throw-exception-on-dependency-version-mismatch=false";
		
		return new FailureAnalysis(descriptionMessage, actionMessage, cause);
	}

}
