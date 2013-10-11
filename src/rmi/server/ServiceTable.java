/**
 * 
 *
 */
package rmi.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anbang Zhao
 *
 *
 *
 */
public class ServiceTable {
	private static Map<String, Integer> sTable = new HashMap<String, Integer>();
	
	public static void insert(String name, int port){
		sTable.put(name, port);
	}
	
	public static int getPort(String name){
		Integer i = sTable.get(name);
		if(i == null)
			return -1;
		return i;
	}
}
