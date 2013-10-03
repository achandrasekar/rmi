public class Message {
	String method;
	Object[] args;
	
	public Message(String method, Object[] args) {
		this.method = method;
		this.args = args;
	}

}