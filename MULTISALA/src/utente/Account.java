package utente;
import java.io.Serializable;

/** 
 * Un account contiene i dati di accesso di una persona registrata.
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static enum Livello{GESTORE, CLIENTE};
	private String username;
	private String password;
	private Livello autorizzazione;
	
	/**
	 * Crea una nuova istanza di Account, dati un username, una password e un livello di autorizzazione.
	 * @param username
	 * @param password
	 * @param livelloAutorizzazione
	 */
	public Account(String username, String password, Livello livelloAutorizzazione) {
		this.username = username;
		this.password = password;
		this.autorizzazione = livelloAutorizzazione;
	}
	
	/**
	 * Restituisce l'username dell'Account.
	 * @return l'username.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Restituisce la password dell'account.
	 * @return la password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Restituisce il livello di autorizzazione dell'account
	 * @return il livello di autorizzazione.
	 */
	public Livello getLivelloAutorizzazione() {
		return autorizzazione;
	}
	
	/**
	 * Aggiorna l'username dell'account
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Aggiorna la password dell'account.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Metodi di Object sovrascritti. 
	 */
	public String toString() {
		return getClass().getSimpleName() + "[username=" + username + ", password=" + password + ", autorizzazione=" + autorizzazione + "]"; // non molto sicuro :)
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		if(getClass() != otherObject.getClass())
			return false;
		Account other = (Account) otherObject;
		return username.equals(other.username) && password.equals(other.password) && autorizzazione == other.autorizzazione;
	}

}
