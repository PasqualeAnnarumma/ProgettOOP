package eccezioni;

public class BigliettoNonCancellabileException extends Exception{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public BigliettoNonCancellabileException() {
		super("Non è possibile cancellare un posto acquistato");
	}
	/**
	 * 
	 * @param msg
	 */
	public BigliettoNonCancellabileException(String msg) {
		super(msg);
	}

}
