package com.giffing.wicket.spring.boot.starter.app.verifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

@Configuration
@ConditionalOnProperty(prefix = WicketDependencyVersionCheckerProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@EnableConfigurationProperties({ WicketDependencyVersionCheckerProperties.class })
public class WicketDependencyVersionChecker implements ResourceLoaderAware {

	private final Logger log = LoggerFactory.getLogger( WicketDependencyVersionChecker.class );

	static final String DEFAULT_RESOURCE_PATTERN = "/META-INF/maven/**/pom.properties";

	private static final String WICKETSTUFF_GROUPID = "org.wicketstuff";

	private static final String WICKET_JQUERYUI_GROUPID = "com.googlecode.wicket-jquery-ui";

	private static final String WICKET_CORE_GROUPID = "org.apache.wicket";

	private ResourcePatternResolver resourcePatternResolver;

	private final WicketDependencyVersionCheckerProperties props;

	@Autowired
	public WicketDependencyVersionChecker(WicketDependencyVersionCheckerProperties props) {
		this.props = props;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
	}

	@PostConstruct
	public void detectWicketDependencyVersionMismatch() {
		List<MavenDependency> wicketMavenDependencies = collectWicketMavenDependencies();
		String wicketCoreVersion = findWicketCoreVersion( wicketMavenDependencies );

		boolean versionMismatchFound = false;
		List<MavenDependency> mismatchVersionDependencies = new ArrayList<>();
		for ( MavenDependency mavenDependency : wicketMavenDependencies ) {
                    if (mavenDependency.groupId.equals(WICKET_CORE_GROUPID) && !mavenDependency.version.equals(wicketCoreVersion)) {
                        log.error("########## INVALID WICKET VERSION DETECTED - CORE: {} - DEPENDENCY: {}",
                                wicketCoreVersion, mavenDependency);
                        versionMismatchFound = true;
                        mismatchVersionDependencies.add(mavenDependency);
                    } else if (mavenDependency.groupId.equals(WICKETSTUFF_GROUPID) || mavenDependency.groupId.equals(WICKET_JQUERYUI_GROUPID)) {
                        String majorWicketCoreVersion = wicketCoreVersion.substring(0, wicketCoreVersion.indexOf('.'));
                        String majorMavenDependencyVersion = mavenDependency.version.substring(0, mavenDependency.version.indexOf('.'));
                        if (!majorWicketCoreVersion.equals(majorMavenDependencyVersion)) {
                            log.error("########## INVALID {} MAJOR VERSION DETECTED - WICKET: {} - DEPENDENCY: {}",
                                    mavenDependency.groupId, majorWicketCoreVersion, majorMavenDependencyVersion);
                            versionMismatchFound = true;
                            mismatchVersionDependencies.add(mavenDependency);
                        }
                    }
		}

		if(versionMismatchFound && props.isThrowExceptionOnDependencyVersionMismatch()) {
			throw new WicketDependencyMismatchDetectedException( wicketCoreVersion, mismatchVersionDependencies );
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

    private List<MavenDependency> collectWicketMavenDependencies() {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + DEFAULT_RESOURCE_PATTERN;
        try {
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            return collectWicketMavenDependencies(resources);
        } catch(IOException e) {
            // do not throw checked exception in @PostConstruct method to provide better JavaEE compatibility
            throw new IllegalStateException(e);
        }
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

	public static class MavenDependency {
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
