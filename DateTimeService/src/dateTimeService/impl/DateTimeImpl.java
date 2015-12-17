package dateTimeService.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import dateTimeService.api.DateTimeService;

/**
 * Default implementation of the {@link DateTimeService}
 * <p>
 * Uses the Java 8 Date time API
 * 
 * @author James Mudd
 */
public class DateTimeImpl implements DateTimeService {

	@Override
	public String getTime() {
		return LocalTime.now().toString();
	}

	@Override
	public String getDate() {
		return LocalDate.now().toString();
	}

	@Override
	public String getTime(String TimeZoneSpecifier) {
		return LocalTime.now(ZoneId.of(TimeZoneSpecifier)).toString();
	}

}
