package Eccezioni;

import java.io.IOException;

public class PostoNonDisponibileException extends IOException {
	
	private static final long serialVersionUID = 1L;

	public PostoNonDisponibileException() {
		super();
	}
	
	public PostoNonDisponibileException(String msg) {
		super(msg);
	}
	
}
