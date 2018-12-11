package sconti;

import multisala.Spettacolo;
import utente.AccountCliente;
import utente.AccountCliente.Gruppo;

/**
 * Sottoclasse di Sconto
 * 
 */
public class ScontoStudenti extends Sconto {

	public ScontoStudenti(int percentuale) {
		super("Studente", percentuale, "Sconto studenti");
	}

	public ScontoStudenti(int percentuale, boolean stato) {
		super("Studente", percentuale, "Sconto studenti", stato);
	}

	@Override
	public boolean applicabile(AccountCliente cliente, Spettacolo spettacolo) {
		return (cliente.getGruppo() == Gruppo.STUDENTE) ? true : false;
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

	public ScontoStudenti clone() {
		return (ScontoStudenti) super.clone();
	}
}
