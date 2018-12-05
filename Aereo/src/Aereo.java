import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author MarioELT
 * Astrazione di Aereo che contiene le informazioni riguardanti i posti totali,
 * i posti liberi, e i posti occupati
 */
public class Aereo implements Serializable {
	
	boolean posti[][];
	int mode;
	int file;
	int num;
	
	public Aereo(int file, int num) {
		
		this.file = file;
		this.num = num;
		
		posti = new boolean[file][num];
		
	}
	
	/**
	 * 
	 * @param i indice di riga del posto
	 * @param j indice di colonna del posto
	 * @param mode bit di modalità
	 * @throws WrongActionException eccezione
	 */
	public void esegui(int i, int j, int mode) throws WrongActionException {
		
		this.mode = mode;
		
		if (mode == 1 && check(i, j))
			throw new WrongActionException("Posto già prenotato!");
		else if (mode == 0 && !check(i, j))
			throw new WrongActionException("Posto non prenotato!");
		else if (mode == 1)
			posti[i][j] = true;
		else if (mode == 0)
			posti[i][j] = false;
		
	}
	
	/**
	 * 
	 * @param i indice di riga del posto
	 * @param j indice di colonna del posto
	 * @return true se il posto è occupato, false altrimenti
	 */
	public boolean check(int i, int j) {
		
		return posti[i][j];
		
	}
	
	/**
	 * 
	 * @return lista di stringhe contenente i posti liberi
	 */
	public ArrayList<String> postiOccupati() {
		
		ArrayList<String> occupati = new ArrayList<String>();
		
		for (char c = 'A'; c < 'A' + file; c++)
		{
			for (int j = 0; j < num; j++)
			{
				if (!posti[c-65][j])
					occupati.add((j + 1) + "" + c);
			}
		}
		return occupati;
		
	}
	
	/**
	 * 
	 * @return numero di file dei posti a sedere
	 */
	public int getFile() {
		
		return file;
		
	}
	
	/**
	 * 
	 * @return numero di colonne dei posti a sedere
	 */
	public int getNum() {
		
		return num;
		
	}
	
	/**
	 * 
	 * @return numero totale di posti a bordo dell'aereo
	 */
	public int getTotalPosti() {
		
		return file*num;
		
	}
	
}
