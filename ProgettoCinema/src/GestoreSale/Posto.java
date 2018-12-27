package GestoreSale;

import java.io.Serializable;

import Eccezioni.PostoNonDisponibileException;

/**
 * Un posto rappresenta il posto fisico nella sala
 * @author MarioELT
 *
 */
public class Posto implements Cloneable, Serializable{
	
	private static final long serialVersionUID = 1L;
	char riga;
	int colonna;
	boolean occupato;
	boolean disponibile;
	boolean acquistato;
	
	/**
	 * Costruisce il posto specificando la riga e la colonna
	 * @param row riga del posto
	 * @param column colonna del posto
	 */
	public Posto(char row, int column) {
		riga = row;
		colonna = column;
		occupato = false;
		disponibile = true;
		acquistato = false;
	}
	
	/**
	 * Costruisce il posto specificando la riga, la colonna e il suo stato
	 * @param row riga del posto
	 * @param column colonna del posto
	 * @param state stato del posto (true = disponibile, false = non disponibile)
	 */
	public Posto(char row, int column, boolean state) {
		riga = row;
		colonna = column;
		occupato = false;
		acquistato = false;
		disponibile = state;
	}
	
	/**
	 * Rende un posto acquistato
	 * @throws PostoNonDisponibileException se il posto non è disponibile o è stato già acquistato
	 */
	public void acquistaPosto() throws PostoNonDisponibileException{
		if (!isDisponibile()) throw new PostoNonDisponibileException("Posto non disponibile");
		if (isAcquistato()) throw new PostoNonDisponibileException("Posto già acquistato");
		occupato = true;
		acquistato = true;
	}
	
	/**
	 * Rende un posto occupato
	 * @throws PostoNonDisponibileException se il posto non è disponibile o è stato già occupato
	 */
	public void occupaPosto() throws PostoNonDisponibileException {
		if (!isDisponibile()) throw new PostoNonDisponibileException("Posto non disponibile!");
		if (occupato) throw new PostoNonDisponibileException("Posto occupato!");
		occupato = true;
	}
	
	/**
	 * Rende un posto libero
	 * @throws PostoNonDisponibileException se il posto è già libero
	 */
	public void liberaPosto() throws PostoNonDisponibileException{
		if (!isOccupato()) throw new PostoNonDisponibileException("Posto già libero!");
		occupato = false;
		acquistato = false;
	}
	
	/**
	 * Rende un posto diponibile
	 */
	public void rendiDisponibile() {
		disponibile = true;
	}
	
	/**
	 * Rende un posto indisponibile
	 */
	public void rendiIndisponibile() {
		disponibile = false;
		occupato = false;
		acquistato = false;
	}
	
	/**
	 * Restituisce la riga del posto
	 * @return riga del posto
	 */
	public char getRiga() {
		return riga;
	}
	
	/**
	 * Restituisce la colonna del posto
	 * @return colonna del posto
	 */
	public int getColonna() {
		return colonna;
	}
	
	/**
	 * Controlla se un posto è occupato
	 * @return true se il posto è occupato, false altrimenti
	 */
	public boolean isOccupato() {
		return occupato;
	}
	
	/**
	 * Controlla se un posto è disponibile
	 * @return true se il posto è disponibile, false altrimenti
	 */
	public boolean isDisponibile() {
		return disponibile;
	}
	
	/**
	 * Controlla se un posto è acquistato
	 * @return true se è acquistato, false altrimenti
	 */
	public boolean isAcquistato() {
		return acquistato;
	}
	
	/**
	 * Effettua una clonazione perfetta del posto
	 */
	public Posto clone() {
		try {
			return (Posto) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Controlla se due posti sono uguali
	 * @return true se i posti sono uguali, false altrimenti
	 */
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Posto p = (Posto) obj;
		return p.riga == riga && p.colonna == colonna && p.disponibile == disponibile && p.occupato == occupato && acquistato == p.acquistato;
	}
	
	/**
	 * Restituisce il posto come stringa nel formato standard
	 */
	public String toString() {
		return getClass().getSimpleName() + "[riga=" + riga + ",colonna=" + colonna + ",occupato=" + occupato + ",dispnibile=" + disponibile + ",acquistato=" + acquistato + "]";
	}
}
