package utente;

import java.io.Serializable;

import eccezioni.PostoCambiatoException;
import multisala.Posto;
import multisala.Spettacolo;

public class Biglietto implements Serializable {
	private static final long serialVersionUID = 1L;

	public static enum Stato{PRENOTATO, ACQUISTATO};
	
	private Spettacolo spettacolo;
	private Posto posto;
	private Stato stato;
	private double prezzo;
	
	/**
	 * 
	 * @param spettacolo
	 * @param posto
	 * @param stato
	 */
	public Biglietto(Spettacolo spettacolo, Posto posto,double prezzo, Stato stato) {
		this.spettacolo = spettacolo;
		this.posto = posto;
		this.stato = stato;
		this.prezzo = prezzo;
	}
	
	/**
	 * 
	 * @return
	 */
	public Spettacolo getSpettacolo() {
		return spettacolo;
	}
	
	/**
	 * 
	 * @return
	 */
	public Posto getPosto() {
		return posto;
	}
	/**
	 * 
	 */
	public double getPrezzo() {
		return prezzo;
	}
	/**
	 * 
	 * @return
	 */
	public Stato getStato() {
		return stato;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isPrenotato() {
		return stato == Stato.PRENOTATO ? true : false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isAcquistato() {
		return stato == Stato.ACQUISTATO ? true : false;
	}
	
	/**
	 * 
	 * @param posto
	 */
	public void setPosto(Posto posto) throws PostoCambiatoException {
		this.posto = posto;
		throw new PostoCambiatoException();
	}
	
	/**
	 * 
	 * @param stato
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}
	
	/**
	 * 
	 */
	public String toString() {
		return getClass().getSimpleName() + "[spettacolo=" + spettacolo + ", posto=" + posto + ", stato=" + stato + "]";
	}
	
	/**
	 * 
	 */
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		if(getClass() != otherObject.getClass())
			return false;
		Biglietto other = (Biglietto) otherObject;
		return spettacolo.equals(other.spettacolo) && posto.equals(other.posto) && stato == other.stato;
	}	
}
