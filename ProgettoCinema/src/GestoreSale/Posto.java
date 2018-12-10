package GestoreSale;

import Eccezioni.PostoNonDisponibileException;

public class Posto {
	
	int riga;
	char colonna;
	boolean occupato;
	boolean disponibile;
	
	public Posto(int row, char column) {
		riga = row;
		colonna = column;
		occupato = false;
		disponibile = true;
	}
	
	public Posto(int row, char column, boolean state) {
		riga = row;
		colonna = column;
		occupato = false;
		disponibile = state;
	}
	
	public void occupaPosto() throws PostoNonDisponibileException {
		if (!isDisponibile()) throw new PostoNonDisponibileException("Posto non disponibile!");
		if (occupato) throw new PostoNonDisponibileException("Posto occupato!");
		occupato = true;
	}
	
	public void liberaPosto() throws PostoNonDisponibileException{
		if (!isOccupato()) throw new PostoNonDisponibileException("Posto già libero!");
		occupato = false;
	}
	
	public void rendiDisponibile() {
		disponibile = true;
	}
	
	public void rendiIndisponibile() {
		disponibile = false;
	}
	
	public int getRiga() {
		return riga;
	}
	
	public char getColonna() {
		return colonna;
	}
	
	public boolean isOccupato() {
		return occupato;
	}
	
	public boolean isDisponibile() {
		return disponibile;
	}
}
