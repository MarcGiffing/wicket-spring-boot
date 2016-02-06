# wicket-spring-boot-context

Advanced Level, a typical spring-boot user does not need this module.

## Description

This module is used to create **custom auto configuration classes**. All necessary code
is decoupled, thus a developer does not depend on the wicket-spring-boot-starter module
itself.

All dependencies in the wicket-spring-boot-context module are scoped as `provided` so the
user of the context module is not forced to use any spring/wicket dependencies.

## Basic Usage

Each configuration class should be marked with the annotation **@ApplicationInitExtension** 
and has to implement `WicketApplicationInitConfiguration`.

The module contains also common exceptions which should be used on e.g. configuration errors.

Here is also the place for common configuration checks which can be used by other projects.
The annotation **@ConditionalOnWicket**  can be used to activate a configuration only on a 
specific version of `Apache Wicket`.

### @ConditionalOnWicket

With the ConditionOnWicket annotation you can check that configuration classes only apply
on a specific Wicket major version. If some functionality is only available on Wicket 7 
you can use this annotation.

```java
@ApplicationInitExtension
@ConditionalOnWicket(value=7, range=Range.EQUALS_OR_HIGHER)
public ConditionalConfig implements WicketApplicationInitConfiguration{
	@Override
	public void init(WebApplication webApplication) {
		// configuration option which only apply to Wickets major version 7 or higher
	}
}
```

## Example


As an an example we will look to the AnnotatedMountScanner configuration. [annotated mount scanner](The https://github.com/wicketstuff/core/wiki/Annotation) 
is an project which supports bookmarkable URLs configured by annotations on WebPage classes. If you have this `@MountPath("login")` annotation on a 
WebPage then the Page is mounted to `http://localhost/login`.

In this project each configuration is separated in two classes to configure this particular feature/extension. The extension
consists of a property and a configuration class.

The property class holds properties to configure the specific feature. In the AnnotatedMountScannerProperties class we
found two properties:

```java
@ConfigurationProperties(prefix = AnnotatedMountScannerProperties.PROPERTY_PREFIX)
public class AnnotatedMountScannerProperties {
	public static final String PROPERTY_PREFIX = "wicket.stuff.annotationscan";
	/**
	 * @see AnnotatedMountScannerConfig
	 */
	private boolean enabled = true;

	/**
	 * An alternative package name for scanning for mount path if the
	 * WicketApplication should not used as the root scan package
	 */
	private String packagename;
```

This property file can be imported in the configuration class AnnotatedMountScannerConfig.

```java
/**
 * Auto configuration for the {@link AnnotatedMountScanner}.
 * 
 * It uses the user defined {@link WebApplication} as the default package scan
 * root directory.
 * 
 * Enables annotate mount scanner if the following two condition matches:
 * 
 * 1. The {@link AnnotatedMountScanner} is in the classpath.
 * 
 * 2. The property {@link AnnotatedMountScannerProperties#PROPERTY_PREFIX}
 * .enabled is true (default = true)
 * 
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = AnnotatedMountScannerProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(value = org.wicketstuff.annotation.scan.AnnotatedMountScanner.class)
@EnableConfigurationProperties({ AnnotatedMountScannerProperties.class })
public class AnnotatedMountScannerConfig implements WicketApplicationInitConfiguration {

	@Autowired
	private AnnotatedMountScannerProperties prop;

	@Override
	public void init(WebApplication webApplication) {
		String packagename = webApplication.getClass().getPackage().getName();
		if (prop.getPackagename() != null) {
			packagename = prop.getPackagename();
		}
		new AnnotatedMountScanner().scanPackage(packagename).mount(webApplication);
	}
}
```

If all conditions on the AnnotatedMountScannerConfig matches the configuration class is configured as
a spring bean. All Spring beans which implements the interface WicketApplicationInitConfiguration will
be injected as a list in the default WicketBootWebApplication class.  

In the WicketBootWebApplication class we iterate in Wickets init method over the list and call on each the
init method to configure the application.

```java
public class WicketBootWebApplication extends AuthenticatedWebApplication {
  @Autowired(required = false)
  private List<WicketApplicationInitConfiguration> configurations = new ArrayList<>();
  @Override
  protected void init() {
    super.init();
    for (WicketApplicationInitConfiguration configuration : configurations) {
      configuration.init(this);
    }
  }
}
```

## Further Reading

To fully understand how [Spring Boots autconfiguration](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-developing-auto-configuration) 
and in general Spring Boot works you should read the excellent [documentation](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) from this fantastic [project](http://projects.spring.io/spring-boot/).
