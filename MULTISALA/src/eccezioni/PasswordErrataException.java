package eccezioni;

public class PasswordErrataException extends Exception {
	private static final long serialVersionUID = 1L;

	public PasswordErrataException() {
		super("Password errata");
	}
	
	public PasswordErrataException(String msg) {
		super(msg);
	}
}
