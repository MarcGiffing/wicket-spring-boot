# wicket-spring-boot-context

Advanced Level, a typical spring-boot user does not need this module.

###Description

This module is used to create **custom auto configuration classes**. All necessary code
is decoupled, thus a developer does not depend on the wicket-spring-boot-starter module
itself.

All dependencies in the wicket-spring-boot-context module are scoped as `provided` so the
user of the context module is not forced to use any spring/wicket dependencies.

Each configuration class should be marked with the annotation **@ApplicationInitExtension** 
and has to implement `WicketApplicationInitConfiguration`.

The module contains also common exceptions which should be used on e.g. configuration errors.

Here is also the place for common configuration checks which can be used by other projects.
The annotation **@ConditionalOnWicket**  can be used to activate a configuration only on a 
specific version of `Apache Wicket`.
