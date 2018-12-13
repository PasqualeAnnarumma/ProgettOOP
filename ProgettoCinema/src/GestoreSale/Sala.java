package GestoreSale;

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
	
	public int getNumeroSala() {
		return numeroSala;
	}
	
	public Posto[][] getPosti() {
		return posti.clone();
	}
	
	public Posto getPosto(int i, int j) {
		return posti[i][j].clone();
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
			Sala clone = (Sala) super.clone();
			clone.posti = posti.clone();
			for (int i = 0; i < righe; i++)
			{
				for (int j = 0; j < colonne; j++)
					clone.posti[i][j] = posti[i][j].clone();
			}
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString() {
		return getClass().getSimpleName() + "[numeroSala=" + numeroSala + ",righe=" + righe + ",colonne=" + colonne + "]";
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
	
}
