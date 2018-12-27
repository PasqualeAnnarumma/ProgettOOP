package GestoreLogin;

import java.util.Calendar;

/**
 * Il cliente è l'utilizzatore del programma, colui che prenota, acquista o cancella le prenotazioni
 * @author MarioELT
 *
 */
public class Cliente extends Utente {
	
	private static final long serialVersionUID = 1L;

	public enum Categoria {
		NESSUNO(), STUDENTE(), PENSIONATO();
	};
	
	private int eta;
	private String dataNascita;
	private Categoria categoria;
	private int prenotazioni;
	
	/**
	 * Costruisce il cliente
	 * @param usr username del cliente
	 * @param pwd password del cliente
	 * @param birth data di nascita del cliente
	 * @param category categoria del cliente (studente, pensionato, ecc...)
	 */
	public Cliente(String usr, String pwd, String birth, Categoria category) {
		super(usr, pwd);
		dataNascita = birth;
		prenotazioni = 0;
		categoria = category;
		calcolaEta(birth);
	}
	
	/**
	 * Calola l'età del cliente
	 * @param birth
	 */
	public void calcolaEta(String birth) {
		Calendar today = Calendar.getInstance();
		int giorno = Integer.parseInt(dataNascita.substring(0, 2));
		int mese = Integer.parseInt(dataNascita.substring(3, 5));
		int anno = Integer.parseInt(dataNascita.substring(6, 10));
		int annoOggi = today.get(Calendar.YEAR);
		int meseOggi = today.get(Calendar.MONTH) + 1;
		int giornoOggi = today.get(Calendar.DAY_OF_MONTH);
		//System.out.println(giornoOggi + "/" + meseOggi + "/" + annoOggi);
		eta = annoOggi - anno;
		if (meseOggi < mese) eta -= 1;
		else if (giornoOggi < giorno) eta -= 1;
		//System.out.println(giorno + "/" + mese + "/" + anno + " --- " + eta);
	}
	
	/**
	 * Restituisce la categoria del cliente
	 * @return categoria del cliente
	 */
	public Categoria getCategoria() {
		return categoria;
	}
	
	/**
	 * restituisce l'età del cliente
	 * @return età del cliente
	 */
	public int getEta() {
		return eta;
	}
	
	/**
	 * restituisce la data di nascita
	 * @return data di nascita del cliente
	 */
	public String getDataNascita() {
		return dataNascita;
	}
	
	/**
	 * Controlla se l'utente ha già usufruito dello sconto
	 * @return true se ne ha già usufruito, false altrimenti
	 */
	public boolean haUsatoSconto() {
		if (prenotazioni == 0) return false;
		return true;
	}
	
	/**
	 * Incrementa il numero di prenotazioni effettuate dal cliente
	 */
	public void addPrenotazione() {
		prenotazioni++;
	}
	
	/**
	 * Decrementa il numero di prenotazioni effettuate dal cliente.
	 * Questo valore non può mai scendere sotto lo 0
	 */
	public void removePrenotazione() {
		if (prenotazioni > 0) prenotazioni--;
	}
}
