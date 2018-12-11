package sconti;

import java.time.DayOfWeek;

import multisala.Spettacolo;
import utente.AccountCliente;

/**
 * Sottoclasse di Sconto
 * 
 */
public class ScontoMercoledi extends Sconto {

	public ScontoMercoledi(int percentuale) {
		super("Mercoledì", percentuale, "Sconto settimanale");
	}

	public ScontoMercoledi(int percentuale, boolean stato) {
		super("Mercoledì", percentuale, "Sconto settimanale", stato);
	}

	@Override
	public boolean applicabile(AccountCliente cliente, Spettacolo spettacolo) {
		return (spettacolo.getData().getDayOfWeek() == DayOfWeek.WEDNESDAY ) ? true : false;
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

	public ScontoMercoledi clone() {
		return (ScontoMercoledi) super.clone();
	}
}
