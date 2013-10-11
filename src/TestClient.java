import rmi.Naming;
import rmi.Remote440Exception;
import java.io.*;

public class TestClient {
	
	private static String fiboService;
	private static String getFiboService;
	private static int port;
	
	
	public static void main(String[] args) {
		//testFibonacci(10);
		try {
			System.out.println("Please enter the IP address of registry");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String ip = br.readLine();
			setupRegistryURL(ip);
			System.out.println("Please enter the port number of registry");
			port = Integer.parseInt(br.readLine());
			System.out.println("RMI to calculate the nth Fibonacci number");
			System.out.println("============================================");

			while(true) {
				System.out.println("Enter a positive integer n: ");
				int n = Integer.parseInt(br.readLine());
				testPassByReference(n);
			}
		} catch(IOException e) {
			System.out.println("IOException: Please enter only numbers for port number and input");
		}
		
	}
	
	public static void setupRegistryURL(String ip) {
		fiboService = "rmi://" + ip + "/fibonnaci";
		getFiboService = "rmi://" + ip + "/getFibo";
	}
	
	private static void testFibonacci(int i){

		FibonacciInterface fibo = (FibonacciInterface)Naming.lookup(fiboService, port);

		System.out.println("fiboclass"+fibo.getClass());
		try {
			System.out.println("fibonacci is:"+fibo.calFibonacci(i));
		} catch (Remote440Exception e) {
			e.printStackTrace();
		}
	}
	

	private static void testPassByReference(int n){
		GetFiboInterface getfibo = (GetFiboInterface)Naming.lookup(getFiboService, port);
		
		try {
			System.out.println(getfibo.getFibonacci().calFibonacci(n));
		} catch (Remote440Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
