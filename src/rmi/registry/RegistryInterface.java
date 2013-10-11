/**
 * 
 *
 */
package rmi.registry;

import java.net.URL;

import rmi.Remote440Exception;

/**
 * @author Anbang Zhao
 *
 *
 *
 */
public interface RegistryInterface{
	public boolean register(String name, 
							Object obj, 
							URL url) 
	throws Remote440Exception;
	
	public Object lookup(String serviceName)
		throws Remote440Exception;
}
