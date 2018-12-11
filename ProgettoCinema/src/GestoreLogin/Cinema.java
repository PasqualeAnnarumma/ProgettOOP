package GestoreLogin;

import Eccezioni.AccountGiaEsistenteException;
import GestorePrenotazioni.GestorePrenotazioni;
import GestoreProgrammazione.GestoreProgrammazione;
import GestoreSale.GestoreSale;

public class Cinema {
	
	GestoreLogin gestoreLogin;
	GestoreSale gestoreSale;
	GestoreProgrammazione gestoreProgrammazione;
	GestorePrenotazioni gestorePrenotazioni;
	Utente utente;
	
	public Cinema() {
		gestoreLogin = new GestoreLogin();
		gestoreSale = new GestoreSale();
		gestoreProgrammazione = new GestoreProgrammazione();
		gestorePrenotazioni = new GestorePrenotazioni();
	}
	
	public void registraCliente(String usr, String pwd) throws AccountGiaEsistenteException{
		gestoreLogin.aggiungiCliente(usr, pwd);
	}
	
	public void registraAmministratore(String usr, String pwd) throws AccountGiaEsistenteException{
		gestoreLogin.aggiungiAmministratore(usr, pwd);
	}
	
	public void aggiungiSala(int rows, int columns) {
		gestoreSale.aggiungiSala(rows, columns);
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public GestoreLogin getGestoreLogin() {
		return gestoreLogin;
	}
	
	public GestoreSale getGestoreSale() {
		return gestoreSale;
	}
	
	public GestoreProgrammazione getGestoreProgrammazione() {
		return gestoreProgrammazione;
	}
	
	public GestorePrenotazioni getGestorePrenotazioni() {
		return gestorePrenotazioni;
	}
	
	public boolean login(String usr, String pwd) {
		utente = gestoreLogin.login(usr, pwd);
		if (utente != null) return true;
		return false;
	}
}
