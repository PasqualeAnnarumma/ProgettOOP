package multisala;
import java.io.Serializable;
import java.util.ArrayList;
import interfacce.Comparatore;
import interfacce.Selettore;
import utils.Ordinatore;

/**
 * Contiene una lista degli spettacoli settimanali del multisala.
 */
public class Programmazione implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Spettacolo> spettacoli;

	/**
	 * Crea una nuova istanza di Programmazione.
	 */
	public Programmazione() {
		spettacoli = new ArrayList<Spettacolo>();
	}

	/**
	 * Dato uno spettacolo, lo ricerca all'interno della programmazione e lo restituisce.
	 * @param spettacolo
	 * @return lo spettacolo cercato.
	 */
	public Spettacolo cercaSpettacolo(Spettacolo spettacolo) {
		for (int i = 0; i < spettacoli.size(); i++)
			if (spettacoli.get(i).equals(spettacolo))
				return spettacoli.get(i);
		return null;
	}
	/**
	 * Restituisce la lista degli spettacolo programmati in settimana.
	 * @return la lista degli spettacoli nel programma settimanale.
	 */
	public ArrayList<Spettacolo> getProgrammazioneSettimanale(){
		return spettacoli;
	}
	/**
	 * Cerca spettacoli in base ad un criterio dato.
	 * @param criterio
	 */
	public ArrayList<Spettacolo> cercaSpettacoliCriterio(Selettore<Spettacolo> criterio) {
		ArrayList<Spettacolo> spettacoliSelezionati = new ArrayList<Spettacolo>();
		for (int i = 0; i < spettacoli.size(); i++)
			if (criterio.seleziona(spettacoli.get(i)))
				spettacoliSelezionati.add(spettacoli.get(i));
		return spettacoliSelezionati;
	}

	/**
	 * Aggiunge uno spettacolo alla programmazione.
	 * @param spettacolo
	 */
	public void aggiungiSpettacolo(Spettacolo spettacolo) {
		spettacoli.add(spettacolo);
	}

	/**
	 * Rimuove uno spettacolo dalla programmazione.
	 * @param spettacolo
	 */
	public void rimuoviSpettacolo(Spettacolo spettacolo) {
		spettacoli.remove(spettacolo);
	}

	/**
	 * Ordina gli spettacoli in base ad un criterio dato.
	 * @param criterio
	 */
	public ArrayList<Spettacolo> ordinaSpettacoli(Comparatore<Spettacolo> criterio) {
		Ordinatore.ordina(spettacoli, criterio);
		//forse ci vuole il clone.
		return spettacoli;
	}

	/**
	 * Metodi di Object sovrascritti.
	 */
	public String toString() {
		return getClass().getSimpleName() + "[spettacoli=" + spettacoli + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		if (otherObject.getClass() != getClass())
			return false;
		Programmazione obj = (Programmazione) otherObject;
		return spettacoli.equals(obj.spettacoli);
	}

	public Programmazione clone() {
		try {
			ArrayList<Spettacolo> clonedSpettacoli = new ArrayList<Spettacolo>();
			Programmazione cloned = (Programmazione) super.clone();
			for (int i = 0; i < spettacoli.size(); i++)
				clonedSpettacoli.add(spettacoli.get(i).clone());
			cloned.spettacoli = clonedSpettacoli;
			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
