package GestoreSale;

public class Sala {
	
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
	
	public void inizializzaPosti() {
		for(int i = 0; i < righe; i++)
		{
			for(int j = 0; j < colonne; j++)
			{
				char ch = (char) (i + 65);
				posti[i][j] = new Posto(i, ch);
			}
		}
	}
	
}
