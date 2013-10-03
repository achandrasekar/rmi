import java.io.*;

public class Stub {
	
	boolean contain(String str1, String str2) {
		try {
			Object[] args = new Object[2];
			args[0] = str1;
			args[1] = str2;
			Message m = new Message("contain", args);
			String serializedFile = "marshal.ser";
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(serializedFile));
			out.writeObject(m);
			out.flush();
			out.close();
		} catch(IOException e) {
			System.out.println("cat: Error: " + e);
		}
		return true;
	}
}