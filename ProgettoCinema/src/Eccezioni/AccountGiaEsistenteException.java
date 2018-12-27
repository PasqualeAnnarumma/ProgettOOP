package Eccezioni;

import java.io.IOException;

/**
 * AccountGiaEsistenteException � un eccezione che viene lanciata quando si tenta di registrare un account
 * con un username gi� esistente
 * @author MarioELT
 *
 */
public class AccountGiaEsistenteException extends IOException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruisce l'eccezione con il messaggio base "Account gi� esistente"
	 */
	public AccountGiaEsistenteException() {
		super("Account gi� esistente!");
	}
	
	/**
	 * Costruisce l'eccezione con il messaggio specificato
	 * @param msg messaggio dell'eccezione
	 */
	public AccountGiaEsistenteException(String msg) {
		super(msg);
	}
	
}
