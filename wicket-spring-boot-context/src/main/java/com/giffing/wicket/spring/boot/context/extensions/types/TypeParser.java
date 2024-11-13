package com.giffing.wicket.spring.boot.context.extensions.types;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.wicket.util.lang.Bytes;

import com.giffing.wicket.spring.boot.context.exceptions.WicketSpringBootException;
import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TypeParser {

	public static Bytes parse(Long size, SessionUnit sessionUnit){
        return switch (sessionUnit) {
            case BYTES -> Bytes.bytes(size);
            case KILOBYTES -> Bytes.kilobytes(size);
            case MEGABYTES -> Bytes.megabytes(size);
            case TERABYTES -> Bytes.terabytes(size);
        };
    }
	
	public static Duration parse(Long time, DurationUnit durationUnit){
        return switch (durationUnit) {
            case DAYS -> Duration.ofDays(time);
            case HOURS -> Duration.ofHours(time);
            case MILLISECONDS -> Duration.ofMillis(time);
            case MINUTES -> Duration.ofMinutes(time);
            case SECONDS -> Duration.ofSeconds(time);
        };

    }
	
	
}
