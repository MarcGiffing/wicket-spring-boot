package com.giffing.wicket.spring.boot.starter.app.classscanner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

@Configuration
public class WicketDependencyVersionChecker implements ResourceLoaderAware {

	private Logger log = LoggerFactory.getLogger( WicketDependencyVersionChecker.class );
	
	static final String DEFAULT_RESOURCE_PATTERN = "/META-INF/maven/**/pom.properties";

	private static final String WICKETSTUFF_GROUPID = "org.wicketstuff";
	
	private static final String WICKET_CORE_GROUPID = "org.apache.wicket";
	
	private ResourcePatternResolver resourcePatternResolver;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
	}
	
	@PostConstruct
	public void detectWicketDependencyVersionMissmatch() throws IOException {
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +  DEFAULT_RESOURCE_PATTERN;
		Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
		
		List<MavenDependency> wicketMavenDependencies = collectWicketMavenDependencies( resources);
		String wicketCoreVersion = findWicketCoreVersion( wicketMavenDependencies );
		
		for ( MavenDependency mavenDependency : wicketMavenDependencies ) {
			if(mavenDependency.groupId.equals( WICKET_CORE_GROUPID ) || mavenDependency.groupId.equals( WICKETSTUFF_GROUPID ) ) {
				if(!mavenDependency.version.equals( wicketCoreVersion )) {
					log.error( "########## INVALID WICKET VERSION DETECTED - CORE: {} - DEPENDENCY: {}", wicketCoreVersion,  mavenDependency);
				}
			}
		}
		
	}

	private String findWicketCoreVersion(List<MavenDependency> wicketMavenDependencies) {
		for (MavenDependency wicketMavenDependency : wicketMavenDependencies ) {
			if( wicketMavenDependency.groupId.equals( WICKET_CORE_GROUPID ) && wicketMavenDependency.artifactId.equals( "wicket-core" )) {
				return wicketMavenDependency.version;
			}
		}
		return null;
	}

	private List<MavenDependency> collectWicketMavenDependencies(Resource[] resources) throws IOException {
		List<MavenDependency> wicketMavenDependencies = new ArrayList<>();
		for (Resource resource : resources) {
			if(resource.isReadable() 
					&& resource.getURL().getPath().contains( "wicket" )) {
				
				Properties props = new Properties();
				props.load( resource.getInputStream());
				String groupId = String.class.cast( props.get( "groupId" ));
				String artifactId = String.class.cast( props.get( "artifactId" ));
				String version = String.class.cast( props.get( "version" ));
				
				wicketMavenDependencies.add( new MavenDependency( groupId, artifactId, version ) );
			}
		}
		return wicketMavenDependencies;
	}
	
	private static class MavenDependency {
		public String groupId;
		public String artifactId;
		public String version;
		
		public MavenDependency(String groupId, String artifactId, String version) {
			this.groupId = groupId;
			this.artifactId = artifactId;
			this.version = version;
		}
		
		@Override
		public String toString() {
			return groupId + ":" + artifactId + ":" + version;
		}
	}

}
