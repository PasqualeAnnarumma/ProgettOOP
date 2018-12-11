package eccezioni;

public class CampiVuotiException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CampiVuotiException() {
		super("Bisogna inserire tutti i campi");
	}
	
	public CampiVuotiException(String msg) {
		super(msg);
	}
}
