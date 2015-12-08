package com.giffing.wicket.spring.boot.context.extensions.types;

import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Duration;

import com.giffing.wicket.spring.boot.context.exceptions.WicketSpringBootException;

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
			return Duration.days(time);
		case HOURS:
			return Duration.hours(time);
		case MILLISECONDS:
			return Duration.milliseconds(time);
		case MINUTES:
			return Duration.minutes(time);
		case SECONDS:
			return Duration.seconds(time);
		}
		
		throw new WicketSpringBootException("Could not parse time with duration unit " + time + " " + durationUnit);
	}
	
	
}
