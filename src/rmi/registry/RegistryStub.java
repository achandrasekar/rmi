/**
 * 
 *
 */
package rmi.registry;

import java.lang.reflect.Method;
import java.net.URL;

import rmi.Remote440Exception;
import rmi.Remote440Stub;
import rmi.RemoteRef;
import rmi.invocation.Parameter;


public class RegistryStub extends Remote440Stub
					implements RegistryInterface{


	public RegistryStub(RemoteRef r) {
		super(r);
	}

	/*  */
	private static final long serialVersionUID = -2836580911312639435L;

	/* (non-Javadoc)
	 * @see rmi.registry.RegistryInterface#calculate(int)
	 */
	@Override
	public boolean register(String name, 
			Object obj, 
			URL url) 
		throws Remote440Exception
	{
		try {
			Method method = this.getClass().getMethod("register", 
								String.class, Object.class, URL.class);
			
			Object retObj = super.invoke(method, 
										new Parameter[]{new Parameter(String.class, name), 
														new Parameter(Object.class, obj),
														new Parameter(URL.class, url)
														});
			
			
			return (Boolean)retObj; 
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new Remote440Exception("security exception caught");		
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new Remote440Exception("No such Method Exception");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Remote440Exception("");
		}

	}

	/* (non-Javadoc)
	 * @see rmi.registry.RegistryInterface#lookup(java.lang.String)
	 */
	@Override
	public Object lookup(String serviceName) 
			throws Remote440Exception 
	{
		try {
			Method method = this.getClass().getMethod("lookup", 
								String.class);
			
			Tuple retObj = (Tuple)super.invoke(method, 
										new Parameter[]{new Parameter(String.class, serviceName)});
			
			
			if(retObj == null){
				System.err.println("service not fount");
				return null;
			}
			return retObj.getObject(); 
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new Remote440Exception("security exception caught");		
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new Remote440Exception("No such Method Exception");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Remote440Exception("");
		}
	}

}
