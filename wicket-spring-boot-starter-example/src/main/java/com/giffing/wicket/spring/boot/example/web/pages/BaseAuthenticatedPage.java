package com.giffing.wicket.spring.boot.example.web.pages;

import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;
import com.giffing.wicket.spring.boot.example.web.pages.footer.Footer;
import com.giffing.wicket.spring.boot.example.web.pages.websocket.ChatPage;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapExternalLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuHeader;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.*;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;
import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconType;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAuthenticatedPage extends BasePage {

    protected BaseAuthenticatedPage(final PageParameters parameters) {
        super(parameters);
        add(newNavbar("navbar"));
        add(newNavigation("navigation"));
        add(new Footer("footer"));
    }

    protected Navbar newNavbar(String markupId) {
        var navbar = new Navbar(markupId)
                .setPosition(Navbar.Position.TOP)
                .setInverted(true)
                .setBackgroundColor(BackgroundColorBehavior.Color.Dark);

        navbar.setBrandName(Model.of("Wicket"));
        navbar.addComponents(NavbarComponents.transform(
                Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(CustomerListPage.class, Model.of("Customers")).setIconType(FontAwesome6IconType.person_s),
                new NavbarButton<Void>(ChatPage.class, Model.of("Chat")).setIconType(FontAwesome6IconType.rocketchat),
                new NavbarExternalLink(Model.of("https://github.com/MarcGiffing/wicket-spring-boot"))
                        .setLabel(Model.of("Github"))
                        .setTarget(BootstrapExternalLink.Target.blank)
                        .setIconType(FontAwesome6IconType.upload_s))
        );
        var dropdown = new NavbarDropDownButton(Model.of("Themes")) {

            @Override
            public boolean isActive(Component item) {
                return false;
            }

            @Override
            protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
                final List<AbstractLink> subMenu = new ArrayList<>();
                subMenu.add(new MenuHeader(Model.of("all available themes:")));
                subMenu.add(new MenuDivider());

                var settings = Bootstrap.getSettings(getApplication());
                var themes = settings.getThemeProvider().available();

                for (final ITheme theme : themes) {
                    var params = new PageParameters();
                    params.set("theme", theme.name());
                    subMenu.add(new MenuBookmarkablePageLink<Void>(getPageClass(), params, Model.of(theme.name())));
                }

                return subMenu;
            }
        }.setIconType(FontAwesome6IconType.book_s);

        navbar.addComponents(new ImmutableNavbarComponent(dropdown, Navbar.ComponentPosition.RIGHT));
        return navbar;
    }

    private void configureTheme(PageParameters pageParameters) {
        var theme = pageParameters.get("theme");

        if (!theme.isEmpty()) {
            var settings = Bootstrap.getSettings(getApplication());
            settings.getActiveThemeProvider().setActiveTheme(theme.toString(""));
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        configureTheme(getPageParameters());
    }

    protected boolean hasNavigation() {
        return false;
    }

    private Component newNavigation(String markupId) {
        var navigation = new WebMarkupContainer(markupId);
        navigation.setVisible(hasNavigation());
        return navigation;
    }
}
