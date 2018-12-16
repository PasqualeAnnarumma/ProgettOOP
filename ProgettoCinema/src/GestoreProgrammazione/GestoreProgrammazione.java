package GestoreProgrammazione;

import java.util.ArrayList;

public class GestoreProgrammazione {
	
	ArrayList<Spettacolo> listaSpettacoli;
	
	public GestoreProgrammazione() {
		listaSpettacoli = new ArrayList<Spettacolo>();
	}
	
	public void aggiungiSpettacolo(Spettacolo spettacolo) {
		listaSpettacoli.add(spettacolo);
	}
	
	public void rimuoviSpettacolo(Spettacolo spettacolo) {
		listaSpettacoli.remove(spettacolo);
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
