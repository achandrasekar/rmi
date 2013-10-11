
import java.io.Serializable;

import rmi.Naming;
import rmi.Remote;

/**
 * 
 *
 */

public class TestServer implements Remote, Serializable{
	
	
	/*  */
	private static final long serialVersionUID = -591654844094763395L;

	public static void main(String[] args){
		Fibonacci wb = new Fibonacci();
		GetFibo fibo = new GetFibo();
		Naming.bind("fibonnaci", wb);
		Naming.bind("getFibo", fibo);
	}
	
}
