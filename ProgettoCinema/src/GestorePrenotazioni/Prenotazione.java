package GestorePrenotazioni;

import java.io.Serializable;

import GestoreLogin.Cliente;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;

/**
 * Una prenotazione rappresenta un posto acquistato o prenotato da un cliente per uno spettacolo
 * @author MarioELT
 *
 */
public class Prenotazione implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Spettacolo spettacolo;
	private Cliente cliente;
	private Posto posto;
	private boolean pagato;
	private double prezzo;
	
	/**
	 * Costruisce la prenotazione
	 * @param show spettacolo della prenotazione
	 * @param posto posto della prenotazione
	 * @param cliente cliente che effettua la prenotazione
	 */
	public Prenotazione(Spettacolo show, Posto posto, Cliente cliente) {
		spettacolo = show;
		this.cliente = cliente;
		this.posto = posto;
		pagato = false;
		prezzo = 0;
	}
	
	/**
	 * Restituisce la stringa contenente le informazioni della prenotazione
	 */
	public String toString() {
		return getClass().getSimpleName() + "[spettacolo=" + spettacolo + ",posto=" + posto + ",cliente=" + cliente.getUsername() + ",pagato=" + pagato + ",prezzo=" + prezzo + "]";
	}
	
	/**
	 * Restituisce il cliente della prenotazione
	 * @return cliente delle prenotazione
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * Controlla se due prenotazioni sono uguali
	 * @return true se sono uguali, false altrimenti
	 */
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Prenotazione p = (Prenotazione) obj;
		return p.spettacolo.equals(spettacolo) && posto.equals(p.posto) && cliente.equals(p.cliente) && pagato == p.pagato && prezzo == p.prezzo;
	}
	
	/**
	 * Restituisce il posto della prenotazione
	 * @return posto della prenotazione
	 */
	public Posto getPosto() {
		return posto;
	}
	
	/**
	 * Cambia il posto della prenotazione
	 * @param posto nuovo posto della prenotazione
	 */
	public void setPosto(Posto posto) {
		this.posto = posto;
	}
	
	/**
	 * Restituisce lo spettacolo della prenotazione
	 * @return spettacolo della prenotazione
	 */
	public Spettacolo getSpettacolo() {
		return spettacolo;
	}
	
	/**
	 * Controlla se lo spettacolo è stato già pagato e quindi acquistato
	 * @return true se è stato pagato, false altrimenti
	 */
	public boolean isPagato() {
		return pagato;
	}
	
	/**
	 * Restituisce il prezzo realmente pagato, tenendo conto dello sconto applicato
	 * @return prezzo realmente pagato
	 */
	public double getPrezzoPagato() {
		return prezzo;
	}
	
	/**
	 * Imposta la prenotazione come pagata
	 */
	public void setPagato() {
		pagato = true;
		setPrezzo(prezzo);
	}
	
	/**
	 * Imposta la prenotazione come non pagata
	 */
	public void setNonPagato() {
		pagato = false;
		prezzo = 0;
	}
	
	/**
	 * imposta il prezzo della prenotazione
	 * @param price
	 */
	public void setPrezzo(double price) {
		prezzo = price;
	}
}
