package eccezioni;

public class SalaGiaOccupataException extends Exception{
	private static final long serialVersionUID = 1L;

	public SalaGiaOccupataException() {
		super("Sala già occupata");
	}
	
	public SalaGiaOccupataException(String msg) {
		super(msg);
	}
}
