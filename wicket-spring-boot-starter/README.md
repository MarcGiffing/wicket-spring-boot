= wicket-spring-boot-starter

Start using Apache Wicket within your Spring Boot Application.

== Getting Started

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
