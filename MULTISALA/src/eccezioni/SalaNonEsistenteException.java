package eccezioni;

public class SalaNonEsistenteException extends Exception{
	private static final long serialVersionUID = 1L;

	public SalaNonEsistenteException() {
		super("La sala non esiste");
	}
	
	public SalaNonEsistenteException(String msg) {
		super(msg);
	}
	
}
