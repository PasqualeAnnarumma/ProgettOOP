package GestoreProgrammazione;

import java.util.Calendar;

import GestoreSale.Sala;

public class Spettacolo {
	
	Sala sala;
	Film film;
	Calendar data;
	String ora;
	double prezzo;
	
	public Spettacolo(Sala room, Film movie, int gg, int mm, int yy, String hour, double price) {
		sala = room;
		film = movie;
		data = Calendar.getInstance();
		data.set(yy, mm-1, gg);;
		ora = hour;
		prezzo = price;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public Film getFilm() {
		return film;
	}
	
	public Calendar getData() {
		return data;
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
}
