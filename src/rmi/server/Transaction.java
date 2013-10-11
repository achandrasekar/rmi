/**
 * 
 *
 */
package rmi.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import rmi.Remote;
import rmi.invocation.Parameter;
import rmi.invocation.RemoteInvocation;
import rmi.network.Message;


public class Transaction implements Runnable{
	
	Socket sock;		// socket to get marshalled method invocation and send return object
	Object srcObj;		// the object to invoke method
	String ip;			// local ip
	int port;			// port used for this web service
	
	public Transaction( Object obj, Socket s, String ipStr, int portNum){
		sock = s;
		srcObj = obj;
		ip = ipStr;
		port = portNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// read all the information about this invocation from socket
		//RemoteInvocation rmtInvoc = Message.recRemoteInvocation(sock);
		
		// get the ObjectInputStream and ObjectOutputStream for all the object read and write
		// if exception occurs, terminate this thread.
		ObjectInputStream ois;
		ObjectOutputStream oos;
		try{
			ois = new ObjectInputStream(sock.getInputStream());
			oos = new ObjectOutputStream(sock.getOutputStream());
		} catch (IOException e){
			e.printStackTrace();
			return;
		}
		
		System.out.println("in transaction, before receive invocation request");
		RemoteInvocation rmtInvoc = Message.recRemoteInvocation(ois);
		System.out.println("in transaction, after receive invocation request");
		Class<?> objType = srcObj.getClass();
		Class<?>[] paramTypes = rmtInvoc.getParamTypeArray();
		Object[] paramObjs = rmtInvoc.getparamObjArray();
		String methodName = rmtInvoc.getMethodName();
		

		try {
			// get the Method object based on above information
			System.out.println("method name is:"+methodName);
			for(int i = 0; i < paramTypes.length; i++){
				System.out.println("param type is:"+paramTypes[i].getSimpleName());
			}
			
			// deal with method without parameters
			Method method;
			if(paramTypes.length == 0)
				method = objType.getMethod(methodName);
			else
				method = objType.getMethod(methodName, paramTypes);
			
			
			// invoke the method on the srcObj
			// return value is in retObj	
			
			
			// deals with method that return void
			// if return value is void, we pass the Void 
			// type and a dummy object back
			Class<?> retType = method.getReturnType();
			if(retType.equals(Void.class)){
				Object retObj = new Object();
				System.out.println("return object type is:"+retType.getSimpleName());
				Message.sendParam(oos, new Parameter(retType, retObj));
			}
			
			Object retObj = method.invoke(srcObj, paramObjs);
			
			// send return object and its type to remote caller
			if(retObj != null){
				// if return value should be returned by reference
				// create a stub object and pass it to client
				if(isByReference(retObj)){
					System.out.println("pass by reference");
					int portNum = ServiceTable.getPort(retObj.getClass().getCanonicalName());
					retObj = Server.getStubObj(retObj, ip, portNum);
				}
				System.out.println("return object type is:"+retObj.getClass().getSimpleName());
				Message.sendParam(oos, new Parameter(retObj.getClass(), retObj));
			}else{
				System.out.println("return object type is null");
				Message.sendParam(oos, new Parameter(null, retObj));
			}
			
		} catch (SecurityException e) {
			e.printStackTrace();
			return;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return;
		}
	}
	
	// if obj has implemented Remote interface
	// then we pass by reference
	private boolean isByReference(Object obj){
		System.out.println(obj.getClass().getCanonicalName());
		Class<?>[] interfaceArray = obj.getClass().getInterfaces();
		for(int i = 0; i < interfaceArray.length; i++){
			System.out.println("interface:"+interfaceArray[i].getCanonicalName());
			if(interfaceArray[i].equals(Remote.class))
			{
				System.out.println("interface:"+interfaceArray[i].getCanonicalName());
				return true;
			}
		}
		return false;
	}
	

}
