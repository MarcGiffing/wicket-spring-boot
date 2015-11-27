package com.giffing.wicket.spring.boot.context.condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

/**
 * Conditional annotation to pre-check if an extension should be picked for autoconfiguration. 
 * 
 * You can define the major Wicket version on which the extension
 * should or should not be executed.
 * 
 * @author Marc Giffing
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(WicketSettingsCondition.class)
public @interface ConditionalOnWicket {
	
	/**
	 * @return The major java version to check with the current value 
	 */
	int value();
	
	/**
	 * @return Defines how the given major version should be checked with the current version
	 */
	Range range() default Range.EQUALS_OR_HIGHER;

	

	
	enum Range {
		/**
		 * The Wicket major version equals the 
		 */
		EQUALS,
		/**
		 * The Wicket major version equals or is newer
		 */
		EQUALS_OR_HIGHER,
		
		/**
		 * The Wicket major version equals or is lower
		 */
		EQUALS_OR_LOWER,

	}
}
