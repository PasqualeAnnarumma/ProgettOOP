package gestoreLogin;

import java.io.Serializable;
import java.util.ArrayList;

import eccezioni.AccountGiaEsistenteException;
import gestoreLogin.Cliente.Categoria;

/**
 * Il gestore login è il gestore che si occupa di effettuare gli accessi al sistema
 * @author MarioELT
 *
 */
public class GestoreLogin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	ArrayList<Utente> listaUtenti;
	
	/**
	 * Costruisce il gestore
	 */
	public GestoreLogin() {
		listaUtenti = new ArrayList<Utente>();
	}
	
	/**
	 * Aggiunge un nuovo cliente alla listaUtenti del gestore
	 * @param usr username del nuovo cliente
	 * @param pwd password dell'utente
	 * @param dataNascita data di nascita del cliente
	 * @param category categoria del cliente
	 * @throws AccountGiaEsistenteException se l'username è già esistente
	 */
	public void aggiungiCliente(String usr, String pwd, String dataNascita, Categoria category) throws AccountGiaEsistenteException{
		Cliente nuovo = new Cliente(usr, pwd, dataNascita, category);
		for (Utente ut : listaUtenti)
			if (ut.getUsername().equals(usr)) throw new AccountGiaEsistenteException();
		
		listaUtenti.add(nuovo);
	}
	
	/**
	 * Aggiunge un nuovo amministratore alla listaUtenti del gestore
	 * @param usr username del nuovo amministratore
	 * @param pwd password del nuovo amministratore
	 * @throws AccountGiaEsistenteException se l'username è già esistente
	 */
	public void aggiungiAmministratore(String usr, String pwd) throws AccountGiaEsistenteException{
		for (Utente ut : listaUtenti)
			if (ut.getUsername().equals(usr)) throw new AccountGiaEsistenteException("Amministratore già esistente");
		
		listaUtenti.add(new Amministratore(usr, pwd));
	}
	
	/**
	 * Rimuove un utente dalla listaUtenti del gestore
	 * @param user username dell'utente
	 */
	public void remove(Utente user) {
		listaUtenti.remove(user);
	}
	
	/**
	 * Effettua il login di un utente
	 * @param usr username dell'utente
	 * @param pwd password dell'utente
	 * @return l'utente che ha effettuato il login se l'utente esiste e se le credenziali sono corrette, null altrimenti
	 */
	public Utente login(String usr, String pwd) {
		for(Utente user : listaUtenti)
			if (user.getUsername().equals(usr) && user.getPassword().equals(pwd)) return user;
		
		return null;
	}
	
	/**
	 * Restituisce la lsita di tutti i clieni
	 * @return lista di tutti i clienti
	 */
	public ArrayList<Cliente> getListaClienti() {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		for (int i = 0; i < listaUtenti.size(); i++)
			if (listaUtenti.get(i) instanceof Cliente) lista.add((Cliente) listaUtenti.get(i));
		
		return lista;
	}
	
}
