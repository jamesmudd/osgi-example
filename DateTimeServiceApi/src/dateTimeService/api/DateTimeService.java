package dateTimeService.api;

/**
 * Simple API defining some date and time functions for use with OSGi
 * 
 * @author James Mudd
 */
public interface DateTimeService {
	
	public String getTime();
	
	public String getDate();
	
	public String getTime(String TimeZoneSpecifier);
	
}
