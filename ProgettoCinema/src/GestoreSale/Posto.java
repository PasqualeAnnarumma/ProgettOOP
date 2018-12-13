package GestoreSale;

import Eccezioni.PostoNonDisponibileException;

public class Posto implements Cloneable{
	
	char riga;
	int colonna;
	boolean occupato;
	boolean disponibile;
	boolean acquistato;
	
	public Posto(char row, int column) {
		riga = row;
		colonna = column;
		occupato = false;
		disponibile = true;
		acquistato = false;
	}
	
	public Posto(char row, int column, boolean state) {
		riga = row;
		colonna = column;
		occupato = false;
		acquistato = false;
		disponibile = state;
	}
	
	public void acquistaPosto() throws PostoNonDisponibileException{
		if (!isDisponibile()) throw new PostoNonDisponibileException("Posto non disponibile");
		if (isAcquistato()) throw new PostoNonDisponibileException("Posto già acquistato");
		occupato = true;
		acquistato = true;
	}
	
	public void occupaPosto() throws PostoNonDisponibileException {
		if (!isDisponibile()) throw new PostoNonDisponibileException("Posto non disponibile!");
		if (occupato) throw new PostoNonDisponibileException("Posto occupato!");
		occupato = true;
	}
	
	public void liberaPosto() throws PostoNonDisponibileException{
		if (!isOccupato()) throw new PostoNonDisponibileException("Posto già libero!");
		occupato = false;
		acquistato = false;
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
	
	public boolean isAcquistato() {
		return acquistato;
	}
	
	public void setRiga(char ch) {
		riga = ch;
	}
	
	public Posto clone() {
		try {
			return (Posto) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Posto p = (Posto) obj;
		return p.riga == riga && p.colonna == colonna && p.disponibile == disponibile && p.occupato == occupato && acquistato == p.acquistato;
	}
	
	public String toString() {
		return getClass().getSimpleName() + "[riga=" + riga + ",colonna=" + colonna + ",occupato=" + occupato + ",dispnibile=" + disponibile + ",acquistato" + "]";
	}
}
