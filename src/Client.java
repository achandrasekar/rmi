import java.io.*;

public class Client {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		Stub s = new Stub();
		boolean b = s.contain(str1, str2);
		System.out.println(b);
	}
}