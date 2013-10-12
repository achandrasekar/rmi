import rmi.Naming;
import rmi.Remote440Exception;
import java.io.*;

public class TestClient {
	
	private static String fiboService;
	private static String getFiboService;
	private static int port;
	
	
	public static void main(String[] args) {
		try {
			System.out.println("Please enter the IP address of registry");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String ip = br.readLine();
			setupRegistryURL(ip);
			
			System.out.println("Please enter the port number of registry");
			port = Integer.parseInt(br.readLine());
			
			System.out.println("RMI to calculate the nth Fibonacci number");
			System.out.println("============================================");
			int n;
			boolean ifDoNotExit = true;
			
			while(ifDoNotExit) {
				System.out.println("Choose one of the following option");
				System.out.println("1 - Pass by Value");
				System.out.println("2 - Pass by Reference");
				System.out.println("3 - Exit Client");
				
				switch(Integer.parseInt(br.readLine())) {
				case 1:
					System.out.println("Enter a positive integer n: ");
					n = Integer.parseInt(br.readLine());
					testFibonacci(n);
					break;
				case 2:
					System.out.println("Enter a positive integer n: ");
					n = Integer.parseInt(br.readLine());
					testPassByReference(n);
					break;
				case 3:
					ifDoNotExit = false;
					break;
				default:
					System.out.println("Invalid option");
				}
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

		try {
			System.out.println("fibonacci is:"+fibo.calFibonacci(i));
		} catch (Remote440Exception e) {
			e.printStackTrace();
		}
	}
	

	private static void testPassByReference(int n){
		GetFiboInterface getfibo = (GetFiboInterface)Naming.lookup(getFiboService, port);
		
		try {
			System.out.println("fibonacci is:"+getfibo.getFibonacci().calFibonacci(n));
		} catch (Remote440Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
