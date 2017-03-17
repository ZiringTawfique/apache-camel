package net.gcicom.cdr.processor.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.springframework.util.ObjectUtils;

/**
 * Common utility class to handle GCI specific date time conversion 
 *
 */
public abstract class DateTimeUtil {

	
	/** Returns The values are numbered following the ISO-8601 standard, 
	 * from 1 (Monday) to 7 (Sunday). See java.time.temporal.WeekFields.dayOfWeek() for localized week-numbering.
	 * @param date
	 * @return
	 */
	public static int getWeekDayFlag(LocalDateTime date) {
		
		if (ObjectUtils.isEmpty(date)) {
			
			throw new IllegalArgumentException("Input date time can not be null");
		}
		
		return date.getDayOfWeek().getValue();
		
	}
	
	/** converts hh:mm:ss duration to seconds
	 * @param duration
	 * @return
	 */
	public static int getDurationInSeconds(String duration) {
		
		if (ObjectUtils.isEmpty(duration)) {
			
			throw new IllegalArgumentException("Input date time can not be null");
			
		} 
		
		try {
			
			return LocalTime.parse(duration).toSecondOfDay();
			
		} catch (DateTimeParseException e) {
			
			throw new IllegalArgumentException("Input duration must be in java.time.format.DateTimeFormatter.ISO_LOCAL_TIME format", e);
		}
		
		
		
	
	}
	
}
