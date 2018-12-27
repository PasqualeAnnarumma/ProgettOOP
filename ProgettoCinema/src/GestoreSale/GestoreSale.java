package GestoreSale;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Il gestoreSale è il gestore che si occupa di aggiungere o rimuovere le sale
 * @author MarioELT
 *
 */
public class GestoreSale implements Serializable{
	
	private static final long serialVersionUID = 1L;
	ArrayList<Sala> listaSale;
	
	/**
	 * Costruisce il gestore delle sale
	 */
	public GestoreSale() {
		listaSale = new ArrayList<Sala>();
	}
	
	/**
	 * Aggiunge una sala nella lista delle sale
	 * @param rows numero di righe della sala
	 * @param columns numero di colonne
	 */
	public void aggiungiSala(int rows, int columns) {
		listaSale.add(new Sala(listaSale.size(), rows, columns));
	}
	
	/**
	 * Rimuove una sala dalla lista delle sale
	 * @param sala sala da rimuovere
	 */
	public void rimuoviSala(Sala sala) {
		listaSale.remove(sala);
	}
	
	/**
	 * Restituisce la lista delle sale
	 * @return lista delle sale
	 */
	public ArrayList<Sala> getListaSale() {
		return listaSale;
	}
	
	/**
	 * Restituice la sala corrispondente all'indice i nella lista delle sale
	 * @param i indice
	 * @return sala contenuta nell'indice i della lista delle sale
	 */
	public Sala getSala(int i) {
		return listaSale.get(i).clone();
	}
	
	/**
	 * Restituisce la grandezza della lista delle sale (numero di sale)
	 * @return grandezza lista delle sale
	 */
	public int size() {
		return listaSale.size();
	}
}
