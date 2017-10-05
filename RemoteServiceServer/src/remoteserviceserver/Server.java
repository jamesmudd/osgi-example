package remoteserviceserver;

import java.util.Properties;

import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.ContainerFactory;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.IContainerFactory;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainerAdapter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import dateTimeService.api.DateTimeService;

/**
 * The activator class controls the plug-in life cycle
 */
public class Server implements BundleActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "RemoteServiceServer"; //$NON-NLS-1$


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Remote Activator");

		IContainerFactory containerFactory = ContainerFactory.getDefault();
		try {
			IContainer container = containerFactory.createContainer("ecf.r_osgi.peer");

			// Get remote service container adapter
			IRemoteServiceContainerAdapter containerAdapter = (IRemoteServiceContainerAdapter) container
					.getAdapter(IRemoteServiceContainerAdapter.class);

			ServiceReference<?> sr = context.getServiceReference(DateTimeService.class);

			Properties props = new Properties();
			// add OSGi service property indicated export of all interfaces exposed
			// by service (wildcard)
			props.put("service.exported.interfaces", "*");
			// add OSGi service property specifying config
			props.put("service.exported.config", "ecf.r_osgi.peer");
			
			Object service = context.getService(sr);
			containerAdapter.registerRemoteService(new String[] {DateTimeService.class.getName() }, service, props);
			
			System.out.println("Started");
			
		} catch (ContainerCreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
