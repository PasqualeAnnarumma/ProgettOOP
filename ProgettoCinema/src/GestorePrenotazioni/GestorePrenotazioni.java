package GestorePrenotazioni;

import java.util.ArrayList;

import Eccezioni.PostoNonDisponibileException;
import GestoreLogin.Cliente;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;
import GestoreSale.Sala;

public class GestorePrenotazioni {
	
	private ArrayList<PrenotazioniCliente> listaPrenotazioni;
	
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
	
	public void acquistaPosto(Posto p) throws PostoNonDisponibileException{
		p.acquistaPosto();
	}
	
	public void aggiungiPrenotazione(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		//System.out.println(prenotazione);
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
		
		//Prenotazione p = new Prenotazione(prenotazione.getSpettacolo(), prenotazione.getPosto(), prenotazione.getCliente());
		//System.out.println("AGGIUNGO : " + p);
		lista.aggiungiPrenotazione(prenotazione);
		
		/*System.out.println("---INIZIO---");
		for (int i = 0; i < lista.size(); i++)
			System.out.println(lista.getPrenotazione(i));
		System.out.println("---FINE---");*/
	}
	
	public void rimuoviPrenotazione(Prenotazione prenotazione, Cliente cliente) throws PostoNonDisponibileException{
		//System.out.println("GESTOREPRENOTAZIONE : " + prenotazione);
		if (prenotazione == null)
			throw new PostoNonDisponibileException("Posto già libero");
		if (controlloProprietà(cliente, prenotazione.getPosto(), prenotazione.getSpettacolo()) == null)
			throw new PostoNonDisponibileException("Il posto è di un altro utente!");
		PrenotazioniCliente lista = getListaCliente(cliente);
		//System.out.println("PAGATO? " + prenotazione.isPagato());
		//System.out.println(prenotazione);
		prenotazione.setNonPagato();
		prenotazione.getPosto().liberaPosto();
		/*System.out.println("OCCUPATO? : " + prenotazione.getPosto().isOccupato());
		System.out.println("PAGATO? :" + prenotazione.isPagato());
		System.out.println(prenotazione);*/	
		if (lista != null)
		{
			lista.rimuoviPrenotazione(prenotazione);
			cliente.removePrenotazione();
		}
	}
	
	public void setDisponibile(Posto posto, Sala sala, ArrayList<Cliente> listaClienti, ArrayList<Spettacolo> listaSpettacoli) throws PostoNonDisponibileException {
		posto.rendiDisponibile();
		aggiornaSpettacoli(posto, sala, listaSpettacoli, true);
	}
	
	public void setIndisponibile(Posto posto, Sala sala, ArrayList<Cliente> listaClienti, ArrayList<Spettacolo> listaSpettacoli) throws PostoNonDisponibileException{
		posto.rendiIndisponibile();
		rimuoviPrenotazioniCliente(posto, sala, listaClienti);
		aggiornaSpettacoli(posto, sala, listaSpettacoli, false);
		//rimuoviPostiSpettacoli(posto, sala, listaSpettacoli);
	}
	
	public void rimuoviPrenotazioniCliente(Posto posto, Sala sala, ArrayList<Cliente> listaClienti) {
		for (Cliente cliente : listaClienti)
		{
			//PRENDI LA LISTA DI PRENOTAZIONI DEL CLIENTE
			PrenotazioniCliente lista = getListaCliente(cliente);
			//PER OGNI PRENOTAZIONE DEL CLIENTE
			if (lista != null)
			{
				for (int j = 0; j < lista.size(); j++)
				{
					Posto p = lista.getPrenotazione(j).getPosto();
					Sala s = lista.getPrenotazione(j).getSpettacolo().getSala();
					if (sala.getNumeroSala() == s.getNumeroSala() && posto.getColonna() == p.getColonna() && posto.getRiga() == p.getRiga())
					{
						Prenotazione prenotazione = lista.getPrenotazione(j);
						lista.rimuoviPrenotazione(prenotazione);
					}
				}
			}
		}
	}
	
	public void aggiornaSpettacoli(Posto posto, Sala sala, ArrayList<Spettacolo> listaSpettacoli, boolean disponibile) {
		for (Spettacolo spettacolo : listaSpettacoli)
		{
			Sala sa = spettacolo.getSala();
			char riga = posto.getRiga();
			int colonna = posto.getColonna();
			if (sala.getNumeroSala() == sa.getNumeroSala())
			{
				Posto postoSala = sa.getPurePosto(riga-65, colonna);
				if (disponibile) postoSala.rendiDisponibile();
				else postoSala.rendiIndisponibile();
			}
		}
	}
	
	public void acquista(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		if (controlloProprietà(cliente, prenotazione.getPosto(), prenotazione.getSpettacolo()) == null)
			throw new PostoNonDisponibileException("Il posto è di un altro utente!");
		PrenotazioniCliente lista = getListaCliente(cliente);
		if (lista != null)
		{
			//System.out.println("Cancello");
			lista.rimuoviPrenotazione(prenotazione);
		}
		else
			lista = new PrenotazioniCliente(cliente);
		
		prenotazione.setPagato();
		//System.out.println(prenotazione);
		lista.aggiungiPrenotazione(prenotazione);
	}
	
	public Posto controlloProprietà(Cliente cliente, Posto p, Spettacolo spettacolo) {
		PrenotazioniCliente pren;
		if ((pren = getListaCliente(cliente)) == null) return null;
		/*for (int i = 0 ; i < pren.size(); i++)
			System.out.println(pren.getPrenotazione(i));*/
		
		return pren.searchPrenotazione(p, spettacolo);
	}

}
