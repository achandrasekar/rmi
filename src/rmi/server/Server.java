/**
 * 
 *
 */
package rmi.server;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rmi.Remote440Exception;
import rmi.RemoteRef;
import rmi.registry.Registry;
import rmi.registry.RegistryInterface;
import rmi.registry.RegistryStub;


public class Server implements Runnable{
	
	String name;			// server name registered in registry
	Object serveObj;		// real object to do the method clients ask
	Object serveStubObj;
	int port;				// port number this server is listening to
	String ip;				// local ip address
	ExecutorService executor;	// thread manager.
	URL url;					// file url location
	ServerSocket serSock;
	
	
	// constructor
	public Server(String str, Object obj) {
		this.name = str;
		this.serveObj = obj;
		executor = Executors.newCachedThreadPool();
		this.port = -1;
		serSock = genSocket(this.port);
		
		try {
			// use local ip address
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public Server(String str, Object obj, int portNum) {
		this.name = str;
		this.serveObj = obj;
		executor = Executors.newCachedThreadPool();
		this.port = portNum;
		serSock = genSocket(this.port);
		
		try {
			// use local ip address
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private boolean init(){

		

		// use obj's class file location as url location
		url = getClassFileUrl(this.serveObj);
		
		this.serveStubObj = getStubObj(serveObj, ip, port);
		
		return true;
	}
	
	private ServerSocket genSocket(int portNum){
		// if port is not specified, use an automatically assigned port number
		if(portNum == -1){
			for(int i = 0; i < 10; i++){			// try binding port for 10 times.
				try {
					serSock = new ServerSocket(0);
					port = serSock.getLocalPort();
					break;
				} catch (IOException e) {
					e.printStackTrace();
					if(i < 9)
						continue;
					else
					{
						System.err.println("cannot establish ServerSocket");
						return null;
					}
				}
			}
		}
		else{					// port is specified
			try{
				serSock = new ServerSocket(portNum);
				this.port = serSock.getLocalPort();
			} catch (IOException e){
				e.printStackTrace();
				System.err.println("port"+port+"is not available");
				return null;
			}
		}
		return serSock;
	}
	
	public static Object getStubObj(Object obj, String ipAddr, int portNum){
		Object stubObj = null;
		String className = obj.getClass().getCanonicalName();
		System.out.println("obj's original name is:"+className);
		String stubClassName = className+"Stub";
		RemoteRef ref = new RemoteRef(ipAddr, portNum);
		try {
			stubObj = Class.forName(stubClassName).getDeclaredConstructor(obj.getClass(), RemoteRef.class).newInstance(obj, ref);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stubObj;
	}


/*	// default version. registry's IP default to local ip
	public boolean registServer(){
		
		this.init();

		try {
			// get local ip address
			ip = InetAddress.getLocalHost().getHostAddress();
			if(registServer(ip)){
				ServiceTable.insert(serveObj.getClass().getCanonicalName(), port);
				return true;
			}
			return false;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			// cannot get local ip, return false
			return false;
		}
		
	}*/
	

	// registry's IP is specified in ip
	public boolean registServer(String ipAddr, int port){
		this.init();
		
		ip = ipAddr;
		Registry.port = port;
		RegistryInterface reg  = new RegistryStub(new RemoteRef(ip, port));
		try {
			// invoke the remote method in registry to register this service
			if(reg.register(name, this.serveStubObj, url)){
				ServiceTable.insert(serveObj.getClass().getCanonicalName(), this.port);
				return true;
			}
			return false;
		} catch (Remote440Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		
		System.out.println("server ip:"+ip+"  server port:"+port);
		
		
		
		while(true){
			Socket sock;
			
			// build socket connection. If fail, ignore it and wait for another one
			try {
				sock = this.serSock.accept();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("socket connection error");
				continue;
			}
			Transaction tran = new Transaction(serveObj, sock, ip, port);
			System.out.println("transaction has been constructed");
			executor.execute(tran); 			// create a new thread to do this transaction
		}
		
	}
	
	private URL getClassFileUrl(Object obj){
		try {
			url = (new File(obj.getClass().getCanonicalName())).toURI().toURL();
			return url;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.exit(-1);
			return null;
		}
	}
	
}
