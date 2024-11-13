package com.giffing.wicket.spring.boot.starter.app.verifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@EnableConfigurationProperties({WicketDependencyVersionCheckerProperties.class})
@Slf4j
public class WicketDependencyVersionChecker implements ResourceLoaderAware {

    private static final String DEFAULT_RESOURCE_PATTERN = "/META-INF/maven/**/pom.properties";

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
        var wicketMavenDependencies = collectWicketMavenDependencies();

        List<MavenDependency> mismatchVersionDependencies = new ArrayList<>();
        var versionMismatchFound = new AtomicBoolean(false);
        Optional<String> wicketCoreVersionOpt = findWicketCoreVersion(wicketMavenDependencies);
        wicketCoreVersionOpt.ifPresent(wicketCoreVersion -> {
            for (var mavenDependency : wicketMavenDependencies) {
                if (mavenDependency.groupId.equals(WICKET_CORE_GROUPID)
                        && !mavenDependency.version.equals(wicketCoreVersion)) {
                    log.error("########## INVALID WICKET VERSION DETECTED - CORE: {} - DEPENDENCY: {}",
                            wicketCoreVersion, mavenDependency);
                    versionMismatchFound.set(true);
                    mismatchVersionDependencies.add(mavenDependency);
                } else if (mavenDependency.groupId.equals(WICKETSTUFF_GROUPID) || mavenDependency.groupId.equals(WICKET_JQUERYUI_GROUPID)) {
                    var majorWicketCoreVersion = wicketCoreVersion.substring(0, wicketCoreVersion.indexOf('.'));
                    var majorMavenDependencyVersion = mavenDependency.version.substring(0, mavenDependency.version.indexOf('.'));
                    if (!majorWicketCoreVersion.equals(majorMavenDependencyVersion)) {
                        log.error("########## INVALID {} MAJOR VERSION DETECTED - WICKET: {} - DEPENDENCY: {}",
                                mavenDependency.groupId, majorWicketCoreVersion, majorMavenDependencyVersion);
                        versionMismatchFound.set(true);
                        mismatchVersionDependencies.add(mavenDependency);
                    }
                }

            }
        });


        if (versionMismatchFound.get() && props.isThrowExceptionOnDependencyVersionMismatch()) {
            throw new WicketDependencyMismatchDetectedException(wicketCoreVersionOpt.orElse("unknown"), mismatchVersionDependencies);
        }

    }

    private Optional<String> findWicketCoreVersion(List<MavenDependency> wicketMavenDependencies) {
        for (var wicketMavenDependency : wicketMavenDependencies) {
            if (wicketMavenDependency.groupId.equals(WICKET_CORE_GROUPID) && wicketMavenDependency.artifactId.equals("wicket-core")) {
                return Optional.of(wicketMavenDependency.version);
            }
        }
        return Optional.empty();
    }

    private List<MavenDependency> collectWicketMavenDependencies() {
        var packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + DEFAULT_RESOURCE_PATTERN;
        try {
            var resources = this.resourcePatternResolver.getResources(packageSearchPath);
            return collectWicketMavenDependencies(resources);
        } catch (IOException e) {
            // do not throw checked exception in @PostConstruct method to provide better JavaEE compatibility
            throw new IllegalStateException(e);
        }
    }

    private List<MavenDependency> collectWicketMavenDependencies(Resource[] resources) throws IOException {
        List<MavenDependency> wicketMavenDependencies = new ArrayList<>();
        for (var resource : resources) {
            if (resource.isReadable()
                    && resource.getURL().getPath().contains("wicket")) {

                var props = new Properties();
                props.load(resource.getInputStream());
                var groupId = (String) props.get("groupId");
                var artifactId = (String) props.get("artifactId");
                var version = (String) props.get("version");

                wicketMavenDependencies.add(new MavenDependency(groupId, artifactId, version));
            }
        }
        return wicketMavenDependencies;
    }

    @RequiredArgsConstructor
    public static class MavenDependency {
        public final String groupId;
        public final String artifactId;
        public final String version;

        @Override
        public String toString() {
            return groupId + ":" + artifactId + ":" + version;
        }
    }

}
