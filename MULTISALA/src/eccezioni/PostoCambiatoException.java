package eccezioni;

public class PostoCambiatoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PostoCambiatoException() {
		super("Un posto è stato cambiato");
	}
	
	public PostoCambiatoException(String msg) {
		super(msg);
	}
}
