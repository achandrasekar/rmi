package rmi;

import java.io.Serializable;
import java.lang.reflect.Method;

import rmi.invocation.Parameter;

public interface Remote440 extends Serializable{
	Object invoke( Method method,
				  Parameter[] params)
	throws Exception;
}