package rmi.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import rmi.invocation.*;

public class Message {
	
	// send remote invocation request
	public static boolean sendRemoteInvocation(ObjectOutputStream oos, 
											    String method,
											    Integer paramNum,
											    Parameter[] params)
	{
		try {
			oos.writeObject(method);
			oos.writeObject(paramNum);
			for(int i = 0; i < paramNum; i++){
				System.out.println("send type is:"+params[i].getParamObj().getClass().getSimpleName());
				oos.writeObject(params[i].getParamType());
				oos.writeObject(params[i].getParamObj());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	// receive a method invocation from sock
	// retrun a RemoteInvocation type
	// return null if fail.
	public static RemoteInvocation recRemoteInvocation(ObjectInputStream ois){
		RemoteInvocation invoc = new RemoteInvocation();
		
		// create object input stream by sock
		
		try {
			// receive and set method name
			String method = (String)ois.readObject();
			invoc.setMethodName(method);
			System.out.println("method name is "+method+"  in Message");
			
			// get parameter number
			Integer paramNum = (Integer)ois.readObject();
			System.out.println("parameter number is "+paramNum);
			
			// get each parameter
			for(int i = 0; i < paramNum; i++){
				Parameter newParam = recParam(ois);	// construct a parameter from type and object received from sock
				
				invoc.addParam(newParam);				// add parameter to the parameter list
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		return invoc;		
	}
	
	// send an object's type and then itself
	// return true if success, false otherwise.
	public static boolean sendParam(ObjectOutputStream oos, Parameter param){
		try {
			System.out.println("type is"+param.getParamType());
			oos.writeObject(param.getParamType());	// send type
			oos.writeObject(param.getParamObj());	// send object
		} catch (IOException e) {
			e.printStackTrace();
			return false;		// exception occurred. Return false.
		}
		
		return true;
		
	}
	
	// receive a <type, object> pair from sock
	public static Parameter recParam(ObjectInputStream ois) throws IOException, ClassNotFoundException{
		// first read type
		Class<?> type = (Class<?>)ois.readObject();
		if(type != null)
			System.out.println("parameter type is "+type.getSimpleName());
		else
			System.out.println("parameter type is null");
		// then read object
		Object obj = ois.readObject();
		Parameter received = new Parameter(type, obj);

		return received;
	}
}
