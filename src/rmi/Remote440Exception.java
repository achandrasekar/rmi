package rmi;

public class Remote440Exception extends Exception {
	/*  */
	private static final long serialVersionUID = 626977387263782552L;
	String errorMsg;
	
	public Remote440Exception(String s) {
		this.errorMsg = s;
	}
}