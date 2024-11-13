package com.giffing.wicket.spring.boot.starter.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.StringUtils;

/**
 * To prevent spring bean naming clashes with user defined beans we will
 * prefix each bean name with wicketextension and capitalize the from spring
 * generated bean name.
 *
 * @author Marc Giffing
 */
public class CustomAnnotationBeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return "wicketextension" + StringUtils.capitalize(super.generateBeanName(definition, registry));
    }

}
