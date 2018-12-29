package GestoreLogin;

import java.io.Serializable;

/**
 * L'utente rappresenta l'utente generico del sistema.
 * Un utente può essere un cliente o un amministratore
 * @author MarioELT
 *
 */
public class Utente implements Cloneable, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	/**
	 * Costruisce l'utente
	 * @param usr username dell'utente
	 * @param pwd password dell'utente
	 */
	public Utente(String usr, String pwd) {
		username = usr;
		password = pwd;
	}
	
	/**
	 * Restituisce l'username dell'utente
	 * @return username dell'utente
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Restituisce la password dell'utente
	 * @return password dell'utente
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Cambia la password all'utente
	 * @param pwd nuova password
	 */
	public void setPassword(String pwd) {
		password = pwd;
	}
	
	/**
	 * Effettua una clonazione dell'utente
	 * @return l'utente clonato
	 */
	public Utente clone() {
		try {
			return (Utente) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * controlla se due utenti sono uguali
	 * @return true se sono uguali, false altrimenti
	 */
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		Utente ut = (Utente) obj;
		return ut.getUsername().equals(username) && ut.getPassword().equals(password);
	}

}
