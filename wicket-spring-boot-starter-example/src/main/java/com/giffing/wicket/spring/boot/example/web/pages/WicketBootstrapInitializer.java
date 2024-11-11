package com.giffing.wicket.spring.boot.example.web.pages;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.CookieThemeProvider;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.sass.BootstrapSass;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.apache.wicket.protocol.http.WebApplication;

@ApplicationInitExtension
public class WicketBootstrapInitializer implements WicketApplicationInitConfiguration {
    @Override
    public void init(WebApplication webApplication) {
        final IBootstrapSettings settings = new BootstrapSettings();
        Bootstrap.builder().withBootstrapSettings(settings).install(webApplication);
        final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.Cerulean);

        settings.setJsResourceFilterName("footer-container")
                .setThemeProvider(themeProvider)
                .setActiveThemeProvider(new CookieThemeProvider());

        BootstrapSass.install(webApplication);
        webApplication.getCspSettings().blocking().disabled();
        webApplication.getMarkupSettings().setStripWicketTags(true);

        webApplication.getHeaderResponseDecorators().add(response -> new JavaScriptFilteredIntoFooterHeaderResponse(response, "footer-container"));
    }
}
