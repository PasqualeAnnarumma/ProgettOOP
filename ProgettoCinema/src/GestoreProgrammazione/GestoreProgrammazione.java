package GestoreProgrammazione;

import java.util.ArrayList;

public class GestoreProgrammazione {
	
	ArrayList<ProgrammaSettimanale> listaProgrammiSettimanali;
	
	public GestoreProgrammazione() {
		listaProgrammiSettimanali = new ArrayList<ProgrammaSettimanale>();
	}
	
	public void aggiungiProgrammaSettimanale(ProgrammaSettimanale prog) {
		listaProgrammiSettimanali.add(prog);
	}
	
	public void rimuoviProgrammaSettimanale(ProgrammaSettimanale prog) {
		listaProgrammiSettimanali.remove(prog);
	}
	
	public ArrayList<ProgrammaSettimanale> getListaProgrammiSettimanali() {
		return listaProgrammiSettimanali;
	}
	
	public ProgrammaSettimanale getProgrammaSettimanale(int i) {
		return listaProgrammiSettimanali.get(i);
	}
	
	public int conteggioTotale() {
		int size = 0;
		for (int i = 0; i < listaProgrammiSettimanali.size(); i++)
			size += listaProgrammiSettimanali.get(i).size();
			
		return size;
	}
	
	public int size() {
		return listaProgrammiSettimanali.size();
	}
	
}
