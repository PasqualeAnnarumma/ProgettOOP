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
		return posti;
	}
	
	public Posto getPosto(int i, int j) {
		return posti[i][j];
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
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
