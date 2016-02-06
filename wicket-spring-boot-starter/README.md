# wicket-spring-boot-starter

Start using Apache Wicket within your Spring Boot Application.

## Getting Started

Following the spring-boot-starter concept, just add the dependency:

```xml
<dependency>
  <groupId>com.giffing.wicket.spring.boot.starter</groupId>
  <artifactId>wicket-spring-boot-starter</artifactId>
</dependency>
```

Beside the Maven dependency configuration we need the following steps to do

* Create a public class which extends from WicketBootWebApplication
* Annotate the class with @WicketSpringBootApplication
* Within the main method
* use the SpringApplicationBuilder to run the WicketApplication class you've created
* Define your project HomePage.


```java
@WicketSpringBootApplication
public class WicketApplication extends WicketBootWebApplication {
  public static void main(String[] args) throws Exception {
    new SpringApplicationBuilder()
      .sources(WicketApplication.class)
      .run(args);
  }

  @Override
  public Class<? extends Page> getHomePage() {
    return YourHomePage.class;
  }
}
```

Start the application as usual (e.g. with `mvn spring-boot:run`).

As in every Wicket application you can override the WicketBootWebApplication.

### Spring profile configuration

The Wicket Spring Boot Starter project ships with a default development configuration.
It can be activated by activating the 'development' Spring profile in the main class or over
external JVM/Maven arguments.

The default configuration can be overridden with a custom property file. See [Spring Boots reference documentation](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config).

[source](wicket-spring-boot-starter/src/main/resources/application-development.yml)
```yml
wicket:
  core:
    settings:
      general:
        configuration-type: development
      debug:
        enabled: true
        component-use-check: true
        development-utilities-enabled: true
  stuff:
    htmlcompressor:
      enabled: false
      features:
        removeComments: false
        removeMultiSpaces: false
        removeIntertagSpaces: false
        removeQuotes: false
        compressJavaScript: false
        compressCss: false
        simpleDoctype: false
        removeScriptAttributes: false
        removeStyleAttributes: false
        removeLinkAttributes: false
        removeFormAttributes: false
        removeInputAttributes: false
        simpleBooleanAttributes: false
        removeJavaScriptProtocol: false
        removeHttpProtocol: false
        removeHttpsProtocol: false
        preserveLineBreaks: false
  external:
    development:
      devutils:
        statelesschecker:
          enabled: true
        interceptor:
          enable-live-sessions-page: true
        diskstorebrowser:
          enabled: true
      wicketsource:
        enabled: true
```

