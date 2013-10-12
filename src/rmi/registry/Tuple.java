/**
 * 
 *
 */
package rmi.registry;

import java.io.Serializable;
import java.net.URL;


public class Tuple implements Serializable{
	
	/*  */
	private static final long serialVersionUID = 4664008042091469299L;
	private String name;
	private Object obj;
	private URL url;
	
	public Tuple(String sName, Object o, URL u){
		this.name = sName;
		this.obj = o;
		this.url = u;
	}
	
	public String getName(){
		return name;
	}
	
	public Object getObject(){
		return obj;
	}
	
	public URL getURL(){
		return url;
	}
	
	public void print(){
		System.out.println("name:"+name);
		System.out.println("Object Type:"+obj.getClass().getSimpleName());
		System.out.println("url: " + url.getPath());
	}
}
