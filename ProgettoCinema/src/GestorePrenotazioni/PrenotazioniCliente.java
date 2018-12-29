package GestorePrenotazioni;

import java.io.Serializable;
import java.util.ArrayList;

import GestoreLogin.Cliente;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;

/**
 * PrenotazioniCliente è un'astrazione che mantiene una lista di prenotazioni per ogni cliente
 * @author MarioELT
 *
 */
public class PrenotazioniCliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private ArrayList<Prenotazione> prenotazioniCliente;
	
	/**
	 * Costruisce l'astrazione
	 * @param customer cliente proprietario della lista
	 */
	public PrenotazioniCliente(Cliente customer) {
		prenotazioniCliente = new ArrayList<Prenotazione>();
		cliente = customer;
	}
	
	/**
	 * Aggiunge una prenotazione
	 * @param prenotazione prenotazione da aggiungere
	 */
	public void aggiungiPrenotazione(Prenotazione prenotazione) {
		prenotazioniCliente.add(prenotazione);
	}
	
	/**
	 * Rimuove una prenotazione
	 * @param prenotazione prenotazione da rimuovere
	 */
	public void rimuoviPrenotazione(Prenotazione prenotazione) {
		prenotazioniCliente.remove(prenotazione);
		//System.out.println("RIMOZIONE : " + prenotazioniCliente.remove(prenotazione));
	}
	
	/**
	 * Restituisce la lista di prenotazioni
	 * @return lista di tutte le prenotazioni
	 */
	public ArrayList<Prenotazione> getListaPrenotazioni() {
		return prenotazioniCliente;
	}
	
	/**
	 * Restituisce la prenotazione specificata in base all'indice
	 * @param i indice della prenotazione
	 * @return prenotazione nell'indice i
	 */
	public Prenotazione getPrenotazione(int i) {
		return prenotazioniCliente.get(i);
	}
	
	/**
	 * Cerca un posto per lo spettacolo specificato nella lista delle prenotazioni del cliente
	 * @param posto posto da ricercare
	 * @param spettacolo spettacolo da ricercare
	 * @return posto posto che combacia alla ricerca
	 */
	public Posto searchPrenotazione(Posto posto, Spettacolo spettacolo) {
		if (posto == null) return null;
		for (int i = 0; i < prenotazioniCliente.size(); i++)
		{
			Prenotazione pren = prenotazioniCliente.get(i);
			Posto posto2 = prenotazioniCliente.get(i).getPosto();
			if (posto.getRiga() == posto2.getRiga() && posto.getColonna() == posto2.getColonna() && pren.getSpettacolo().equals(spettacolo))
				return prenotazioniCliente.get(i).getPosto();
		}
		
		return null;
	}
	
	/**
	 * Restituisce il cliente
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * Restituisce la grandezza della lista
	 * @return grandezza della lista
	 */
	public int size() {
		return prenotazioniCliente.size();
	}
	
}
