package com.giffing.wicket.spring.boot.starter.app.classscanner;

import com.giffing.wicket.spring.boot.context.scan.*;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidate;
import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * The candidate class scanner is used to find class with special annotations.
 *
 * @author Marc Giffing
 */
@RequiredArgsConstructor
public class ClassCandidateScanner {

    private final Environment environment;
    private final ResourceLoader resourceLoader;
    private final BeanFactory beanFactory;
    private final ClassLoader classLoader;
    private final WicketClassCandidatesHolder classCandidates;

    public void postConstruct() {
        var scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.setEnvironment(this.environment);
        scanner.setResourceLoader(this.resourceLoader);
        scanner.addIncludeFilter(new AnnotationTypeFilter(SpringBootApplication.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(WicketHomePage.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(WicketSignInPage.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(WicketAccessDeniedPage.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(WicketExpiredPage.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(WicketInternalErrorPage.class));
        for (var basePackage : getMappingBasePackages(beanFactory)) {
            if (StringUtils.hasText(basePackage)) {
                classCandidates.getBasePackages().add(basePackage);
                var beanDefinitions = scanner.findCandidateComponents(basePackage);
                for (var beanDefinition : beanDefinitions) {
                    Class<?> beanClass;
                    try {
                        beanClass = ClassUtils.forName(beanDefinition.getBeanClassName(), classLoader);
                    } catch (ClassNotFoundException e) {
                        throw new IllegalStateException(e);
                    }
                    if (beanClass.isAnnotationPresent(WicketHomePage.class)) {
                        classCandidates.getHomePageCandidates()
                                .add(new WicketClassCandidate<>((Class<Page>) beanClass));
                    }
                    if (beanClass.isAnnotationPresent(WicketSignInPage.class)) {
                        classCandidates.getSignInPageCandidates()
                                .add(new WicketClassCandidate<>((Class<WebPage>) beanClass));
                    }
                    if (beanClass.isAnnotationPresent(WicketAccessDeniedPage.class)) {
                        classCandidates.getAccessDeniedPageCandidates()
                                .add(new WicketClassCandidate<>((Class<Page>) beanClass));
                    }
                    if (beanClass.isAnnotationPresent(WicketExpiredPage.class)) {
                        classCandidates.getExpiredPageCandidates()
                                .add(new WicketClassCandidate<>((Class<Page>) beanClass));
                    }
                    if (beanClass.isAnnotationPresent(WicketInternalErrorPage.class)) {
                        classCandidates.getInternalErrorPageCandidates()
                                .add(new WicketClassCandidate<>((Class<Page>) beanClass));
                    }
                    if (beanClass.isAnnotationPresent(SpringBootApplication.class)) {
                        classCandidates.setSpringBootMainClass(beanClass);
                    }
                }

            }
        }
    }

    private static Collection<String> getMappingBasePackages(BeanFactory beanFactory) {
        try {
            return AutoConfigurationPackages.get(beanFactory);
        } catch (IllegalStateException ex) {
            // no auto-configuration package registered yet
            return Collections.emptyList();
        }
    }

}