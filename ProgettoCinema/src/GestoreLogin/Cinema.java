package GestoreLogin;

import java.util.ArrayList;
import java.util.Calendar;
import Eccezioni.AccountGiaEsistenteException;
import Eccezioni.PostoNonDisponibileException;
import GestorePrenotazioni.GestorePrenotazioni;
import GestorePrenotazioni.Prenotazione;
import GestorePrenotazioni.PrenotazioniCliente;
import GestoreProgrammazione.Criterio;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.GestoreProgrammazione;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.GestoreSale;
import GestoreSale.Posto;
import GestoreSale.Sala;
import GestoreSconti.GestoreSconti;

public class Cinema {
	
	GestoreLogin gestoreLogin;
	GestoreSale gestoreSale;
	GestoreProgrammazione gestoreProgrammazione;
	GestorePrenotazioni gestorePrenotazioni;
	GestoreSconti gestoreSconti;
	Utente utente;
	
	public Cinema() {
		gestoreLogin = new GestoreLogin();
		gestoreSale = new GestoreSale();
		gestoreProgrammazione = new GestoreProgrammazione();
		gestorePrenotazioni = new GestorePrenotazioni();
		gestoreSconti = new GestoreSconti();
	}
	
	public void registraCliente(String usr, String pwd, int eta) throws AccountGiaEsistenteException{
		gestoreLogin.aggiungiCliente(usr, pwd, eta);
	}
	
	public void registraAmministratore(String usr, String pwd) throws AccountGiaEsistenteException{
		gestoreLogin.aggiungiAmministratore(usr, pwd);
	}
	
	public int getNumeroSale() {
		return gestoreSale.size();
	}
	
	public ArrayList<Film> getListaFilm() {
		/*ArrayList<Film> listaFilm = new ArrayList<Film>();
		ArrayList<Spettacolo> listaSpettacoli = getListaSpettacoli();
		for (Spettacolo spettacolo : listaSpettacoli)
			listaFilm.add(spettacolo.getFilm());
		return listaFilm;*/
		return gestoreProgrammazione.getListaFilm();
	}
	
	public float getIncasso(ArrayList<Spettacolo> spettacoli) {
		float incasso = 0;
		for (Spettacolo s : spettacoli)
			incasso += getIncasso(s.getFilm());
		incasso *= 1000;
		Math.floor(incasso);
		incasso /= 1000;
		return incasso;
	}
	
	public ArrayList<Prenotazione> getListaPrenotazioni(Film film) {
		ArrayList<Prenotazione> lista = new ArrayList<Prenotazione>();
		ArrayList<PrenotazioniCliente> prenotazioni = getListaPrenotazioni();
		for (int i = 0; i < prenotazioni.size(); i++)
		{
			PrenotazioniCliente listaPrenotazione = prenotazioni.get(i);
			for (int j = 0; j < listaPrenotazione.size(); j++)
			{
				Prenotazione prenotazione = listaPrenotazione.getPrenotazione(j);
				Film filmconfronto = prenotazione.getSpettacolo().getFilm();
				if (film.equals(filmconfronto))
					lista.add(prenotazione);
			}
		}
		return lista;
	}
	
	public float getIncasso(Film film) {
		float incasso = 0;
		/*ArrayList<Prenotazione> prenotazioni = getListaPrenotazioni(film);
		for (Prenotazione p : prenotazioni)
			incasso += p.getPrezzoPagato();*/
		
		ArrayList<PrenotazioniCliente> lista = getListaPrenotazioni();
		for (int i = 0; i < lista.size(); i++)
		{
			PrenotazioniCliente prenotazioni = lista.get(i);
			for (int j = 0; j < prenotazioni.size(); j++)
			{
				Prenotazione pren = prenotazioni.getPrenotazione(j);
				Spettacolo spettacolo = pren.getSpettacolo();
				if (spettacolo.getFilm().equals(film) && pren.isPagato())
					incasso += pren.getPrezzoPagato();
			}
		}
		incasso *= 1000;
		Math.floor(incasso);
		incasso /= 1000;
		return incasso;
	}
	
	public ArrayList<Spettacolo> getListaSpettacoli(Criterio c1, Criterio c2) {
		ArrayList<Spettacolo> listaSpettacoli = new ArrayList<Spettacolo>();
		ArrayList<Spettacolo> oldList = getListaSpettacoli();
		for (Spettacolo s : oldList)
			if (c1.criterio(s) && c2.criterio(s) && isFruibile(s))
				listaSpettacoli.add(s);
		return listaSpettacoli;
	}
	
	public ArrayList<Spettacolo> nonDuplicateListaSpettacoli(Criterio c1, Criterio c2) {
		ArrayList<Spettacolo> listaSpettacoli = new ArrayList<Spettacolo>();
		ArrayList<Spettacolo> oldList = getListaSpettacoli();
		for (Spettacolo s : oldList)
			if (c1.criterio(s) && c2.criterio(s) && isFruibile(s) && cercaSpettacolo(listaSpettacoli, s))
				listaSpettacoli.add(s);
		return listaSpettacoli;
	}
	
