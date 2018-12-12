package GestorePrenotazioni;

import java.util.ArrayList;
import GestoreLogin.Cliente;

public class GestorePrenotazioni {
	
	ArrayList<PrenotazioniCliente> listaPrenotazioni;
	
	public GestorePrenotazioni() {
		listaPrenotazioni = new ArrayList<PrenotazioniCliente>();
	}
	
	public ArrayList<PrenotazioniCliente> getListaPrenotazioni() {
		return listaPrenotazioni;
	}
	
	public PrenotazioniCliente getListaCliente(Cliente cliente) {
		for (PrenotazioniCliente pc : listaPrenotazioni)
			if (cliente.equals(pc.getCliente())) return pc;
		
		return null;
	}
	
	public void aggiungiPrenotazione(Prenotazione prenotazione, Cliente cliente) {
		PrenotazioniCliente lista = getListaCliente(cliente);
		if (lista != null)
			lista.aggiungiPrenotazione(prenotazione);
		else
		{
			PrenotazioniCliente nuova = new PrenotazioniCliente(cliente);
			listaPrenotazioni.add(nuova);
		}
	}

}
