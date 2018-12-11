package GestoreProgrammazione;

import java.util.ArrayList;

public class ProgrammaSettimanale {

	ArrayList<Spettacolo> listaSpettacoli;
	
	public ProgrammaSettimanale() {
		listaSpettacoli = new ArrayList<Spettacolo>();
	}
	
	public void aggiungiSpettacolo(Spettacolo show) {
		listaSpettacoli.add(show);
	}
	
	public void rimuoviSpettacolo(Spettacolo show) {
		listaSpettacoli.remove(show);
	}
	
	public ArrayList<Spettacolo> getListaSpettacoli() {
		return listaSpettacoli;
	}
	
	public Spettacolo getSpettacolo(int i) {
		return listaSpettacoli.get(i);
	}
	
	public int size() {
		return listaSpettacoli.size();
	}
	
}
