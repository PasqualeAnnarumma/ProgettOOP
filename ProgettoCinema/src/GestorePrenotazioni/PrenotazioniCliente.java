package GestorePrenotazioni;

import java.util.ArrayList;

import GestoreLogin.Cliente;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;

public class PrenotazioniCliente {
	
	private Cliente cliente;
	private ArrayList<Prenotazione> prenotazioniCliente;
	
	public PrenotazioniCliente(Cliente c) {
		prenotazioniCliente = new ArrayList<Prenotazione>();
		cliente = c;
	}
	
	public void aggiungiPrenotazione(Prenotazione prenotazione) {
		prenotazioniCliente.add(prenotazione);
	}
	
	public void rimuoviPrenotazione(Prenotazione prenotazione) {
		prenotazioniCliente.remove(prenotazione);
		//System.out.println("RIMOZIONE : " + prenotazioniCliente.remove(prenotazione));
	}
	
	public ArrayList<Prenotazione> getListaPrenotazioni() {
		return prenotazioniCliente;
	}
	
	public Prenotazione getPrenotazione(int i) {
		return prenotazioniCliente.get(i);
	}
	
	public Posto searchPrenotazione(Posto posto, Spettacolo spettacolo) {
		if (posto == null) return null;
		for (int i = 0; i < prenotazioniCliente.size(); i++)
		{
			Prenotazione pren = prenotazioniCliente.get(i);
			Posto posto2 = prenotazioniCliente.get(i).getPosto();
			if (posto.getRiga() == posto2.getRiga() && posto.getColonna() == posto2.getColonna() && pren.getSpettacolo().equals(spettacolo))
				return prenotazioniCliente.get(i).getPosto();
		}
		
		return null;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public int size() {
		return prenotazioniCliente.size();
	}
	
}