	public boolean cercaSpettacolo(ArrayList<Spettacolo> lista, Spettacolo spettacolo) {
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).getFilm().equals(spettacolo.getFilm())) return false;
		return true;
	}
	
	public boolean isFruibile(Spettacolo spettacolo) {
		Calendar cal = Calendar.getInstance();
		int hh = (cal.get(Calendar.HOUR) + 12);
		int mm = (cal.get(Calendar.MINUTE));
		String ora = "";
		if (hh < 10) ora = 0 + hh + ":";
		else ora = hh + ":";
		if (mm < 10) ora += 0 + mm;
		else ora += mm;
		int r = spettacolo.compareCalendar(spettacolo.getData(), cal);
		if((r == 0) && (spettacolo.getOra().compareTo(ora) == 1)) return true;
		else if (r > 0) return true;
		return false;
	}
	
	public ArrayList<Spettacolo> getListaSpettacoli(Criterio c1) {
		return getListaSpettacoli(c1, c1);
	}
	
	public void aggiungiSala(int rows, int columns) {
		gestoreSale.aggiungiSala(rows, columns);
	}
	
	public ArrayList<PrenotazioniCliente> getListaPrenotazioni() {
		return gestorePrenotazioni.getListaPrenotazioni();
	}
	
	public void setDisponibile(Posto posto, Sala sala) throws PostoNonDisponibileException{
		ArrayList<Cliente> listaClienti = getGestoreLogin().getListaClienti();
		ArrayList<Spettacolo> listaSpettacoli = getListaSpettacoli();
		gestorePrenotazioni.setDisponibile(posto, sala, listaClienti, listaSpettacoli);
		posto.rendiDisponibile();
	}
	
	public void setIndisponibile(Posto posto, Sala sala) throws PostoNonDisponibileException{
		ArrayList<Cliente> listaClienti = getGestoreLogin().getListaClienti();
		ArrayList<Spettacolo> listaSpettacoli = getListaSpettacoli();
		gestorePrenotazioni.setIndisponibile(posto, sala, listaClienti, listaSpettacoli);
	}
	
	public void aggiungiSpettacolo(Spettacolo spettacolo) {
		ArrayList<Sala> listaSale = getListaSale();
		for (Sala sala : listaSale)
		{
			if (sala.getNumeroSala() == spettacolo.getSala().getNumeroSala())
				spettacolo.getSala().setPosti(sala.getPosti());
		}
		gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
	}
	
	public int getNumeroSpettacoli() {
		return gestoreProgrammazione.size();
	}
	
	public ArrayList<Spettacolo> getListaSpettacoli() {
		return gestoreProgrammazione.getListaSpettacoli();
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
	
	public ArrayList<Sala> getListaSale() {
		return gestoreSale.getListaSale();
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
	
	public Prenotazione cercaPrenotazione(Posto posto, Spettacolo spettacolo) {
		ArrayList<PrenotazioniCliente> prenotazioni = getListaPrenotazioni();
		for (int i = 0; i < prenotazioni.size(); i++)
		{
			PrenotazioniCliente lista = prenotazioni.get(i);
			for (int j = 0; j < lista.size(); j++)
			{
				Prenotazione prenotazione = lista.getPrenotazione(j);
				if (prenotazione.getSpettacolo().equals(spettacolo) && prenotazione.getPosto().equals(posto))
					return prenotazione;
			}
		}
		return null;
	}
	
	public void rimuoviPrenotazione(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		//System.out.println("CINEMA : " + prenotazione);
		gestorePrenotazioni.rimuoviPrenotazione(prenotazione, cliente);
	}
	
	public void acquistaPosto(Cliente cliente, Prenotazione prenotazione, Posto p) throws PostoNonDisponibileException{
		//p = prenotazione.getPosto();
		//prenotazione.setPagato();
		if (!p.isOccupato())
		{
			gestorePrenotazioni.aggiungiPrenotazione(cliente, prenotazione);
			gestorePrenotazioni.acquistaPosto(p);
		}
		else if (gestorePrenotazioni.controlloProprietà(cliente, p) != null)
			gestorePrenotazioni.acquistaPosto(p);
		
		gestorePrenotazioni.acquista(cliente, prenotazione);
	}
	
	public Posto controlloProprietà(Cliente cliente, Posto posto) {
		return gestorePrenotazioni.controlloProprietà(cliente, posto);
	}
	
	public GestoreSconti getGestoreSconti() {
		return gestoreSconti;
	}
	
	public float cercaSconto(Cliente cliente, Spettacolo show) {
		return gestoreSconti.cercaSconto(cliente, show);
	}
	
	Criterio settimana = (Spettacolo s1) -> {
		Calendar dataSpettacolo = s1.getData();
		Calendar nowDate = Calendar.getInstance();
		int r;
		if ((r = s1.compareCalendar(dataSpettacolo, nowDate)) <= 7 && r >= 0)
			return true;
		return false;
	};
	
	public Criterio sempre = (Spettacolo s1) -> {return true;};
}
