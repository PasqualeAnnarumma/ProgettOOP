package GestoreSconti;

import java.io.Serializable;
import java.util.ArrayList;

import GestoreLogin.Cliente;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;

/**
 * Il GestoreSconti è il gestore che si occupa di aggiungere, rimuovere, attivare e disattivare gli sconti
 * @author MarioELT
 *
 */
public class GestoreSconti implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Sconto<Cliente>> listaScontiCliente;
	private ArrayList<Sconto<Spettacolo>> listaScontiSpettacolo;
	private ArrayList<Sconto<Film>> listaScontiFilm;
	
	/**
	 * Costruisce il gestore degli sconti
	 */
	public GestoreSconti() {
		listaScontiCliente = new ArrayList<Sconto<Cliente>>();
		listaScontiSpettacolo = new ArrayList<Sconto<Spettacolo>>();
		listaScontiFilm = new ArrayList<Sconto<Film>>();
	}
	
	/**
	 * Aggiunge uno sconto applicabile ad un cliente
	 * @param sconto sconto per il cliente
	 */
	public void aggiungiScontoCliente(Sconto<Cliente> sconto) {
		listaScontiCliente.add(sconto);
	}
	
	/**
	 * Rimuove uno sconto per un cliente
	 * @param i indice nella lista per la rimozione
	 */
	public void rimuoviScontoCliente(int i) {
		listaScontiCliente.remove(i);
	}
	
	/**
	 * Restituisce uno sconto per un cliente specificando l'indice
	 * @param i indice nella lista
	 * @return sconto per il cliente
	 */
	public Sconto<Cliente> getScontoCliente(int i) {
		return listaScontiCliente.get(i);
	}
	
	/**
	 * Restituisce la lsita degli sconti per il cliente
	 * @return lista degli sconti per il cliente
	 */
	public ArrayList<Sconto<Cliente>> getScontiCliente() {
		return listaScontiCliente;
	}
	
	/**
	 * Aggiunge uno sconto applicabile ad uno settacolo
	 * @param sconto sconto da aggiungere
	 */
	public void aggiungiScontoSpettacolo(Sconto<Spettacolo> sconto) {
		listaScontiSpettacolo.add(sconto);
	}
	
	/**
	 * Rimuove uno sconto per uno spettacolo
	 * @param i indice nella lista
	 */
	public void rimuoviScontoSpettacolo(int i) {
		listaScontiSpettacolo.remove(i);
	}
	
	/**
	 * Restituisce lo sconto dello spettacolo
	 * @param i indice nella lista
	 * @return sconto corrispondente all'indice nella lista
	 */
	public Sconto<Spettacolo> getScontoSpettacolo(int i) {
		return listaScontiSpettacolo.get(i);
	}
	
	/**
	 * Restituisce la lista degli sconti per gli spettacoli
	 * @return lista sconti spettacoli
	 */
	public ArrayList<Sconto<Spettacolo>> getScontiSpettacolo() {
		return listaScontiSpettacolo;
	}
	
	/**
	 * Aggiunge uno sconto applicabile ad un film
	 * @param sconto sconto da aggiungere
	 */
	public void aggiungiScontoFilm(Sconto<Film> sconto) {
		listaScontiFilm.add(sconto);
	}
	
	/**
	 * Rimuove uno sconto per un film
	 * @param i indice nella lista per la rimozione
	 */
	public void rimuoviScontoFilm(int i) {
		listaScontiFilm.remove(i);
	}
	
	/**
	 * Restituisce lo sconto per un film in base a un indice
	 * @param i indice nella lista
	 * @return
	 */
	public Sconto<Film> getScontoFilm(int i) {
		return listaScontiFilm.get(i);
	}
	
	/**
	 * Restituisce la lista degli sconti per i film
	 * @return lista degli sconti dei film
	 */
	public ArrayList<Sconto<Film>> getScontiFilm() {
		return listaScontiFilm;
	}
	
	/**
	 * Cerca lo sconto più conveniente
	 * @param cliente cliente al quale si applica lo sconto
	 * @param show spettacolo al quale si applica lo sconto
	 * @return percentuale sconto migliore
	 */
	public float cercaSconto(Cliente cliente, Spettacolo show) {
		float sconto = 0;
		if (cliente.haUsatoSconto()) return 0;
		
		for (int i = 0; i < listaScontiCliente.size(); i++)
		{
			Sconto<Cliente> s = listaScontiCliente.get(i);
			float temp = s.calcolaSconto(cliente);
			if (sconto < temp && s.isAttivo()) sconto = temp;
		}
		
		for (int i = 0; i < listaScontiSpettacolo.size(); i++)
		{
			Sconto<Spettacolo> s = listaScontiSpettacolo.get(i);
			float temp = s.calcolaSconto(show);
			if (sconto < temp && s.isAttivo()) sconto = temp;
		}
		
		for (int i = 0; i < listaScontiFilm.size(); i++)
		{
			Sconto<Film> s = listaScontiFilm.get(i);
			float temp = s.calcolaSconto(show.getFilm());
			if (sconto < temp && s.isAttivo()) sconto = temp;
		}
		
		return sconto;
	}
	
}
