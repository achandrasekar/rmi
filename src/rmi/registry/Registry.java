/**
 * 
 *
 */
package rmi.registry;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import rmi.Remote440Exception;
import rmi.server.Server;

/**
 * @author Anbang Zhao
 *
 *
 *
 */
public class Registry implements RegistryInterface{

	String ip;
	public static int port = 2500;		// Well known port number for our version of rmi registry.
	String name = "registry";
	RegisterTable rTable;
	
	public Registry(String ipAddr){
		ip = ipAddr;
		rTable = new RegisterTable();
	}
	
	public static void main(String[] args) throws UnknownHostException{
		
		if(args.length != 1){
			System.err.println("parameter error.");
			System.err.println("Example: java rmi.registry.Registry portNum");
			return;
		}
		
		Registry.port = Integer.parseInt(args[0]);
		
		// get ip address. If cannot get local ip address, terminate the program.
		String ip = InetAddress.getLocalHost().getHostAddress();
		Registry registry = new Registry(ip);
		
		Server registryServer = new Server(registry.name, registry, Registry.port);
		new Thread(registryServer).start();
		
	}
	
	/* (non-Javadoc)
	 * @see rmi.registry.RegistryInterface#calculate(int)
	 */
	@Override
	public boolean register(String name, 
							Object obj, 
							URL url) 
	{
		rTable.registerService(name, obj, url);
		System.out.println("currently registered services are:");
		rTable.print();
		return true;
	}

	/* (non-Javadoc)
	 * @see rmi.registry.RegistryInterface#lookup(java.lang.String)
	 */
	@Override
	public Object lookup(String serviceName) throws Remote440Exception {
		System.out.println("servecieName:"+serviceName);
		Tuple t = rTable.findByName(serviceName);
		rTable.print();
		if(t == null)
			System.out.println("service not found");
		
		return t;
	}

}
