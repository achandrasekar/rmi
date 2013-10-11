/**
 * 
 *
 */
package rmi.registry;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RegisterTable {
	
	
	private List<Tuple> tupleList;
	
	public RegisterTable(){
		tupleList = new ArrayList<Tuple>();
	}
	
	public void registerService(String name, 
								Object obj, 
								URL url)
	{
		tupleList.add(new Tuple(name, obj, url));
	}
	
	public void print(){
		for(int i = 0; i < tupleList.size(); i++){
			tupleList.get(i).print();
		}
	}
	
	public Tuple findByName(String name){
		for(Tuple t : tupleList){
			if(t.getName().equals(name))
				return t;
		}
		return null;
	}
}
