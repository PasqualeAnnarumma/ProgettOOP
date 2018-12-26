package GestoreLogin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import Eccezioni.AccountGiaEsistenteException;
import Eccezioni.PostoNonDisponibileException;
import GestoreLogin.Cliente.Categoria;
import GestorePrenotazioni.GestorePrenotazioni;
import GestorePrenotazioni.Prenotazione;
import GestorePrenotazioni.PrenotazioniCliente;
import GestoreProgrammazione.Selettore;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.GestoreProgrammazione;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.GestoreSale;
import GestoreSale.Posto;
import GestoreSale.Sala;
import GestoreSconti.GestoreSconti;

public class Cinema {
	
	private GestoreLogin gestoreLogin;
	private GestoreSale gestoreSale;
	private GestoreProgrammazione gestoreProgrammazione;
	private GestorePrenotazioni gestorePrenotazioni;
	private GestoreSconti gestoreSconti;
	private Utente utente;
	
	public Cinema() {
		gestoreLogin = new GestoreLogin();
		gestoreSale = new GestoreSale();
		gestoreProgrammazione = new GestoreProgrammazione();
		gestorePrenotazioni = new GestorePrenotazioni();
		gestoreSconti = new GestoreSconti();
	}
	
	public void registraCliente(String usr, String pwd, String compleanno, Categoria category) throws AccountGiaEsistenteException{
		gestoreLogin.aggiungiCliente(usr, pwd, compleanno, category);
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
	
	public ArrayList<Spettacolo> getListaSpettacoli() {
		return gestoreProgrammazione.getListaSpettacoli();
	}
	
	public ArrayList<Spettacolo> getListaSpettacoli(Comparator<Spettacolo> c1, String sala) {
		ArrayList<Spettacolo> oldList = getListaSpettacoli();
		OrdinaLista<Spettacolo> ordina = new OrdinaLista<Spettacolo>(c1);
		for (int i = 0; i < oldList.size(); i++)
			ordina.add(oldList.get(i));
		
		ArrayList<Spettacolo> listaDue = ordina.getList();
		ArrayList<Spettacolo> lista = new ArrayList<Spettacolo>();
		for (Spettacolo s : listaDue)
		{
			if (sala.equals("Tutte") || (sala.equals(s.getSala() + "")))
				lista.add(s);
		}
		
		return lista;
	}
	
	public ArrayList<Spettacolo> getListaSpettacoli(Selettore<Spettacolo> c1) {
		ArrayList<Spettacolo> listaSpettacoli = new ArrayList<Spettacolo>();
		ArrayList<Spettacolo> oldList = getListaSpettacoli();
		for (Spettacolo s : oldList)
			if (c1.seleziona(s) && isFruibile(s) && cercaSpettacolo(listaSpettacoli, s))
				listaSpettacoli.add(s);
		return listaSpettacoli;
	}
	
	public ArrayList<Spettacolo> getSpettacoliFruibili(Comparator<Spettacolo> c1, String sala) {
		ArrayList<Spettacolo> spettacoli = getListaSpettacoli(c1, sala);
		ArrayList<Spettacolo> lista = new ArrayList<Spettacolo>();
		for (Spettacolo s : spettacoli)
			if (isFruibile(s)) lista.add(s);
		
		return lista;
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
		else if (r >= 0) return true;
		return false;
	}
	
	/*public ArrayList<Spettacolo> getListaSpettacoli(Criterio c1) {
		return getListaSpettacoli(c1, c1);
	}*/
	
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
	
	public void aggiungiFilm(Film film) {
		gestoreProgrammazione.aggiungiFilm(film);
	}
	
	public void rimuoviFilm(Film film) throws PostoNonDisponibileException {
		gestoreProgrammazione.rimuoviFilm(film);
		aggiornaPrenotazioni(film);
	}
	
	public void aggiornaPrenotazioni(Film film) throws PostoNonDisponibileException{
		ArrayList<Prenotazione> listaPrenotazioni = gestorePrenotazioni.getListaPrenotazioni(film);
		for (int i = 0; i < listaPrenotazioni.size(); i++)
			gestorePrenotazioni.rimuoviPrenotazione(listaPrenotazioni.get(i), listaPrenotazioni.get(i).getCliente());
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
	
	public void rimuoviSpettacolo(Spettacolo spettacolo) throws PostoNonDisponibileException{
		gestoreProgrammazione.rimuoviSpettacolo(spettacolo);
		aggiornaPrenotazioni(spettacolo.getFilm());
	}
	
	public int getNumeroSpettacoli() {
		return gestoreProgrammazione.size();
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
		else if (gestorePrenotazioni.controlloProprietà(cliente, p, prenotazione.getSpettacolo()) != null)
			gestorePrenotazioni.acquistaPosto(p);
		
		gestorePrenotazioni.acquista(cliente, prenotazione);
		cliente.addPrenotazione();
	}
	
	public Posto controlloProprietà(Cliente cliente, Posto posto, Spettacolo spettacolo) {
		return gestorePrenotazioni.controlloProprietà(cliente, posto, spettacolo);
	}
	
	public GestoreSconti getGestoreSconti() {
		return gestoreSconti;
	}
	
	public float cercaSconto(Cliente cliente, Spettacolo show) {
		return gestoreSconti.cercaSconto(cliente, show);
	}
	
	public Selettore<Spettacolo> settimana = (Spettacolo s1) -> {
		Calendar dataSpettacolo = s1.getData();
		Calendar nowDate = Calendar.getInstance();
		int r;
		if ((r = s1.compareCalendar(dataSpettacolo, nowDate)) <= 7 && r >= 0)
			return true;
		return false;
	};
	
	public Comparator<Spettacolo> ordineCronologico = (Spettacolo s1, Spettacolo s2) -> {
		if (s1.stringDate().compareTo(s2.stringDate()) < 0) return -1;
		if (s1.stringDate().compareTo(s2.stringDate()) > 0) return 1;
		return s1.getOra().compareTo(s2.getOra());
	};
	
	public Comparator<Spettacolo> salaCrescente = (Spettacolo s1, Spettacolo s2) -> {
		if (s1.getSala().getNumeroSala() < s2.getSala().getNumeroSala()) return -1;
		if (s1.getSala().getNumeroSala() > s2.getSala().getNumeroSala()) return 1;
		return 0;
	};
	
	public Comparator<Spettacolo> titoloAlfabetico = (Spettacolo s1, Spettacolo s2) -> {
		return s1.getFilm().getNome().compareTo(s2.getFilm().getNome());
	};
	
	public Comparator<Spettacolo> postiDisponibili = (Spettacolo s1, Spettacolo s2) -> {
		if (s1.getPostiLiberi() < s2.getPostiLiberi()) return -1;
		if (s1.getPostiLiberi() > s2.getPostiLiberi()) return 1;
		return 0;
	};
}
