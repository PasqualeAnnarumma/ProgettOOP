package Eccezioni;

import java.io.IOException;

/**
 * PostoNonDisponibileException � un eccezione che viene lanciata quando si tenta di eseguire operazioni su
 * un posto che non � disponibile. Per esempio se il posto � rotto, oppure � gi� stato prenotato o acquistato
 * da qualcun altro.
 * @author MarioELT
 *
 */
public class PostoNonDisponibileException extends IOException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruisce l'eccezione con il messaggio base "Posto non disponibile!"
	 */
	public PostoNonDisponibileException() {
		super("Posto non disponibile!");
	}
	
	/**
	 * Costruisce l'eccezione con il messaggio specificato
	 * @param msg messaggio dell'eccezione
	 */
	public PostoNonDisponibileException(String msg) {
		super(msg);
	}
	
}
