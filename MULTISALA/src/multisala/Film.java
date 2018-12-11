package multisala;
import java.io.Serializable;

/**
 * Un film è composto da un titolo, una descrizione, una durata in minuti e un prezzo.
 */
public class Film implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private String titolo;
	private String descrizione;
	private int durata; // in minuti
	private double prezzo;
	private double incassoFilm; // incasso settimanale del film.

	/**
	 * Crea una nuova istanza di Film con dei vari parametri.
	 * @param titolo Il titolo del film.
	 * @param descrizione Una breve descrizione del film.
	 * @param durata La durata del film in minuti.
	 * @param prezzo Il prezzo standard per la visione del film.
	 */
	public Film(String titolo, String descrizione, int durata, double prezzo) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.durata = durata;
		this.prezzo = prezzo;
	}

	/**
	 * Restituisce il titolo del film.
	 * @return il titolo del film.
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * Restituisce la descrizione del film.
	 * @return la descrizione del film.
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Restituisce la durata in minuti del film.
	 * @return la durata del film
	 */
	public int getDurata() {
		return durata;
	}

	/**
	 * Restituisce il prezzo standard del film.
	 * @return il prezzo del film.
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Restituisce l'incasso attuale del film nel cinema.
	 * @return l'incasso attuale.
	 */
	public double getIncassoFilm() {
		return incassoFilm;
	}

	/**
	 * Aggiorna il titolo del film.
	 * @param titolo
	 */
	public void setNome(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Aggiorna la descrizione del film.
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Aggiorna il prezzo del film.
	 * @param prezzo
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Riporta a zero l'incasso del film.
	 */
	public void resetIncassoFilm() {
		this.incassoFilm = 0;
	}

	/**
	 * Aggiunge il prezzo di un biglietto venduto all'incasso del film.
	 * @param prezzo
	 */
	public void addIncassoFilm(double prezzo) {
		this.incassoFilm += prezzo;
	}

	/**
	 * Metodi di Object sovrascritti.
	 */
	public String toString() {
		return getClass().getSimpleName() + "[titolo=" + titolo + ", descrizione=" + descrizione + ", durata=" + durata
				+ ", prezzo=" + prezzo + ", incassoFilm=" + incassoFilm + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		if (otherObject.getClass() != getClass())
			return false;
		Film obj = (Film) otherObject;
		return titolo.equals(obj.titolo) && descrizione.equals(obj.descrizione) && durata == obj.durata
				&& prezzo == obj.prezzo;
	}

	public Film clone() {
		try {
			return (Film) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
