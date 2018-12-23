package GestoreProgrammazione;

import java.util.ArrayList;

public class GestoreProgrammazione {
	
	ArrayList<Spettacolo> listaSpettacoli;
	ArrayList<Film> listaFilm;
	
	public GestoreProgrammazione() {
		listaSpettacoli = new ArrayList<Spettacolo>();
		listaFilm = new ArrayList<Film>();
	}
	
	public void aggiungiSpettacolo(Spettacolo spettacolo) {
		listaSpettacoli.add(spettacolo);
		if (!cercaFilm(spettacolo.getFilm()))
			listaFilm.add(spettacolo.getFilm());
	}
	
	public void rimuoviSpettacolo(Spettacolo spettacolo) {
		listaSpettacoli.remove(spettacolo);
	}
	
	public ArrayList<Spettacolo> getListaSpettacoli() {
		return listaSpettacoli;
	}
	
	public ArrayList<Film> getListaFilm() {
		return listaFilm;
	}
	
	public Spettacolo getSpettacolo(int i) {
		return listaSpettacoli.get(i);
	}
	
	public Film getFilm(int i) {
		return listaFilm.get(i);
	}
	
	public int size() {
		return listaSpettacoli.size();
	}
	
	public boolean cercaFilm(Film movie) {
		for (int i = 0; i < listaFilm.size(); i++)
			if (listaFilm.get(i).equals(movie)) return true;
		return false;
	}
	
}
