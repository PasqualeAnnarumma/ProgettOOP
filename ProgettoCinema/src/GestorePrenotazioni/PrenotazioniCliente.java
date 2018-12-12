package GestorePrenotazioni;

import java.util.ArrayList;

import GestoreLogin.Cliente;

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
	}
	
	public ArrayList<Prenotazione> getListaPrenotazioni() {
		return prenotazioniCliente;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
}
