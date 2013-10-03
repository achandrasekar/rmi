public class Remote440Exception extends Exception {
	String errorMsg;
	
	public Remote440Exception(String s) {
		this.errorMsg = s;
	}
}