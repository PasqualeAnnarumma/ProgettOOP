package GestoreSale;

import Eccezioni.PostoNonDisponibileException;

public class Posto {
	
	char riga;
	int colonna;
	boolean occupato;
	boolean disponibile;
	
	public Posto(char row, int column) {
		riga = row;
		colonna = column;
		occupato = false;
		disponibile = true;
	}
	
	public Posto(char row, int column, boolean state) {
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
	
	public char getRiga() {
		return riga;
	}
	
	public int getColonna() {
		return colonna;
	}
	
	public boolean isOccupato() {
		return occupato;
	}
	
	public boolean isDisponibile() {
		return disponibile;
	}
}
