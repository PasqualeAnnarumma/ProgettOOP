package GestoreSale;

import java.util.ArrayList;

public class Sala implements Cloneable{
	
	int numeroSala;
	int righe;
	int colonne;
	Posto posti[][];
	
	public Sala(int n, int rows, int columns) {
		numeroSala = n;
		righe = rows;
		colonne = columns;
		posti = new Posto[righe][colonne];
		inizializzaPosti();
	}
	
	public ArrayList<Posto> getListaPostiIndisponibili() {
		ArrayList<Posto> lista = new ArrayList<Posto>();
		for (int i = 0; i < righe; i++)
			for (int j = 0; j < colonne; j++)
				if (posti[i][j].isDisponibile() == false) lista.add(posti[i][j]);
		return lista;
	}
	
	public void printPosti() {
		for (int i = 0; i < righe; i++)
			for (int j = 0; j < colonne; j++)
				System.out.println(posti[i][j]);
	}
	
	public void setPosti(Posto posti[][]) {
		for (int i = 0; i < righe; i++)
			for (int j = 0; j < colonne; j++)
				this.posti[i][j] = posti[i][j];
	}
	
	public int getNumeroSala() {
		return numeroSala;
	}
	
	public Posto[][] getPosti() {
		return posti.clone();
	}
	
	public Posto getPosto(int riga, int colonna) {
		return posti[riga][colonna].clone();
	}
	
	public Posto getPurePosto(int riga, int colonna) {
		return posti[riga][colonna];
	}
	
	public int getRighe() {
		return righe;
	}
	
	public int getColonne() {
		return colonne;
	}
	
	public void inizializzaPosti() {
		for(int i = 0; i < righe; i++)
		{
			char ch = (char) (i + 65);
			for(int j = 0; j < colonne; j++)
				posti[i][j] = new Posto(ch, j);
		}
	}
	
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
	
	public String toString() {
		//return getClass().getSimpleName() + "[numeroSala=" + numeroSala + ",righe=" + righe + ",colonne=" + colonne + "]";
		return numeroSala + "";
	}
	
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		Sala s = (Sala) obj;
		return s.numeroSala == numeroSala && s.righe == righe && s.colonne == colonne && confrontaPosti(s.getPosti());
	}
	
	public boolean confrontaPosti(Posto[][] p) {
		if (posti.length != p.length) return false;
		for (int i = 0; i < righe; i++)
		{
			for (int j = 0; j < colonne; j++)
				if (!posti[i][j].equals(p[i][j])) return false;
		}
		return true;
	}
	
	public int getPostiOccupati() {
		int count = 0;
		for (int i = 0; i < righe; i++) 
		{
			for (int j = 0; j < colonne; j++)
				if (posti[i][j].isOccupato()) count++;
		}
		return count;
	}
	
	public int getPostiDisponibili() {
		int count = 0;
		for (int i = 0; i < righe; i++) 
		{
			for (int j = 0; j < colonne; j++)
				if (posti[i][j].isDisponibile()) count++;
		}
		return count;
	}
	
	public int getPostiTotali() {
		return righe * colonne;
	}
	
}
