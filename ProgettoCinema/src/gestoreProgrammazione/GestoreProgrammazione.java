package gestoreProgrammazione;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Il gestore della programmazione è il gestore che si occupa di
 * aggiungere e rimuovere spettacoli dalla programmazione
 * @author MarioELT
 *
 */
public class GestoreProgrammazione implements Serializable{
	
	private static final long serialVersionUID = 1L;
	ArrayList<Spettacolo> listaSpettacoli;
	ArrayList<Film> listaFilm;
	
	/**
	 * Costruisce il gestore della programmazione
	 */
	public GestoreProgrammazione() {
		listaSpettacoli = new ArrayList<Spettacolo>();
		listaFilm = new ArrayList<Film>();
	}
	
	/**
	 * Aggiunge uno spettacolo alla programmazione
	 * @param spettacolo spettacolo da aggiungere
	 */
	public void aggiungiSpettacolo(Spettacolo spettacolo) {
		listaSpettacoli.add(spettacolo);
		aggiungiFilm(spettacolo.getFilm());
	}
	
	/**
	 * Rimuove uno spettacolo dalla programmazione
	 * @param spettacolo spettacolo da rimuovere
	 */
	public void rimuoviSpettacolo(Spettacolo spettacolo) {
		listaSpettacoli.remove(spettacolo);
	}
	
	/**
	 * Aggiunge un film alla lista dei film
	 * @param film film da aggiungere
	 */
	public void aggiungiFilm(Film film) {
		if (!cercaFilm(film))
			listaFilm.add(film);
	}
	
	/**
	 * Rimuove un film dalla programmazione
	 * @param film film da rimuovere
	 */
	public void rimuoviFilm(Film film) {
		listaFilm.remove(film);
		ArrayList<Spettacolo> lista = getListaSpettacoli();
		
		//i-- perchè la lista si accorcia
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).getFilm().equals(film))
				rimuoviSpettacolo(lista.get(i--));
	}
	
	/**
	 * Restituisce la lista degli spettacoli
	 * @return lista di tutti gli spettacoli
	 */
	public ArrayList<Spettacolo> getListaSpettacoli() {
		return listaSpettacoli;
	}
	
	/**
	 * Restituisce la lista dei film
	 * @return lista di tutti i film
	 */
	public ArrayList<Film> getListaFilm() {
		return listaFilm;
	}
	
	/**
	 * Restituisce lo spettacolo specificato dall'indice
	 * @param i indice dello spettacolo
	 * @return spettacolo specificato dall'indice
	 */
	public Spettacolo getSpettacolo(int i) {
		return listaSpettacoli.get(i);
	}
	
	/**
	 * Restituisce il film specificato dall'indice
	 * @param i indice dello spettacolo
	 * @return fil specificato dall'indice
	 */
	public Film getFilm(int i) {
		return listaFilm.get(i);
	}
	
	/**
	 * Restituisce la grandezza della lista degli spettacoli
	 * @return grandezza lista spettacoli
	 */
	public int getNumeroSpettacoli() {
		return listaSpettacoli.size();
	}
	
	/**
	 * Cerca un film nella lista di film
	 * @param movie film da cercare
	 * @return true se il film viene trovato, false altrimenti
	 */
	public boolean cercaFilm(Film movie) {
		for (int i = 0; i < listaFilm.size(); i++)
			if (listaFilm.get(i).equals(movie)) return true;
		return false;
	}
	
}
