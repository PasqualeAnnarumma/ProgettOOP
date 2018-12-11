package GestoreProgrammazione;

import GestoreSale.Sala;

public class Spettacolo {
	
	Sala sala;
	Film film;
	String data;
	String ora;
	double prezzo;
	
	public Spettacolo(Sala room, Film movie, String date, String hour, double price) {
		sala = room;
		film = movie;
		data = date;
		ora = hour;
		prezzo = price;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public Film getFilm() {
		return film;
	}
	
	public String getData() {
		return data;
	}
	
	public String getOra() {
		return ora;
	}
}
