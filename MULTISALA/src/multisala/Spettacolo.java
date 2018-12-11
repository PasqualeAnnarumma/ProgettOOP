package multisala;
import java.io.Serializable;
import java.time.LocalDateTime;

/** 
 * Uno spettacolo è un evento nel quale viene proiettato un film in una sala.
 */
public class Spettacolo implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private Film film;
	private Sala sala;
	private LocalDateTime data; //data e ora di inizio dello spettacolo

	/**
	 * Crea una nuova istanza di spettacolo, dati un film, una sala e una data.
	 * @param film
	 * @param sala
	 * @param data
	 */
	public Spettacolo(Film film, Sala sala, LocalDateTime data) {
		this.film = film;
		this.sala = sala.clone();
		this.data = data;
	}

	/**
	 * Restituisce il film proiettato durante lo spettacolo.
	 * @return il film proiettato.
	 */
	public Film getFilm() {
		return film;
	}

	/**
	 * Restituisce il numero della sala nel quale si tiene lo spettacolo.
	 * @return il numero della sala.
	 */
	public Sala getSala() {
		return sala;
	}	
	
	/**
	 * Restituisce la data e l'orario di inizio dello spettacolo.
	 * @return la data e l'orario di inizio dello spettacolo.
	 */
	public LocalDateTime getData() {
		return data;
	}
	
	public int getDurata()
	{
		return film.getDurata();
	}
	
	/**
	 * Aggiorna il film proiettato durante lo spettacolo.
	 * @param film
	 */
	public void setFilm(Film film) {
		this.film = film;
	}


	/**
	 * Aggiorna la sala nel quale verrà proiettato lo spettacolo.
	 * @param sala
	 */
	public void setSala(Sala sala) {
		this.sala = sala.clone();
	}

	/**
	 * Aggiorna la data dello spettacolo
	 * @param data
	 */
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	

	/**
	 * Metodi di Object sovrascritti.
	 */
	public String toString() {
		return getClass().getSimpleName() + "[film=" + film + ", sala=" + sala + ", data=" + data + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		if (otherObject.getClass() != getClass())
			return false;
		Spettacolo obj = (Spettacolo) otherObject;
		return film.equals(obj.film) && sala.equals(obj.sala) && data.equals(obj.data);
	}

	public Spettacolo clone() {
		try {
			Spettacolo cloned = (Spettacolo) super.clone();
			cloned.film = film.clone();
			cloned.sala = sala.clone();
			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
