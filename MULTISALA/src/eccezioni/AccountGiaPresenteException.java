package eccezioni;

public class AccountGiaPresenteException extends Exception {
	private static final long serialVersionUID = 1L;

	public AccountGiaPresenteException() {
		super("Esiste gi� un account con lo stesso username");
	}
	
	public AccountGiaPresenteException(String msg) {
		super(msg);
	}
}
