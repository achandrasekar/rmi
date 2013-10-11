import rmi.Remote;


public class Fibonacci implements FibonacciInterface, Remote{

	/* (non-Javadoc)
	 * @see WebServiceInterface#calFibonacci(int)
	 */
	
	// intentionally use the slowest algorithm to test speed tolerance
	@Override
	public int calFibonacci(int a) {
		if(a == 0)
			return 0;
		if(a == 1)
			return 1;
		else
			return calFibonacci(a - 1) + calFibonacci(a - 2);
	}

}
