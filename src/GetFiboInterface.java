import rmi.Remote440Exception;


public interface GetFiboInterface {
	FibonacciInterface getFibonacci()
		throws Remote440Exception;
}
