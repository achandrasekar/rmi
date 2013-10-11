import java.lang.reflect.Method;

import rmi.Remote;
import rmi.Remote440Exception;
import rmi.RemoteRef;
import rmi.invocation.Parameter;
import rmi.Remote440Stub;


public class GetFiboStub extends Remote440Stub 
						implements GetFiboInterface, Remote
	{

	/**
	 * @param r
	 */
	public GetFiboStub(GetFibo fibo, RemoteRef r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	/*  */
	private static final long serialVersionUID = -942207163210151107L;

	/* (non-Javadoc)
	 * @see GetFiboInterface#getFibonacci()
	 */
	@Override
	public FibonacciInterface getFibonacci() throws Remote440Exception {
		try {
			Method method = this.getClass().getMethod("getFibonacci");
			
			Object retObj = super.invoke(method, null);
			
			System.out.println("super has invoked");
			
			return (FibonacciInterface) retObj;
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new Remote440Exception("security exception caught");		
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new Remote440Exception("No such Method Exception");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Remote440Exception("");
		}	}

}
