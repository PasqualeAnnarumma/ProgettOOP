package Eccezioni;

import java.io.IOException;

public class AccountGiaEsistenteException extends IOException {
	
	private static final long serialVersionUID = 1L;

	public AccountGiaEsistenteException() {
		super("Account già esistente!");
	}
	
	public AccountGiaEsistenteException(String msg) {
		super(msg);
	}
	
}
