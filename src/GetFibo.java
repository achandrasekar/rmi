import rmi.Remote440Exception;


public class GetFibo implements GetFiboInterface{

	/* (non-Javadoc)
	 * @see GetFiboInterface#getFibonacci()
	 */
	@Override
	public FibonacciInterface getFibonacci() throws Remote440Exception {
		Fibonacci f = new Fibonacci();
		return f;
	}

}
