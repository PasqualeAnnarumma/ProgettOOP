package sconti;

import multisala.Spettacolo;
import utente.AccountCliente;
import utente.AccountCliente.Gruppo;

/**
 * Sottoclasse di Sconto
 * 
 */
public class ScontoUnder14 extends Sconto {

	public ScontoUnder14(int percentuale) {
		super("Bambino", percentuale, "Sconto Bambini");
	}

	public ScontoUnder14(int percentuale, boolean stato) {
		super("Bambino", percentuale, "Sconto Bambini", stato);
	}

	/**
	 * Metodo astratto sovrascritto
	 */
	public boolean applicabile(AccountCliente cliente, Spettacolo spettacolo) {
		return (cliente.getGruppo() == Gruppo.UNDER14) ? true : false;
	}

	// Metodi Object
	public String toString() {
		return super.toString() + "[]";
	}

	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject))
			return false;
		return true;
	}

	public ScontoUnder14 clone() {
		return (ScontoUnder14) super.clone();
	}
}
