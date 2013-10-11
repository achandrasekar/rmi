/**
 * 
 *
 */
package rmi;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

import rmi.invocation.Parameter;
import rmi.network.Message;


public class Remote440Stub implements Remote440{

	/*  */
	private static final long serialVersionUID = 2L;
	RemoteRef remoteRef;
	
	public Remote440Stub(RemoteRef r){
		remoteRef = r;
	}
	
	/* (non-Javadoc)
	 * @see rmi.Remote440#invoke(rmi.Remote440, java.lang.reflect.Method, java.lang.Object[], long)
	 */
	@Override
	public Object invoke(Method method, 
						Parameter[] params) 
		throws Exception {
		
		// Create socket and streams related with it
		Socket sock = new Socket(remoteRef.getIp(), remoteRef.getPort());
		ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
		
		if(params == null){
			// if no parameter, send 0 parameters.
			Message.sendRemoteInvocation(oos, method.getName(), 0, null);
		}else{
			// get method related information
			int paramNum = params.length;
			// send method invocation request to remote server
			Message.sendRemoteInvocation(oos, method.getName(), paramNum, params);
			System.out.println("invocation message has been sent");
		}
		
		// receive return value
		Parameter retParam = Message.recParam(ois);
		
		return retParam.getParamObj();
	}

}
