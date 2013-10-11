import java.lang.reflect.Method;
import rmi.Remote440Exception;
import rmi.Remote440Stub;
import rmi.RemoteRef;
import rmi.invocation.Parameter;

/**
 * 
 *
 */

/**
 * @author Anbang Zhao
 *
 *
 *
 */
public class FibonacciStub 
	extends Remote440Stub
	implements FibonacciInterface{


	/*  */
	private static final long serialVersionUID = 3387311472487497412L;
	
	public FibonacciStub(Fibonacci ws, RemoteRef r){
		super(r);
	}

	/* (non-Javadoc)
	 * @see WebServiceInterface#calFibonacci(int)
	 */
	@Override
	public int calFibonacci(int a) 
			throws Remote440Exception{
		try {
			Method method = this.getClass().getMethod("calFibonacci", 
													int.class);
			
			Object retObj = super.invoke(method, 
										new Parameter[]{new Parameter(int.class, a)});
			
			System.out.println("super has invoked");
			
			
			return (Integer)retObj; 
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new Remote440Exception("security exception caught");		
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new Remote440Exception("No such Method Exception");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Remote440Exception("");
		}
	}
	
}
