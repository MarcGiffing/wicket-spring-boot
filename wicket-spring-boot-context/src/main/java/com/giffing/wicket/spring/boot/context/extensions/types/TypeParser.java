package com.giffing.wicket.spring.boot.context.extensions.types;

import org.apache.wicket.util.lang.Bytes;

import com.giffing.wicket.spring.boot.context.exceptions.WicketSpringBootException;
import java.time.Duration;

public class TypeParser {

	public static Bytes parse(Long size, SessionUnit sessionUnit){
		switch(sessionUnit){
		case BYTES:
			return Bytes.bytes(size);
		case KILOBYTES:
			return Bytes.kilobytes(size);
		case MEGABYTES:
			return Bytes.megabytes(size);
		case TERABYTES:
			return Bytes.terabytes(size);
		}
		throw new WicketSpringBootException("Could not parse size with session unit " + size + " " + sessionUnit);
	}
	
	public static Duration parse(Long time, DurationUnit durationUnit){
		switch(durationUnit){
		case DAYS:
			return Duration.ofDays(time);
		case HOURS:
			return Duration.ofHours(time);
		case MILLISECONDS:
			return Duration.ofMillis(time);
		case MINUTES:
			return Duration.ofMinutes(time);
		case SECONDS:
			return Duration.ofSeconds(time);
		}
		
		throw new WicketSpringBootException("Could not parse time with duration unit " + time + " " + durationUnit);
	}
	
	
}
