package com.giffing.wicket.spring.boot.starter.app.classscanner;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import com.giffing.wicket.spring.boot.starter.app.classscanner.candidates.WicketClassCandidatesHolder;

@Configuration
@RequiredArgsConstructor
public class ClassCandidateScannerConfiguration {

    private final Environment environment;

    private final ResourceLoader resourceLoader;

    private final BeanFactory beanFactory;

    @Bean
    public WicketClassCandidatesHolder pageCandidates() {
        var result = new WicketClassCandidatesHolder();
        // TODO Why is the ClassCandidateScanner initialized?
        new ClassCandidateScanner(environment,
                resourceLoader,
                beanFactory,
                getClass().getClassLoader(),
                result)
                .postConstruct();
        return result;
    }

}
