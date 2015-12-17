package terminalTimeProvider;

import java.time.DateTimeException;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import dateTimeService.api.DateTimeService;

/**
 * A simple class for experimenting with the OSGi DS framework.
 * <p>
 * This class uses the {@link DateTimeService} to provide commands to the OSGi console
 * <p>
 * The provided commands are:
 * <li>time - returns the current time
 * <li>time timeZoneSpecifier (e.g time GMT) - returns the time in a specific time zone
 * <li>date - returns the current date
 * 
 * @author James Mudd
 */
public class OSGIConsoleComamndProvider implements CommandProvider {

	private DateTimeService ts;

	@Override
	public String getHelp() {
		StringBuilder sb = new StringBuilder();
		sb.append("time - Get the current time (or time in specifed time zone)\n");
		sb.append("\tparameters:\n\t\t(Time Zone Specifer)\n\n");
		sb.append("date - Get the current date\n");
		return sb.toString();
	}

	public void _time(CommandInterpreter ci) {
		checkServices();
		String arg = ci.nextArgument();
		if (arg != null) {
			try {
				System.out.println(ts.getTime(arg));
			} catch (DateTimeException e) {
				System.out.println("Invalid time zone specifier");
			}
		} else {
			System.out.println(ts.getTime());
		}
	}

	public void _date(CommandInterpreter ci) {
		checkServices();
		System.out.println(ts.getDate());
	}

	public void setDateTimeService(DateTimeService ts) {
		System.out.println("Set Date Time Service called");
		this.ts = ts;
	}

	public void unsetDateTimeService(DateTimeService ts) {
		System.out.println("Unset Date Time Service called");
		if (ts == this.ts) {
			this.ts = null;
		}
	}
	
	private void checkServices() {
		if (ts == null) {
			throw new RuntimeException("Date Time service is not avaliable!");
		}
	}
	
	public void activate() {
		// Called after all REQUIRED services are bound. You can use services here
		// See the difference between static and dynamic here!
		System.out.println("Activate called");
	}
	
	public void deActivate() {
		// Called when a REQUIRED service is becoming unavailable you still have the service here so you can cleanup / teardown
		// See the difference between 1..1 and 0..1 cardinality here!
		System.out.println("Deactivate called");
	}

}
