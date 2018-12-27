package GestoreSale;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Una sala rappresenta la sala fisica all'interno del cinema
 * @author MarioELT
 *
 */
public class Sala implements Cloneable, Serializable{
	
	private static final long serialVersionUID = 1L;
	int numeroSala;
	int righe;
	int colonne;
	Posto posti[][];
	
	/**
	 * Costruisce la sala
	 * @param n numero di sala
	 * @param rows righe della sala
	 * @param columns colonne della sala
	 */
	public Sala(int n, int rows, int columns) {
		numeroSala = n;
		righe = rows;
		colonne = columns;
		posti = new Posto[righe][colonne];
		inizializzaPosti();
	}
	
	/**
	 * Restituisce la lista dei posti indisponibili
	 * @return lista dei posti indisponibili
	 */
	public ArrayList<Posto> getListaPostiIndisponibili() {
		ArrayList<Posto> lista = new ArrayList<Posto>();
		for (int i = 0; i < righe; i++)
			for (int j = 0; j < colonne; j++)
				if (posti[i][j].isDisponibile() == false) lista.add(posti[i][j]);
		return lista;
	}
	
	/*public void printPosti() {
		for (int i = 0; i < righe; i++)
			for (int j = 0; j < colonne; j++)
				System.out.println(posti[i][j]);
	}*/
	
	/**
	 * Effettua una clonazione dei posti
	 * @param posti posti da clonare
	 */
	public void setPosti(Posto posti[][]) {
		for (int i = 0; i < righe; i++)
			for (int j = 0; j < colonne; j++)
				this.posti[i][j] = posti[i][j];
	}
	
	/**
	 * Restituisce il numero di sala
	 * @return numero di sala
	 */
	public int getNumeroSala() {
		return numeroSala;
	}
	
	/**
	 * Restituisce la matrice di posti
	 * @return matrice di posti
	 */
	public Posto[][] getPosti() {
		return posti.clone();
	}
	
	/**
	 * Restituisce un clone del posto specificato dalla riga e dalla colonna
	 * @param riga riga del posto
	 * @param colonna colonna del posto
	 * @return clone del posto specificato
	 */
	public Posto getPosto(int riga, int colonna) {
		return posti[riga][colonna].clone();
	}
	
	/**
	 * Restituisce il reale posto, e non il clone, specificato dal numero di riga e di colonna
	 * @param riga numero di riga
	 * @param colonna numero di colonna
	 * @return reale posto, e non il clone, specificato
	 */
	public Posto getPurePosto(int riga, int colonna) {
		return posti[riga][colonna];
	}
	
	/**
	 * Restituisce il numero di righe della sala
	 * @return numero di righe
	 */
	public int getRighe() {
		return righe;
	}
	
	/**
	 * Restituisce il numero di colonne della sala
	 * @return numero di colonne
	 */
	public int getColonne() {
		return colonne;
	}
	
	/**
	 * Inizializza tutti i posti della sala come nuovi
	 */
	public void inizializzaPosti() {
		for(int i = 0; i < righe; i++)
		{
			char ch = (char) (i + 65);
			for(int j = 0; j < colonne; j++)
				posti[i][j] = new Posto(ch, j);
		}
	}
	
	/**
	 * Effettua una clonazione perfetta della sala
	 */
	public Sala clone() {
		try {
			Sala clon = (Sala) super.clone();
			clon.posti = posti.clone();
			for (int i = 0; i < righe; i++)
			{
				for (int j = 0; j < colonne; j++)
					clon.posti[i][j] = posti[i][j].clone();
			}
			return clon;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Restituisce la sala come stringa nel formato standard
	 */
	public String toString() {
		//return getClass().getSimpleName() + "[numeroSala=" + numeroSala + ",righe=" + righe + ",colonne=" + colonne + "]";
		return numeroSala + "";
	}
	
	/**
	 * Controlla se due sale sono uguali
	 * @return true se sono uguali, false altrimenti
	 */
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		Sala s = (Sala) obj;
		return s.numeroSala == numeroSala && s.righe == righe && s.colonne == colonne && confrontaPosti(s.getPosti());
	}
	
	/**
	 * Controlla se la matrice di posti della sala è uguale a quella che viene passata
	 * @param p matrice di posti da confrontare
	 * @return true se sono uguali, false altrimenti
	 */
	public boolean confrontaPosti(Posto[][] p) {
		if (posti.length != p.length) return false;
		for (int i = 0; i < righe; i++)
		{
			for (int j = 0; j < colonne; j++)
				if (!posti[i][j].equals(p[i][j])) return false;
		}
		return true;
	}
	
	/**
	 * Restituisce il numero di posti occupati
	 * @return numero di posti occupati
	 */
	public int getPostiOccupati() {
		int count = 0;
		for (int i = 0; i < righe; i++) 
		{
			for (int j = 0; j < colonne; j++)
				if (posti[i][j].isOccupato()) count++;
		}
		return count;
	}
	
	/**
	 * Restituisce il numero di posti disponibili
	 * @return numero posti dsponibili
	 */
	public int getPostiDisponibili() {
		int count = 0;
		for (int i = 0; i < righe; i++) 
		{
			for (int j = 0; j < colonne; j++)
				if (posti[i][j].isDisponibile()) count++;
		}
		return count;
	}
	
	/**
	 * Restituisce il numero di posti totali
	 * @return numero posti totali
	 */
	public int getPostiTotali() {
		return righe * colonne;
	}
	
}
