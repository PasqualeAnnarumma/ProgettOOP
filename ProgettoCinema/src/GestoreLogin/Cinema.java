package GestoreLogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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

/**
 * Il cinema è il sistema centrale che comunica con i vari gestori per eseguire le operazioni
 * @author MarioELT
 *
 */
public class Cinema implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private GestoreLogin gestoreLogin;
	private GestoreSale gestoreSale;
	private GestoreProgrammazione gestoreProgrammazione;
	private GestorePrenotazioni gestorePrenotazioni;
	private GestoreSconti gestoreSconti;
	private Utente utente;
	
	/**
	 * Costruisce il cinema
	 */
	public Cinema() {
		gestoreLogin = new GestoreLogin();
		gestoreSale = new GestoreSale();
		gestoreProgrammazione = new GestoreProgrammazione();
		gestorePrenotazioni = new GestorePrenotazioni();
		gestoreSconti = new GestoreSconti();
	}
	
	/**
	 * Registra un cliente
	 * @param usr username del cliente
	 * @param pwd password del cliente
	 * @param dataNascita data di nascita
	 * @param category categoria del cliente (studente, pensionat, ecc...)
	 * @throws AccountGiaEsistenteException se l'username è già utilizzato da qualche utente
	 */
	public void registraCliente(String usr, String pwd, String dataNascita, Categoria category) throws AccountGiaEsistenteException{
		gestoreLogin.aggiungiCliente(usr, pwd, dataNascita, category);
	}
	
	/**
	 * Registra un amministratore
	 * @param usr username dell'amminitratore
	 * @param pwd password dell'amministratore
	 * @throws AccountGiaEsistenteException se l'username è già utilizzato da qualche amministratore
	 */
	public void registraAmministratore(String usr, String pwd) throws AccountGiaEsistenteException{
		gestoreLogin.aggiungiAmministratore(usr, pwd);
	}
	
	/**
	 * Restituisce il numero di sale del cinema
	 * @return numero di sale del cinema
	 */
	public int getNumeroSale() {
		return gestoreSale.size();
	}
	
	/**
	 * Restituisce la lista dei film
	 * @return lista dei film
	 */
	public ArrayList<Film> getListaFilm() {
		return gestoreProgrammazione.getListaFilm();
	}
	
	/**
	 * Restituisce l'incasso per gli spettacoli specificati
	 * @param spettacoli lista di tutti gli spettacoli
	 * @return incasso totale di tutti gli spettacoli specificati
	 */
	public float getIncasso(ArrayList<Spettacolo> spettacoli) {
		float incasso = 0;
		for (Spettacolo s : spettacoli)
			incasso += getIncasso(s.getFilm());
		incasso *= 1000;
		Math.floor(incasso);
		incasso /= 1000;
		return incasso;
	}
	
	/**
	 * Restituisce tutte le prenotazioni per il film specificato
	 * @param film film per il quale si voglniono le prenotazioni
	 * @return lista delle prenotazioni che riguardano quel film
	 */
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
	
	/**
	 * Restituisce l'incasso totale del film specificato
	 * @param film film per il quale si vuole sapere l'incasso totale
	 * @return totale dell'incasso prodotto da quel film
	 */
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
	
	/**
	 * Restituisce la lista degli spettacoli
	 * @return lista di tutti gli spettacoli
	 */
	public ArrayList<Spettacolo> getListaSpettacoli() {
		return gestoreProgrammazione.getListaSpettacoli();
	}
	
	/**
	 * Restituisce la lista degli spettacoli in base ad un comparator ed una sala specificati
	 * @param comp comparatore
	 * @param sala sala degli spettacoli che si desiderano
	 * @return lista degli spettacoli filtrati per sala in base al comparatore
	 */
	public ArrayList<Spettacolo> getListaSpettacoli(Comparatore<Spettacolo> comp, String sala) {
		ArrayList<Spettacolo> oldList = getListaSpettacoli();
		OrdinaLista<Spettacolo> ordina = new OrdinaLista<Spettacolo>(comp);
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
	
	/**
	 * Restituisce la lista degli spettacoli in base ad un selettore
	 * @param select selettore
	 * @return lista degli spettacoli filtrati in base al selettore
	 */
	public ArrayList<Spettacolo> getListaSpettacoli(Selettore<Spettacolo> select) {
		ArrayList<Spettacolo> listaSpettacoli = new ArrayList<Spettacolo>();
		ArrayList<Spettacolo> oldList = getListaSpettacoli();
		for (Spettacolo s : oldList)
			if (select.seleziona(s) && isFruibile(s) && cercaSpettacolo(listaSpettacoli, s))
				listaSpettacoli.add(s);
		return listaSpettacoli;
	}
	
	/**
	 * Restituisce la lista degli spettacoli fruibili, cioè che non sono ancora iniziati
	 * @param comp comparator
	 * @param sala sala degli spettacoli che si desiderano
	 * @return lista degli spettacoli filtrati per sala e in base al comparatore, che sono fruibili
	 */
	public ArrayList<Spettacolo> getSpettacoliFruibili(Comparatore<Spettacolo> comp, String sala) {
		ArrayList<Spettacolo> spettacoli = getListaSpettacoli(comp, sala);
		ArrayList<Spettacolo> lista = new ArrayList<Spettacolo>();
		for (Spettacolo s : spettacoli)
			if (isFruibile(s)) lista.add(s);
		
		return lista;
	}
	
	/**
	 * Cerca uno spettacolo in una lista di spettacoli
	 * @param lista lista di spettacoli nella quale cercare
	 * @param spettacolo spettacolo da trovare
	 * @return false se lo spettacolo è stato trovato, true altrimenti
	 */
	public boolean cercaSpettacolo(ArrayList<Spettacolo> lista, Spettacolo spettacolo) {
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).getFilm().equals(spettacolo.getFilm())) return false;
		return true;
	}
	
	/**
	 * Controlla se uno spettacolo è fruibile, cioè se è già iniziato
	 * @param spettacolo spettacolo da controllare
	 * @return true se è fruibile (ancora non inizia), false altrimenti (già iniziato)
	 */
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
	
	/**
	 * Aggiunge una sala al cinema
	 * @param rows righe della sala
	 * @param columns colonne della sala
	 */
	public void aggiungiSala(int rows, int columns) {
		gestoreSale.aggiungiSala(rows, columns);
	}
	
	/**
	 * Restituisce la lista di tutte le prenotazioni
	 * @return lista di tutte le prenotazioni
	 */
	public ArrayList<PrenotazioniCliente> getListaPrenotazioni() {
		return gestorePrenotazioni.getListaPrenotazioni();
	}
	
	/**
	 * Imposta un posto della sala come disponibile
	 * @param posto posto da rendere disponibile
	 * @param sala sala nella quale è il posto
	 * @throws PostoNonDisponibileException se è il posto è già disponibile
	 */
	public void setDisponibile(Posto posto, Sala sala) throws PostoNonDisponibileException{
		ArrayList<Spettacolo> listaSpettacoli = getListaSpettacoli();
		gestorePrenotazioni.setDisponibile(posto, sala, listaSpettacoli);
		posto.rendiDisponibile();
	}
	
	/**
	 * Imposta un posto della sala come non disponibile
	 * @param posto posto da rendere indisponibile
	 * @param sala sala nella quale è il posto
	 * @throws PostoNonDisponibileException se il posto è già indisponibile
	 */
	public void setIndisponibile(Posto posto, Sala sala) throws PostoNonDisponibileException{
		ArrayList<Cliente> listaClienti = getGestoreLogin().getListaClienti();
		ArrayList<Spettacolo> listaSpettacoli = getListaSpettacoli();
		gestorePrenotazioni.setIndisponibile(posto, sala, listaClienti, listaSpettacoli);
	}
	
	/**
	 * Aggiunge un film
	 * @param film film da aggiungere
	 */
	public void aggiungiFilm(Film film) {
		gestoreProgrammazione.aggiungiFilm(film);
	}
	
	/**
	 * Rimuove un film
	 * @param film film da rimuovere
	 * @throws PostoNonDisponibileException 
	 */
	public void rimuoviFilm(Film film) throws PostoNonDisponibileException {
		gestoreProgrammazione.rimuoviFilm(film);
		rimuoviPrenotazioniFilm(film);
	}
	
	/**
	 * Rimuove tutte le prenotazioni per un film
	 * @param film film del quale si vogliono eliminare le prenotazioni
	 * @throws PostoNonDisponibileException se il posto è già libero o è di un altro utente (non viene lanciata mai)
	 */
	public void rimuoviPrenotazioniFilm(Film film) throws PostoNonDisponibileException{
		ArrayList<Prenotazione> listaPrenotazioni = gestorePrenotazioni.getListaPrenotazioni(film);
		for (int i = 0; i < listaPrenotazioni.size(); i++)
			gestorePrenotazioni.rimuoviPrenotazione(listaPrenotazioni.get(i), listaPrenotazioni.get(i).getCliente());
	}
	
	/**
	 * Aggiunge uno spettacolo
	 * @param spettacolo spettacolo da aggiungere
	 */
	public void aggiungiSpettacolo(Spettacolo spettacolo) {
		ArrayList<Sala> listaSale = getListaSale();
		for (Sala sala : listaSale)
		{
			if (sala.getNumeroSala() == spettacolo.getSala().getNumeroSala())
				spettacolo.getSala().setPosti(sala.getPosti());
		}
		gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
	}
	
	/**
	 * Rimuove uno spettacolo
	 * @param spettacolo spettacolo da rimuovere
	 * @throws PostoNonDisponibileException se il posto è già libero o è di un altro utente (non viene lanciata mai)
	 */
	public void rimuoviSpettacolo(Spettacolo spettacolo) throws PostoNonDisponibileException{
		gestoreProgrammazione.rimuoviSpettacolo(spettacolo);
		rimuoviPrenotazioniFilm(spettacolo.getFilm());
	}
	
	/**
	 * Restituisce il numero di spettacoli totali
	 * @return numero di spettacoli totali
	 */
	public int getNumeroSpettacoli() {
		return gestoreProgrammazione.size();
	}
	
	/**
	 * restituisce l'utente
	 * @return utente della sessione in corso
	 */
	public Utente getUtente() {
		return utente;
	}
	
	/**
	 * Restituisce il gestore dei login
	 * @return gestore dei login
	 */
	public GestoreLogin getGestoreLogin() {
		return gestoreLogin;
	}
	
	/**
	 * Restituisce il gestore delle sale
	 * @return gestore delle sale
	 */
	public GestoreSale getGestoreSale() {
		return gestoreSale;
	}
	
	/**
	 * Restituisce il gestore delle programmazione
	 * @return gestore della programmazione
	 */
	public GestoreProgrammazione getGestoreProgrammazione() {
		return gestoreProgrammazione;
	}
	
	/**
	 * Restituisce la lista di tutte le sale
	 * @return lista di tutte le sale
	 */
	public ArrayList<Sala> getListaSale() {
		return gestoreSale.getListaSale();
	}
	
	/**
	 * Restituisce il gestore delle prenotazioni
	 * @return gestore delle prenotazioni
	 */
	public GestorePrenotazioni getGestorePrenotazioni() {
		return gestorePrenotazioni;
	}
	
	/**
	 * Effettua il login
	 * @param usr username per il login
	 * @param pwd password dell'account
	 * @return true se i dati corrispondono, false altrimenti
	 */
	public boolean login(String usr, String pwd) {
		utente = gestoreLogin.login(usr, pwd);
		if (utente != null) return true;
		return false;
	}
	
	/**
	 * Aggiunge una prenotazione
	 * @param cliente cliente che effettua la prenotazione
	 * @param prenotazione prenotazione del cliente
	 * @throws PostoNonDisponibileException se il posto è occupato, indisponibile, o è stato già acquistato
	 */
	public void aggiungiPrenotazione(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		gestorePrenotazioni.aggiungiPrenotazione(cliente, prenotazione);
	}
	
	/**
	 * Cerca una prenotazione in base ad uno spettacolo e un posto
	 * @param posto posto associato alla prenotazione
	 * @param spettacolo spettacolo associato alla prenotazione
	 * @return prenotazione che corrisponde al posto e allo spettacolo
	 */
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
	
	/**
	 * Rimuove una prenotazione
	 * @param cliente cliente che ha effettuato la prenotazione
	 * @param prenotazione prenotazione da eliminare
	 * @throws PostoNonDisponibileException se il posto è già libero o è di un altro utente
	 */
	public void rimuoviPrenotazione(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		//System.out.println("CINEMA : " + prenotazione);
		gestorePrenotazioni.rimuoviPrenotazione(prenotazione, cliente);
	}
	
	/**
	 * Acquista un posto nella sala
	 * @param cliente cliente che esegue l'acquisto
	 * @param prenotazione prenotazione del cliente che diventa acquisto
	 * @param posto posto da acquistare
	 * @throws PostoNonDisponibileException se il posto è già acquistato, non disponibile oppure è di un altro utente
	 */
	public void acquistaPosto(Cliente cliente, Prenotazione prenotazione, Posto posto) throws PostoNonDisponibileException{
		//p = prenotazione.getPosto();
		//prenotazione.setPagato();
		if (!posto.isOccupato())
		{
			gestorePrenotazioni.aggiungiPrenotazione(cliente, prenotazione);
			gestorePrenotazioni.acquistaPosto(posto);
		}
		else if (gestorePrenotazioni.controlloProprietà(cliente, posto, prenotazione.getSpettacolo()) != null)
			gestorePrenotazioni.acquistaPosto(posto);
		
		gestorePrenotazioni.acquista(cliente, prenotazione);
		cliente.addPrenotazione();
	}
	
	/**
	 * Controlla se un posto è prenotato dal cliente specificato
	 * @param cliente cliente per il quale si vuole verificare che sia il proprietario del posto
	 * @param posto posto da controllare
	 * @param spettacolo spettacolo che contiene il posto
	 * @return il posto se il cliente è il proprietario del posto, null se non lo è
	 */
	public Posto controlloProprietà(Cliente cliente, Posto posto, Spettacolo spettacolo) {
		return gestorePrenotazioni.controlloProprietà(cliente, posto, spettacolo);
	}
	
	/**
	 * Restituisce il gestore degli sconti
	 * @return gestore degli sconti
	 */
	public GestoreSconti getGestoreSconti() {
		return gestoreSconti;
	}
	
	/**
	 * Cerca il migliore sconto applicabile
	 * @param cliente cliente per il quale si cerca lo sconto
	 * @param show spettacolo per il quale si cerca lo sconto
	 * @return percentuale del miglior sconto applicabile
	 */
	public float cercaSconto(Cliente cliente, Spettacolo show) {
		return gestoreSconti.cercaSconto(cliente, show);
	}
	
	/**
	 * Selettore degli spettacoli per settimana
	 */
	public Selettore<Spettacolo> settimana = (Spettacolo s1) -> {
		Calendar dataSpettacolo = s1.getData();
		Calendar nowDate = Calendar.getInstance();
		int r;
		if ((r = s1.compareCalendar(dataSpettacolo, nowDate)) <= 7 && r >= 0)
			return true;
		return false;
	};
	
	/**
	 * Selettore degli spettacoli per ordine cronologico
	 */
	public Comparatore<Spettacolo> ordineCronologico = (Spettacolo s1, Spettacolo s2) -> {
		if (s1.stringDate().compareTo(s2.stringDate()) < 0) return -1;
		if (s1.stringDate().compareTo(s2.stringDate()) > 0) return 1;
		return s1.getOra().compareTo(s2.getOra());
	};
	
	/**
	 * Comparatore degli spettacoli per sala crescente
	 */
	public Comparatore<Spettacolo> salaCrescente = (Spettacolo s1, Spettacolo s2) -> {
		if (s1.getSala().getNumeroSala() < s2.getSala().getNumeroSala()) return -1;
		if (s1.getSala().getNumeroSala() > s2.getSala().getNumeroSala()) return 1;
		return 0;
	};
	
	/**
	 * Comparatore degli spettacoli per titolo in ordine alfabetico
	 */
	public Comparatore<Spettacolo> titoloAlfabetico = (Spettacolo s1, Spettacolo s2) -> {
		return s1.getFilm().getNome().compareTo(s2.getFilm().getNome());
	};
	
	/**
	 * Comparatore degli spettacoli per maggior numero di posti disponibili
	 */
	public Comparatore<Spettacolo> postiDisponibili = (Spettacolo s1, Spettacolo s2) -> {
		if (s1.getPostiLiberi() < s2.getPostiLiberi()) return -1;
		if (s1.getPostiLiberi() > s2.getPostiLiberi()) return 1;
		return 0;
	};
}
