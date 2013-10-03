import java.io.*;

public class Client {
	String str;
	
	public Client() {
		str = new String("Test");
	}
	
	public static void main(String[] args) throws IOException{
		Client cli = new Client();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str2 = br.readLine();
		Stub s = new Stub();
		boolean b = s.contain(cli.str, str2);
		System.out.println(b);
	}
}