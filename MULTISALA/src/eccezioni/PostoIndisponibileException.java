package eccezioni;

public class PostoIndisponibileException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PostoIndisponibileException() {
		super("Non è possibile prenotare o acquistare il posto!");
	}
	
	public PostoIndisponibileException(String msg) {
		super(msg);
	}
}
