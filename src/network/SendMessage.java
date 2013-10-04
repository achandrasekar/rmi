package network;

import java.io.*;
import java.net.Socket;

public class SendMessage{
	private String ip;
	private int port;
	Socket sock;
	
	public SendMessage(String ip, int port){
		this.ip = ip;
		this.port = port;
	}

	public Object marshal(Message m){
		ObjectOutputStream oos;
		ObjectInputStream ois;
		Object obj;
		try {
			sock = new Socket(this.ip, this.port);
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(m);
			ois = new ObjectInputStream(sock.getInputStream());
			obj = ois.readObject();
			ois.close();
			sock.close();
			return obj;
		} catch (Exception e) {
			System.out.println("Error . . .");
			return false;
		}
	}
	
}
