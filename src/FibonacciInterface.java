import rmi.Remote;
import rmi.Remote440Exception;


public interface FibonacciInterface extends Remote
{
	public int calFibonacci(int a)
			throws Remote440Exception;
}
