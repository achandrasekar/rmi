/**
 * 
 *
 */
package rmi.invocation;


public class Parameter {
	Class<?> paramType;		// parameter type
	Object paramObj;		// parameter value
	
	public Parameter(Class<?> type, Object obj){
		paramType = type;
		paramObj = obj;
	}
	
	public Class<?> getParamType(){
		return paramType;
	}
	
	public Object getParamObj(){
		return paramObj;
	}
}
