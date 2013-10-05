import network.*;

public class Registry440 {
	static String hostname;
	static int port = 2000; // To do - get it fron user
	static String name;
	static Object object;
	
	static void bind(String n, Object obj) throws Exception{
		name = n;
		object = obj;
		new ReceiveMessage(port);
	}
}