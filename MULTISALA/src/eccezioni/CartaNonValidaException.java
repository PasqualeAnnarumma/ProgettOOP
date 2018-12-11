package eccezioni;

public class CartaNonValidaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CartaNonValidaException() {
		super("La carta di credito non è valida (almeno 16 cifre)");
	}
	
	public CartaNonValidaException(String msg) {
		super(msg);
	}
}
