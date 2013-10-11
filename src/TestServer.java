
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
		
		if(args.length != 2){
			System.err.println("parameter error");
			System.err.println("Example: java TestServer registryIP registryPort");
			return;
		}
		
		String ip = args[0];
		int port = Integer.parseInt(args[1]);
		
		Fibonacci wb = new Fibonacci();
		GetFibo fibo = new GetFibo();
		Naming.bind("fibonnaci", wb, ip, port);
		Naming.bind("getFibo", fibo, ip, port);
	}
	
}
