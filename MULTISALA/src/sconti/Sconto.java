package sconti;

import multisala.Spettacolo;
import utente.AccountCliente;

/**
 * Classe Sconto astratta, racchiude il concetto di sconto
 *
 */
public abstract class Sconto implements Cloneable {
	private String nome;
	private String descrizione;
	private boolean stato;
	private int percentualeSconto;

	/**
	 * Costruttore di sconto
	 * 
	 * @param nome        dello sconto
	 * @param percentuale di sconto come intero tra 0 e 100
	 * @param descrizione dello sconto
	 */
	public Sconto(String nome, int percentuale, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.percentualeSconto = (percentuale < 0 ) ? 0 : (percentuale>100) ? 100 : percentuale;
	}

	/**
	 * Costruttore di sconto avente stato dello sconto che può essere settato attivo
	 * o non attivo
	 * 
	 * @param nome        dello sconto
	 * @param percentuale dello sconto come intero tra 0 e 100
	 * @param descrizione dello sconto
	 * @param stato       dello sconto(Attivo o Disattivo)
	 */
	public Sconto(String nome, int percentuale, String descrizione, boolean stato) {
		this.nome = nome;
		this.stato = stato;
		this.descrizione = descrizione;
		this.percentualeSconto = (percentuale < 0 ) ? 0 : (percentuale>100) ? 100 : percentuale; //Controllo del range
	}

	/**
	 * Controlla che lo sconto risulti applicabile o meno
	 * 
	 * @param cliente
	 * @param spettacolo
	 * @return true se lo sconto è applicabile, false altrimenti
	 */
	public abstract boolean applicabile(AccountCliente cliente, Spettacolo spettacolo);

	/**
	 * Ritorna il prezzo scontato in base allo sconto
	 * 
	 * @param prezzo
	 * @return prezzo con sconto applicato
	 */
	public double getsconto(double prezzo) {
		return prezzo - prezzo * percentualeSconto/100;
	}

	/**
	 * Ritorna nome dello sconto
	 * 
	 * @return nome dello sconto
	 */
	public String getNomeSconto() {
		return nome;
	}

	/**
	 * Ritorna la descrizione dello sconto
	 * 
	 * @return descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Ritorna lo stato attuale dello sconto
	 * 
	 * @return valore booleano
	 */
	public boolean getStato() {
		return stato;
	}

	/**
	 * Attiva lo stato dello sconto: pone a true la variabile stato
	 */
	public void attiva() {
		stato = true;
	}

	/**
	 *	Disattiva lo stato dello sconto: pone a false la variabile stato
	 */
	public void disattiva() {
		stato = false;
	}

	// Metodi Object
	public String toString() {
		return getClass().getSimpleName() + "[nome=" + nome + ",descrizione=" + descrizione + ",percentualeSconto="
				+ percentualeSconto + ",stato=" + stato + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || otherObject.getClass() != getClass())
			return false;
		Sconto other = (Sconto) otherObject;
		return nome.equalsIgnoreCase(other.nome) && descrizione.equalsIgnoreCase(other.descrizione)
				&& percentualeSconto == other.percentualeSconto;
	}

	public Sconto clone() {
		try {
			return (Sconto) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
