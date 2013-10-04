import java.net.InetAddress;

import network.*;

public class Stub {
	
	boolean contain(String str1, String str2) throws Exception {
		Object[] args = new Object[2];
		args[0] = str1;
		args[1] = str2;
		Message m = new Message("contain", args);
		SendMessage sdm = new SendMessage(InetAddress.getLocalHost().getHostAddress(), 2000);
		return (Boolean)sdm.marshal(m);
	}
}