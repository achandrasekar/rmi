import rmi.Naming;
import rmi.Remote440Exception;


public class TestClient {
	
	private static final String fiboService = "rmi://128.237.139.242/fibonnaci";
	private static final String getFiboService = "rmi://128.237.139.242/getFibo";
	
	
	public static void main(String[] args){
		//testFibonacci(10);
		testPassByReference();
	}
	
	private static void testFibonacci(int i){
		FibonacciInterface fibo = (FibonacciInterface)Naming.lookup(fiboService, 2600);
		System.out.println("fiboclass"+fibo.getClass());
		try {
			System.out.println("fibonacci is:"+fibo.calFibonacci(i));
		} catch (Remote440Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testPassByReference(){
		GetFiboInterface getfibo = (GetFiboInterface)Naming.lookup(getFiboService, 2600);
		
		try {
			System.out.println(getfibo.getFibonacci().calFibonacci(3));
		} catch (Remote440Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
