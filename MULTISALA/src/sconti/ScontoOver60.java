package sconti;

import multisala.Spettacolo;
import utente.AccountCliente;
import utente.AccountCliente.Gruppo;

/**
 * Sottoclasse di Sconto
 * 
 */
public class ScontoOver60 extends Sconto {

	public ScontoOver60(int percentuale) {
		super("Pensionato", percentuale, "Sconto Pensionati");
	}

	public ScontoOver60(int percentuale, boolean stato) {
		super("Pensionato", percentuale, "Sconto Pensionati", stato);
	}

	@Override
	public boolean applicabile(AccountCliente cliente, Spettacolo spettacolo) {
		return (cliente.getGruppo() == Gruppo.OVER60) ? true : false;
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

	public ScontoOver60 clone() {
		return (ScontoOver60) super.clone();
	}
}
