package GestoreProgrammazione;

import java.io.Serializable;

/**
 * Un film è composto da un titolo, una durata e un regista
 * @author MarioELT
 *
 */
public class Film implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String titolo;
	String durata;
	String regista;
	
	/**
	 * Costruisce il film
	 * @param name nome del film
	 * @param duration durata del film
	 * @param producer regista del film
	 */
	public Film (String name, String duration, String producer) {
		titolo = name;
		durata = duration;
		regista = producer;
	}
	
	/**
	 * Restituisce il nome del film
	 * @return nome del film
	 */
	public String getNome() {
		return titolo;
	}
	
	/**
	 * Restituisce la durata del film
	 * @return durata del film
	 */
	public String getDurata() {
		return durata;
	}
	
	/**
	 * Restituisce il regista del film
	 * @return regista del film
	 */
	public String getRegista() {
		return regista;
	}
	
	/**
	 * Restituisce il film sotto forma di stringa
	 */
	public String toString() {
		//return getClass().getSimpleName() + "[nome=" + nome + ",durata=" + durata + ",regista=" + regista + "]";
		return titolo;
	}
}
