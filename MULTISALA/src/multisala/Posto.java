package multisala;
import java.io.Serializable;

/**
 * Un posto è una poltrona all'interno di una sala, distinta univocamente da un  
 * codice identificativo alfanumerico, che ne individua fila e posizione al suo interno.
 */
public class Posto implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	public static enum Stato { DISPONIBILE, INDISPONIBILE, PRENOTATO, VENDUTO };
	private String idPosto;
	private Stato stato;

	/**
	 * Crea una nuova istanza di Posto con un codice identificativo dato.
	 * @param idPosto
	 */
	public Posto(String idPosto) {
		this.idPosto = idPosto;
		this.stato = Stato.DISPONIBILE;
	}

	/**
	 * Restituisce il codice identificativo del posto.
	 * @return il codice identificativo del posto.
	 */
	public String getIdPosto() {
		return idPosto;
	}

	/**
	 * Restituisce lo stato attuale del posto.
	 * @return lo stato attuale del posto.
	 */
	public Stato getStato() {
		return stato;
	}

	/**
	 * Aggiorna lo stato del posto.
	 * @param stato
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}

	/**
	 * Aggiorna il codice identificativo del posto.
	 * @param idPosto
	 */
	public void setIdPosto(String idPosto) {
		this.idPosto = idPosto;
	}

	/**
	 * Metodi di Object sovrascritti.
	 */
	public String toString() {
		return getClass().getSimpleName() + "[idPosto=" + idPosto + ", stato=" + stato + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		if (otherObject.getClass() != getClass())
			return false;
		Posto obj = (Posto) otherObject;
		return idPosto.equals(obj.idPosto);
	}

	public Posto clone() {
		try {
			return (Posto) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
