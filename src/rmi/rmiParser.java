/**
 * 
 *
 */
package rmi;

import java.io.IOException;


public class rmiParser {
	String url;
	String ip;
	String name;
	
	public rmiParser(String u) throws IOException{
		url = u;
		if(!parse())
			throw new IOException();
	}
	
	private boolean parse(){
		int ipBegin = url.indexOf("://") + 3;
		int ipEnd = url.indexOf('/', ipBegin);
		if(ipBegin == -1 || ipEnd == -1)
			return false;
		ip = url.substring(ipBegin, ipEnd);
		name = url.substring(ipEnd + 1);
		return true;
	}
	
	public String getName(){
		return name;
	}
	
	public String getIp(){
		return ip;
	}
}
