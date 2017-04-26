package com.giffing.wicket.spring.boot.starter.app.verifier;

import java.util.ArrayList;
import java.util.List;

import com.giffing.wicket.spring.boot.starter.app.verifier.WicketDependencyVersionChecker.MavenDependency;

/**
 * Should  be thrown if the version of wicket dependencies doesn't match the core dependency version
 * 
 * @author Marc Giffing
 *
 */
public class WicketDependencyMismatchDetectedException extends RuntimeException {

	private String wicketCoreVersion;
	
	private List<MavenDependency> dependencies = new ArrayList<>();
	
	public WicketDependencyMismatchDetectedException(String wicketCoreVersion, List<MavenDependency> dependencies) {
		this.wicketCoreVersion = wicketCoreVersion;
		this.dependencies = dependencies;
	}

	
	public String getWicketCoreVersion() {
		return wicketCoreVersion;
	}

	
	public void setWicketCoreVersion(String wicketCoreVersion) {
		this.wicketCoreVersion = wicketCoreVersion;
	}

	
	public List<MavenDependency> getDependencies() {
		return dependencies;
	}

	
	public void setDependencies(List<MavenDependency> dependencies) {
		this.dependencies = dependencies;
	}
	
}
