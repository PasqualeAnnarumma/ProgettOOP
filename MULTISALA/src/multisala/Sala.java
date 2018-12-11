package multisala;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Una sala è una stanza del cinema nel quale viene proiettato un film.
 * È composta da varie file distinte alfabeticamente e vari posti numerati per ogni fila.
 */
public class Sala implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private int postiPerFila;
	private int numeroFile;
	private int codice;
	private int postiDisponibili;
	private ArrayList<Posto> posti;

	/**
	 * Istanzia una nuova Sala, dati un numero identificativo della sala, il numero di file e il numero di posti per ogni fila.
	 * @param codice
	 * @param postiPerFila
	 * @param numeroFile
	 */
	public Sala(int codice, int postiPerFila, int numeroFile) {
		this.codice = codice;
		this.postiPerFila = postiPerFila;
		this.numeroFile = numeroFile;
		this.postiDisponibili=postiPerFila*numeroFile;
		posti=new ArrayList<Posto>();
		for (int i = 0; i < postiPerFila * numeroFile; i++)
			posti.add(new Posto(generaIdPosti(i)));
	}

	//Metodo privato per generare il codice identificativo di un posto
	private String generaIdPosti(int offset) {
		int x = 'A';
		int y = 1;
		x = x + offset / postiPerFila;
		y = y + offset % postiPerFila;
		Character ch0 = (char) x;
		String str = "";
		str += ch0;
		str += y;
		return str;
	}

	/**
	 * Restituisce il numero delle file all'interno della sala.
	 * @return il numero delle file.
	 */
	public int getNumeroFile() {
		return numeroFile;
	}
	
	/**
	 * Restituisce il numero dei posti per ogni fila.
	 * @return il numero dei posti per ogni fila.
	 */
	public int getPostiPerFila() {
		return postiPerFila;
	}

	/**
	 * Restituisce il numero dei posti totali all'interno della sala.
	 * @return il numero dei posti totali.
	 */
	public int getNumeroPosti() {
		return postiPerFila * numeroFile;
	}

	/**
	 * Restituisce il numero identificativo della sala.
	 * @return il numero identificato della sala.
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * Restituisce la collezione dei posti della sala.
	 * @return la collezione dei posti della sala.
	 */
	public ArrayList<Posto> getPosti() {
		return posti;
	}

	/**
	 * Dato un posto, lo ricerca all'interno della sala e lo restituisce.
	 * @param posto
	 * @return il posto cercato.
	 */
	public Posto cercaPosto(Posto posto) {
		for (int i = 0; i < postiPerFila * numeroFile; i++)
			if (posti.get(i).equals(posto))
				return posti.get(i);
		return null;
	}

	/**
	 * Restituisce il numero di posti disponibili per l'acquisto all'interno della sala.
	 * @return il numero di posti disponibili.
	 */
	public int numeroPostiDisponibili() {
		return postiDisponibili;
	}

	/**
	 * Occupa un posto all'interno della sala.
	 */
	public void occupaPosto() {
		if(postiDisponibili>0)
			postiDisponibili--;
	}

	/**
	 * Libera un posto all'interno della sala.
	 */
	public void liberaPosto() {
		if(postiDisponibili<getNumeroPosti())
			postiDisponibili++;
	}

	/**
	 * Metodi di Object sovrascritti.
	 */
	public String toString() {
		return getClass().getSimpleName() + "[codice=" + codice + ",posti=" + posti + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		if (otherObject.getClass() != getClass())
			return false;
		Sala obj = (Sala) otherObject;
		return codice == obj.codice && posti.equals(posti);
	}

	public Sala clone() {
		try {
			Sala cloned = (Sala) super.clone();
			ArrayList<Posto> clonedPosti = new ArrayList<Posto>();
			for (int i = 0; i < postiPerFila * numeroFile; i++)
				clonedPosti.add(posti.get(i).clone());
			cloned.posti = clonedPosti;
			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
