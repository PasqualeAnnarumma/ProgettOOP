package multisala;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Il Multisala contiene la programmazione settimanale ed è composto da varie sale.
 */
public class Multisala implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private Programmazione programmazione;
	private ArrayList<Film> listaFilm;
	private ArrayList<Sala> listaSale;
	private double incassoCinema;

	/**
	 * Crea una nuova istanza di Multisala.
	 */
	public Multisala() {
		this.programmazione = new Programmazione();
		this.listaFilm = new ArrayList<Film>();
		this.listaSale = new ArrayList<Sala>();
	}

	/**
	 * Restituisce la programmazione attuale del multisala.
	 * @return la programmazione attuale.
	 */
	public Programmazione getProgrammazione() {
		return programmazione;
	}

	/**
	 * Restituisce la lista dei film attualmente disponibili
	 * @return la lista dei film attualmente disponibili.
	 */
	public ArrayList<Film> getListaFilm() {
		return listaFilm;
	}

	/**
	 * Restituisce la varie sale presenti nel Multisala
	 * @return la lista delle sale.
	 */
	public ArrayList<Sala> getListaSala() {
		return listaSale;
	}

	/**
	 * Restituisce l'incasso totale del multisala questa settimana.
	 * @return l'incasso settimanale di tutti i film.
	 */
	public double getIncasso() {
		return incassoCinema;
	}

	/**
	 * Aggiunge un nuovo film alla lista di film disponibili.
	 * @param nuovoFilm
	 */
	public void aggiungiFilm(Film nuovoFilm) {
		listaFilm.add(nuovoFilm);
	}

	/**
	 * Aggiunge una nuova sala disponibile al pubblico.
	 * @param nuovaSala
	 */
	public void aggiungiSala(Sala nuovaSala) {
		listaSale.add(nuovaSala);
	}

	/**
	 * Rimuove un film dalla lista di film disponibili
	 * @param film
	 */
	public void rimuoviFilm(Film film) {
		listaFilm.remove(film);
	}

	/**
	 * Rende indisponibile una sala al pubblico.
	 * @param sala
	 */
	public void rimuoviSala(Sala sala) {
		listaSale.remove(sala);
	}

	/**
	 * Riporta a zero l'incasso totale del multisala.
	 */
	public void resetIncasso() {
		incassoCinema = 0;
	}

	/**
	 * Aggiunge il prezzo di un biglietto venduto all'incasso totale del multisala.
	 * @param incassoAggiunto
	 */
	public void aggiornaIncasso(double incassoAggiunto) {
		incassoCinema += incassoAggiunto;
	}

	/**
	 * Metodi di Object sovrascritti.
	 */
	public String toString() {
		return getClass().getSimpleName() + "[programmazione=" + programmazione + ", listaFilm=" + listaFilm
				+ ", listaSala=" + listaSale + ", incassoCinema=" + incassoCinema + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		if (otherObject.getClass() != getClass())
			return false;
		Multisala obj = (Multisala) otherObject;
		return listaFilm.equals(obj.listaFilm) && listaSale.equals(obj.listaSale)
				&& programmazione.equals(obj.programmazione);
	}

	public Multisala clone() {
		try {
			ArrayList<Film> clonedFilm = new ArrayList<Film>();
			ArrayList<Sala> clonedSale = new ArrayList<Sala>();
			Multisala cloned = (Multisala) super.clone();
			for (int i = 0; i < listaFilm.size(); i++)
				clonedFilm.add(listaFilm.get(i));
			for (int i = 0; i < listaFilm.size(); i++)
				clonedSale.add(listaSale.get(i));
			cloned.listaSale = clonedSale;
			cloned.listaFilm = clonedFilm;
			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
