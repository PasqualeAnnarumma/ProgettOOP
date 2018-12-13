package GestoreLogin;

import Eccezioni.AccountGiaEsistenteException;
import Eccezioni.PostoNonDisponibileException;
import GestorePrenotazioni.GestorePrenotazioni;
import GestorePrenotazioni.Prenotazione;
import GestoreProgrammazione.GestoreProgrammazione;
import GestoreSale.GestoreSale;
import GestoreSale.Posto;

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
	
	public void aggiungiPrenotazione(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		gestorePrenotazioni.aggiungiPrenotazione(cliente, prenotazione);
	}
	
	public void rimuoviPrenotazione(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		gestorePrenotazioni.rimuoviPrenotazione(prenotazione, cliente);
	}
	
	public void acquistaPosto(Cliente cliente, Prenotazione prenotazione, Posto p) throws PostoNonDisponibileException{
		p = prenotazione.getPosto();
		System.out.println(p.isOccupato());
		if (!p.isOccupato())
		{
			gestorePrenotazioni.aggiungiPrenotazione(cliente, prenotazione);
			p.acquistaPosto();
		}
		else if (gestorePrenotazioni.controlloProprietà(cliente, p) != null)
			gestorePrenotazioni.acquistaPosto(p);
		
		gestorePrenotazioni.acquista(cliente, prenotazione);
	}
	
	public Posto controlloProprietà(Cliente cliente, Posto posto) {
		return gestorePrenotazioni.controlloProprietà(cliente, posto);
	}
}
