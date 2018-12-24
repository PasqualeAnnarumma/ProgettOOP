package GestoreProgrammazione;

import java.util.Calendar;
import java.util.GregorianCalendar;

import GestoreSale.Sala;

public class Spettacolo implements Cloneable {
	
	private Sala sala;
	private Film film;
	private Calendar data;
	private String ora;
	private double prezzo;
	int i = 0;
	
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
	
	public Sala getSala() {
		return sala.clone();
	}
	
	public int getOre() {
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
	
	public int getMinuti() {
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
	
	public Film getFilm() {
		return film;
	}
	
	public Calendar getData() {
		return data;
	}
	
	public int getPostiDisponibili() {
		return sala.getPostiDisponibili();
	}
	
	public int getPostiLiberi() {
		int posti = sala.getRighe() * sala.getColonne();
		int postiOccupati = sala.getPostiOccupati();
		return posti - postiOccupati;
	}
	
	public String stringDate() {
		return data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.YEAR);
	}
	
	public String getOra() {
		return ora;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
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
	
	public String toString() {
		return getClass().getSimpleName() + "[sala=" + sala + ",film=" + film + ",ora=" + ora + ",prezzo=" + prezzo + ",data=" + stringDate() + "]";
	}
	
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		Spettacolo s = (Spettacolo) obj;
		return s.sala.equals(sala) && s.film.equals(film) && s.data.equals(data) && s.ora.equals(ora) && s.prezzo == prezzo;
	}
	
	public boolean isPrenotable() {
		Calendar cal = Calendar.getInstance();
		long diff = data.getTimeInMillis() - cal.getTimeInMillis();
		cal.setTimeInMillis(diff);
		if (diff > 43200000) return true;
		return false;
	}
	
	public int compareCalendar(Calendar c1, Calendar c2) {
		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
			if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
				return (c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH));
		
		return -1;
	}
}
