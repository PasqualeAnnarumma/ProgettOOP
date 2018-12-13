package GestorePrenotazioni;

import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;

public class Prenotazione {
	
	private Spettacolo spettacolo;
	Posto posto;
	
	public Prenotazione(Spettacolo show, Posto p) {
		spettacolo = show;
		posto = p;
	}
	
	public String toString() {
		return getClass().getSimpleName() + "[spettacolo=" + spettacolo + ",posto=" + posto + "]";
	}

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Prenotazione p = (Prenotazione) obj;
		return p.spettacolo.equals(spettacolo) && posto.equals(p.posto);
	}
	
	public Posto getPosto() {
		return posto;
	}
	
	public void setPosto(Posto p) {
		posto = p;
	}
}
