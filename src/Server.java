import network.*;

public class Server {
	
	public static void main(String[] args) throws Exception {
		new ReceiveMessage(Integer.parseInt(args[0]));
	}
	
	boolean contain(String str1, String str2) {
		return str1.contains(str2);
	}
}