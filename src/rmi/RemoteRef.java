/**
 * 
 *
 */
package rmi;

import java.io.Serializable;


public class RemoteRef implements Serializable{
	/*  */
	private static final long serialVersionUID = 1L;
	private String ip;
	private int port;
	
	public RemoteRef(String ipAddr, int portNum){
		ip = ipAddr;
		port = portNum;
	}
	
	public String getIp(){
		return ip;
	}
	
	public int getPort(){
		return port;
	}
}
