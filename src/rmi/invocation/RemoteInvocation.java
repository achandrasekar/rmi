package rmi.invocation;

import java.util.ArrayList;
import java.util.List;



public class RemoteInvocation {
	private String method;			// method name
	private List<Parameter> pList;	// parameter list
	
	public RemoteInvocation(){
		pList = new ArrayList<Parameter>();
	}
	
	public void setMethodName(String name){
		method = name;
	}
	
	// add a parameter into pList based on type and obj
	public void addParam(Parameter p){
		pList.add(p);
	}
	
	// get parameter type array
	public Class<?>[] getParamTypeArray(){
		int paramNum = pList.size();
		Class<?>[] typeArray = new Class<?>[paramNum];
		for(int i = 0; i < paramNum; i++){
			typeArray[i] = pList.get(i).getParamType();
		}
		
		return typeArray;
	}
	
	// get parameter object array
	public Object[] getparamObjArray(){
		int paramNum = pList.size();
		Object[] objArray = new Object[paramNum];
		for(int i = 0; i < objArray.length; i++){
			objArray[i] = pList.get(i).getParamObj();
		}
		
		return objArray;
	}
	
	public String getMethodName(){
		return this.method;
	}
}
