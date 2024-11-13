package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.htmlcompressor;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketAutoConfig;
import com.giffing.wicket.spring.boot.context.extensions.boot.actuator.WicketEndpointRepository;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory;

import java.lang.reflect.Method;

/**
 * Enables the HTML markup compression if the following two conditions matches:
 * <p>
 * 1. The {@link HtmlCompressingMarkupFactory} class is present
 * <p>
 * 2. The {@link HTMLCompressingProperties}.compressHTMLEnabled is set to true
 * (default is true).
 *
 * @author Marc Giffing
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = HTMLCompressingProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory.class)
@EnableConfigurationProperties({HTMLCompressingProperties.class})
@RequiredArgsConstructor
@Slf4j
public class HTMLCompressingConfig implements WicketApplicationInitConfiguration {

    private final HTMLCompressingProperties props;

    private final WicketEndpointRepository wicketEndpointRepository;

    @Override
    public void init(WebApplication webApplication) {
        var compressor = new HtmlCompressor();
        setFeatureConfiguration(compressor);
        webApplication.getMarkupSettings().setMarkupFactory(new HtmlCompressingMarkupFactory(compressor));

        wicketEndpointRepository.add(new WicketAutoConfig.Builder(this.getClass())
                .withDetail("properties", props)
                .build());
    }

    private void setFeatureConfiguration(HtmlCompressor compressor) {
        for (var entrySet : props.getFeatures().entrySet()) {
            Method method = null;
            var capitalizedKey = StringUtils.capitalize(entrySet.getKey());
            var methodname = "set" + capitalizedKey;
            try {
                method = compressor.getClass().getMethod(methodname, boolean.class);
                method.setAccessible(true);
                ReflectionUtils.invokeMethod(method, compressor, entrySet.getValue());
            } catch (Exception e) {
                log.warn("failed to invoke method: {} with value {}. Exception: {}", methodname, entrySet.getValue(), e.getMessage());
            }
        }
    }
}
