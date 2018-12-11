package eccezioni;

public class AccountNonPresenteException extends Exception {
	private static final long serialVersionUID = 1L;

	public AccountNonPresenteException() {
		super("L'account non � presente");
	}
	
	public AccountNonPresenteException(String msg) {
		super(msg);
	}
}
