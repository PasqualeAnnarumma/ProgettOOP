package GestorePrenotazioni;

import java.util.ArrayList;

import Eccezioni.PostoNonDisponibileException;
import GestoreLogin.Cliente;
import GestoreSale.Posto;

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
	
	public void acquistaPosto(Posto p) {
		try {
			p.acquistaPosto();
		} catch (PostoNonDisponibileException e) {
			System.out.println(e);
		}
	}
	
	public void aggiungiPrenotazione(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		if (prenotazione.getPosto().isOccupato()) throw new PostoNonDisponibileException("Posto già occupato!");
		if (prenotazione.getPosto().isAcquistato()) throw new PostoNonDisponibileException("Posto già acquistato");
		if (prenotazione.getPosto().isDisponibile() == false) throw new PostoNonDisponibileException("Posto non disponibile");
		prenotazione.getPosto().occupaPosto();
		PrenotazioniCliente lista = getListaCliente(cliente);
		if (lista == null)
		{
			lista = new PrenotazioniCliente(cliente);
			listaPrenotazioni.add(lista);
		}
			
		lista.aggiungiPrenotazione(prenotazione);
	}
	
	public void rimuoviPrenotazione(Prenotazione prenotazione, Cliente cliente) throws PostoNonDisponibileException{
		if (controlloProprietà(cliente, prenotazione.getPosto()) == null) throw new PostoNonDisponibileException("Il posto è di un altro utente!");
		PrenotazioniCliente lista = getListaCliente(cliente);
		if (lista != null)
			lista.rimuoviPrenotazione(prenotazione);
	}
	
	public void acquista(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		if (controlloProprietà(cliente, prenotazione.getPosto()) == null) throw new PostoNonDisponibileException("Il posto è di un altro utente!");
		PrenotazioniCliente lista = getListaCliente(cliente);
		if (lista != null)
			lista.rimuoviPrenotazione(prenotazione);
		else
			lista = new PrenotazioniCliente(cliente);
		
		lista.aggiungiPrenotazione(prenotazione);
	}
	
	public Posto controlloProprietà(Cliente cliente, Posto p) {
		PrenotazioniCliente pren;
		if ((pren = getListaCliente(cliente)) == null) return null;
		return pren.searchPrenotazione(p);
	}

}
