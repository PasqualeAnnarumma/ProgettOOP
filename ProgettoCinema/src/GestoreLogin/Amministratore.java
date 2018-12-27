package GestoreLogin;

/**
 * L'amministratore � uno speciale utente che pu� aggiungere o rimuovere film e programmazioni,
 * rendere disponibili o indisponibili i posti nelle sale e attivare o disattivare politiche di sconto
 * @author MarioELT
 *
 */
public class Amministratore extends Utente {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Costruisce l'amministratore
	 * @param usr username dell'amministratore
	 * @param pwd password dell'amministratore
	 */
	public Amministratore(String usr, String pwd) {
		super(usr, pwd);
	}	
}
