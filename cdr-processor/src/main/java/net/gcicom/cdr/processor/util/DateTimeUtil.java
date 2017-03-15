package net.gcicom.cdr.processor.util;

import java.time.LocalDateTime;

/**
 * Commong utility class to handle GCI specific date time conversion 
 *
 */
public abstract class DateTimeUtil {

	
	/** Returns The values are numbered following the ISO-8601 standard, 
	 * from 1 (Monday) to 7 (Sunday). See java.time.temporal.WeekFields.dayOfWeek() for localized week-numbering.
	 * @param date
	 * @return
	 */
	public static int getWeekDayFlag(LocalDateTime date) {
		
		return date.getDayOfWeek().getValue();
		
	} 
	
}
