package remoteserviceserver;

import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.ContainerFactory;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.IContainerFactory;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.remoteservice.IRemoteService;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainerAdapter;
import org.eclipse.ecf.remoteservice.IRemoteServiceReference;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import dateTimeService.api.DateTimeService;

/**
 * The activator class controls the plug-in life cycle
 */
public class Client implements BundleActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "RemoteServiceClient"; //$NON-NLS-1$
	
	public static final String GENERIC_SERVICE_HOST = "r-osgi://localhost:9278";

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Remote Client");

		IContainerFactory containerFactory = ContainerFactory.getDefault();
		try {
			IContainer container = containerFactory.createContainer("ecf.r_osgi.peer");

			// Get remote service container adapter
			IRemoteServiceContainerAdapter containerAdapter = (IRemoteServiceContainerAdapter) container
					.getAdapter(IRemoteServiceContainerAdapter.class);

			ID id = IDFactory.getDefault().createID(container.getConnectNamespace(), GENERIC_SERVICE_HOST);
			
			IRemoteServiceReference[] remoteServiceReference = containerAdapter.getRemoteServiceReferences(id, DateTimeService.class.getName(), null);
			System.out.println(remoteServiceReference);
			
			// Get remote service for reference
			IRemoteService remoteService = containerAdapter.getRemoteService(remoteServiceReference[0]);

			// Get the proxy
			DateTimeService proxy = (DateTimeService) remoteService.getProxy();

			System.out.println(proxy.getTime());
			System.out.println(proxy.getTime());
			System.out.println(proxy.getTime());
			System.out.println(proxy.getTime());
			System.out.println(proxy.getTime());
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
