package com.giffing.wicket.spring.boot.starter.condition;

import java.util.Map;

import org.apache.wicket.settings.FrameworkSettings;
import org.apache.wicket.util.string.Strings;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.giffing.wicket.spring.boot.starter.condition.ConditionalOnWicket.Range;

/**
 * Retrieves the current major Wicket Version from the classpath and matches it against
 * the given conditional value in the {@link ConditionalOnWicket} annotation.
 * 
 * @author Marc Giffing
 *
 */
public class WicketSettingsCondition extends SpringBootCondition {

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String implVersion = null;
		String wicketVersion = retrieveWicketVersion(implVersion);
		
		Map<String, Object> attributes = metadata
				.getAnnotationAttributes(ConditionalOnWicket.class.getName());
		Range range = (Range) attributes.get("range");
		int expectedVersion = (int) attributes.get("value");
		String[] splittedWicketVersion = wicketVersion.split("\\.");
		int majorWicketVersion = Integer.valueOf(splittedWicketVersion[0]);
		return getMatchOutcome(range, majorWicketVersion, expectedVersion);
	}

	protected ConditionOutcome getMatchOutcome(Range range, int runningVersion,
			int expectedVersion) {
		boolean match = matches(range, expectedVersion, runningVersion);
		return new ConditionOutcome(match, getMessage(match, range, runningVersion, expectedVersion));
	}

	private boolean matches(Range range, int expectedVersion, int runningVersion) {
		switch(range){
		case EQUALS:
			return runningVersion == expectedVersion;
		case EQUALS_OR_LOWER:
			return runningVersion <= expectedVersion;
		case EQUALS_OR_HIGHER:
			return runningVersion >= expectedVersion;
		default:
			return false;
		
		}
	}

	private String getMessage(boolean matches, Range range, int runningVersion,
			int expectedVersion) {
		if(matches){
			return "Wicket version matches current: " + runningVersion + " " + range + " expected: " + expectedVersion;
		}else {
			return "Wicket version does not match current: " + runningVersion + " " + range + " expected: " +  expectedVersion;
		}
	}
	
	private String retrieveWicketVersion(String implVersion) {
		Package pkg = FrameworkSettings.class.getPackage();
		if (pkg != null)
		{
			implVersion = pkg.getImplementationVersion();
		}
		String wicketVersion = Strings.isEmpty(implVersion) ? "0" : implVersion;
		return wicketVersion;
	}

}
