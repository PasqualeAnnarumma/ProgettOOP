package GestoreSale;

import java.util.ArrayList;

public class GestoreSale {
	
	ArrayList<Sala> listaSale;
	
	public GestoreSale() {
		listaSale = new ArrayList<Sala>();
	}
	
	public void aggiungiSala(int rows, int columns) {
		listaSale.add(new Sala(listaSale.size(), rows, columns));
	}
	
	public void rimuoviSala(Sala s) {
		listaSale.remove(s);
	}
	
	public ArrayList<Sala> getListaSale() {
		return listaSale;
	}
	
	public Sala getSala(int i) {
		return listaSale.get(i).clone();
	}
	
	public int size() {
		return listaSale.size();
	}
}
