package GestoreProgrammazione;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import GestoreSale.Sala;

/**
 * Uno spettacolo è composto da un film e una sala e contiene altre informazioni come la data,
 * il prezzo e l'ora
 * @author MarioELT
 *
 */
public class Spettacolo implements Cloneable, Serializable{
	
	private static final long serialVersionUID = 1L;
	private Sala sala;
	private Film film;
	private Calendar data;
	private String ora;
	private double prezzo;
	int i = 0;
	
	/**
	 * Costruisce lo spettacolo
	 * @param room sala dello spettacolo
	 * @param movie film dello spettacolo
	 * @param gg giorno di svolgimento
	 * @param mm mese di svolgimento
	 * @param yy anno di svolgimento
	 * @param hour ora di inizio
	 * @param price prezzo
	 */
	public Spettacolo(Sala room, Film movie, int gg, int mm, int yy, String hour, double price) {
		sala = new Sala(room.getNumeroSala(), room.getRighe(), room.getColonne());
		film = movie;
		data = new GregorianCalendar();
		ora = hour;
		data = Calendar.getInstance();
		data.set(Calendar.HOUR_OF_DAY, getOre());
		data.set(Calendar.MINUTE, getMinuti());
		data.set(Calendar.SECOND, 0);
		data.set(Calendar.YEAR, yy);
		data.set(Calendar.DAY_OF_MONTH, gg);
		data.set(Calendar.MONTH, mm-1);
		prezzo = price;
	}
	
	/**
	 * Restituisce la sala dello spettacolo
	 * @return sala dello spettacolo
	 */
	public Sala getSala() {
		return sala.clone();
	}
	
	/**
	 * Restituisce l'intero corrispondente alle ore dell'ora di inizio
	 * @return campo ore dell'ora di inizio
	 */
	private int getOre() {
		int hh = (Integer.parseInt(ora.charAt(i) + "") * 10);
		i++;
		char ch = ora.charAt(i);
		int mm = 0;
		if (ch != ':')
		{
			mm = (Integer.parseInt(ch + ""));
			i += 2;
			return hh + mm;
		}
		i++;
		return hh + mm;
	}
	
	/**
	 * Restituisce l'intero corrispondente ai minuti dell'ora di inizio
	 * @return campo minuti dell'ora di inizio
	 */
	private int getMinuti() {
		int hh = (Integer.parseInt(ora.charAt(i) + "") * 10);
		i++;
		int mm = 0;
		if (ora.length() == 5)
		{
			char ch = ora.charAt(i);
			mm = (Integer.parseInt(ch + ""));
			return hh + mm;
		}
		//int mm = (Integer.parseInt(ora.charAt(i) + ""));
		return hh + mm;
	}
	
	/**
	 * Restituisce il film dello spettacolo
	 * @return film dello spettacolo
	 */
	public Film getFilm() {
		return film;
	}
	
	/**
	 * Restituisce la data dello spettacolo
	 * @return data dello spettacolo
	 */
	public Calendar getData() {
		return data;
	}
	
	/**
	 * Restituisce il numero di posti disponibili nella sala dello spettacolo
	 * @return numero di posti disponibili nella sala dello spettacolo
	 */
	public int getPostiDisponibili() {
		return sala.getPostiDisponibili();
	}
	
	/**
	 * Restituisce il numero di posti liberi nella sala dello spettacolo
	 * @return numero di posti liberi nella sala dello spettacolo
	 */
	public int getPostiLiberi() {
		int posti = sala.getRighe() * sala.getColonne();
		int postiOccupati = sala.getPostiOccupati();
		return posti - postiOccupati;
	}
	
	/**
	 * Restituisce la data sotto forma di stringa nel formato gg/mm/yyyy
	 * @return la stringa della data
	 */
	public String stringDate() {
		return data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.YEAR);
	}
	
	/**
	 * Restituisce l'ora di inizio
	 * @return ora di inizio
	 */
	public String getOra() {
		return ora;
	}
	
	/**
	 * Restituisce il prezzo
	 * @return prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}
	
	/**
	 * Effettua una clonazione perfetta
	 */
	public Spettacolo clone() {
		try {
			Spettacolo clone = (Spettacolo) super.clone();
			clone.sala = sala.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Restituisce la stringa dello spettacolo nel formato standard
	 * @return stringa nel formato standard dello spettacolo
	 */
	public String toString() {
		return getClass().getSimpleName() + "[sala=" + sala + ",film=" + film + ",ora=" + ora + ",prezzo=" + prezzo + ",data=" + stringDate() + "]";
	}
	
	/**
	 * Controlla se due spettacoli sono uguali
	 * @return true se sono uguali, false altrimenti
	 */
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		Spettacolo s = (Spettacolo) obj;
		return s.sala.equals(sala) && s.film.equals(film) && s.data.equals(data) && s.ora.equals(ora) && s.prezzo == prezzo;
	}
	
	/**
	 * Controlla se è possibile prenotare un posto per lo spettacolo, cioè se inizia tra più di 12 ore
	 * @return true se è prenotabile, cioè se inizia tra più di 12 ore, false altrimenti
	 */
	public boolean isPrenotable() {
		Calendar cal = Calendar.getInstance();
		long diff = data.getTimeInMillis() - cal.getTimeInMillis();
		cal.setTimeInMillis(diff);
		//43200000 millisecondi equivalgono a 12 ore
		if (diff > 43200000) return true;
		return false;
	}
	
	/**
	 * Confronta due Calendar
	 * @param c1 primo Calendar
	 * @param c2 secondo Calendar
	 * @return un intero maggiore o uguale a 0 se la data del primo calendar è maggiore o uguale a quella del secondo, un valore negativo altrimenti
	 */
	public int compareCalendar(Calendar c1, Calendar c2) {
		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
			if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
				return (c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH));
		
		return -1;
	}
}
