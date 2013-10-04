package network;

import java.io.*;
import java.net.*;

public class ReceiveMessage {
	Socket sock;
	ServerSocket serSock;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Message msg;
	
	public ReceiveMessage(int port) throws ConnectException,IOException, ClassNotFoundException{
		serSock = new ServerSocket(port);
		while(true) {
			sock = serSock.accept();
			ois = new ObjectInputStream(sock.getInputStream());
			msg = (Message) ois.readObject();
			System.out.println(msg.getClass());
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject((Object)this.invoke());
			oos.close();
		}
	}
	
	public Object invoke() {
		// msg.args - contains arguments
		// msg.method - contains method name
		// Invoking server method can be done from here
		System.out.println(msg.method);
		for(Object arg : msg.args) {
			System.out.println(arg.getClass());
			System.out.println((String)arg);
		}
		return true;
	}
	
	public String getIp(){
		return sock.getInetAddress().getHostAddress();
	}
	
}
