package GestoreSconti;

import java.io.Serializable;

/**
 * Uno sconto è composto da uno scontatore, da unoa descrizione e da uno stato: attivo o non attivo
 * @author MarioELT
 *
 * @param <T>
 */
public class Sconto<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Scontatore<T> scontatore;
	String descrizione;
	boolean attivo;
	
	/**
	 * Costruisce lo sconto
	 * @param sc scontatore da utilizzare per l'applicazione degli sconti
	 * @param description descrizione dello sconto
	 */
	public Sconto(Scontatore<T> sc, String description) {
		scontatore = sc;
		descrizione = description;
		attivo = true;
	}
	
	/**
	 * Controlla se lo sconto è attivo
	 * @return true se lo sconto è attivo, false altrimenti
	 */
	public boolean isAttivo() {
		return attivo;
	}
	
	/**
	 * Attiva lo sconto
	 */
	public void attiva() {
		attivo = true;
	}
	
	/**
	 * Disattiva lo sconto
	 */
	public void disattiva() {
		attivo = false;
	}
	
	/**
	 * Calcola lo sconto
	 * @param obj elemento dello sconto
	 * @return percentuale sconto
	 */
	public float calcolaSconto(T obj) {
		return scontatore.calcolaSconto(obj);
	}
	
	/**
	 * Restituisce lo sconto sotto forma di stringa nel formato standard
	 */
	public String toString() {
		return getClass().getSimpleName() + "[attivo=" + attivo + ", descrizione=" + descrizione + "]";
	}
	
	/**
	 * Restituisce la descrizione dello sconto
	 * @return descrizione dello sconto
	 */
	public String getDescrizione() {
		return descrizione;
	}
}
