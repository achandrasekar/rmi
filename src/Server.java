public class Server implements Remote440 {
	
	public static void main(String[] args) throws Exception {
		Server s = new Server();
		Registry440.bind("Server", s);
	}
	
	boolean contain(String str1, String str2) {
		return str1.contains(str2);
	}
}