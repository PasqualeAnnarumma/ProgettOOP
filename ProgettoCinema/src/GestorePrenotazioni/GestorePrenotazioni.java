package GestorePrenotazioni;

import java.io.Serializable;
import java.util.ArrayList;

import Eccezioni.PostoNonDisponibileException;
import GestoreLogin.Cliente;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;
import GestoreSale.Sala;

/**
 * Il gestore delle prenotazioni è il gestore che si occupa delle prenotazioni del cinema
 * @author MarioELT
 *
 */
public class GestorePrenotazioni implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<PrenotazioniCliente> listaPrenotazioni;
	
	/**
	 * Costruisce il gestore delle prenotazioni
	 */
	public GestorePrenotazioni() {
		listaPrenotazioni = new ArrayList<PrenotazioniCliente>();
	}
	
	/**
	 * Restituisce la lista delle prenotazioniCliente
	 * @return lista di tutte le prenotazioniCliente
	 */
	public ArrayList<PrenotazioniCliente> getListaPrenotazioni() {
		return listaPrenotazioni;
	}
	
	/**
	 * Restituisce la lista delle prenotazioni di un film
	 * @param film film per il quale si vuole la lista delle prenotazioni
	 * @return lista delle prenotazioni per quel film
	 */
	public ArrayList<Prenotazione> getListaPrenotazioni(Film film) {
		ArrayList<Prenotazione> result = new ArrayList<Prenotazione>();
		for (PrenotazioniCliente pc : listaPrenotazioni)
		{
			ArrayList<Prenotazione> lista = pc.getListaPrenotazioni();
			for (Prenotazione p : lista)
			{
				Film f = p.getSpettacolo().getFilm();
				if (f.equals(film))
					result.add(p);
			}
		}
		
		return result;
	}
	
	/**
	 * restituisce la lista delle prenotazioni di un cliente
	 * @param cliente per il quale si vuole la lista di prenotazioni
	 * @return lista di prenotazioni del cliente specificato
	 */
	public PrenotazioniCliente getPrenotazioniCliente(Cliente cliente) {
		for (PrenotazioniCliente pc : listaPrenotazioni)
			if (cliente.equals(pc.getCliente())) return pc;
		
		return null;
	}
	
	/**
	 * Imposta un posto come acquistato
	 * @param posto da acquistare
	 * @throws PostoNonDisponibileException se il posto non è disponibile o è stato già acquistato
	 */
	/*public void acquistaPosto(Posto posto) throws PostoNonDisponibileException{
		posto.acquistaPosto();
	}*/
	
	/**
	 * Aggiunge una prenotazione per un cliente
	 * @param cliente cliente che effettua la prenotazione
	 * @param prenotazione prenotazione del cliente
	 * @throws PostoNonDisponibileException se il posto delle prenotazione è già occupato, già acquistato o non disponibile
	 */
	public void aggiungiPrenotazione(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		//System.out.println(prenotazione);
		if (prenotazione.getPosto().isOccupato()) throw new PostoNonDisponibileException("Posto già occupato!");
		if (prenotazione.getPosto().isAcquistato()) throw new PostoNonDisponibileException("Posto già acquistato");
		if (prenotazione.getPosto().isDisponibile() == false) throw new PostoNonDisponibileException("Posto non disponibile");
		prenotazione.getPosto().occupaPosto();
		PrenotazioniCliente lista = getPrenotazioniCliente(cliente);
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
	
	/**
	 * Rimuove una prenotazione di un cliente
	 * @param prenotazione prenotazione da rimuovere
	 * @param cliente cliente proprietario della prenotazione
	 * @throws PostoNonDisponibileException se il posto della prenotazione è già libero o è di un altro utente
	 */
	public void rimuoviPrenotazione(Prenotazione prenotazione, Cliente cliente) throws PostoNonDisponibileException{
		//System.out.println("GESTOREPRENOTAZIONE : " + prenotazione);
		if (prenotazione == null)
			throw new PostoNonDisponibileException("Posto già libero");
		if (controlloProprietà(cliente, prenotazione.getPosto(), prenotazione.getSpettacolo()) == null)
			throw new PostoNonDisponibileException("Il posto è di un altro utente!");
		PrenotazioniCliente lista = getPrenotazioniCliente(cliente);
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
	
	/**
	 * Setta disponibile il posto della sala specificato
	 * @param posto posto da rendere disponibile
	 * @param sala sala che contiene il posto
	 * @param listaSpettacoli lista degli spettacoli che vengono svolti nella sala specificata
	 */
	public void setDisponibile(Posto posto, Sala sala, ArrayList<Spettacolo> listaSpettacoli) {
		posto.rendiDisponibile();
		aggiornaSpettacoli(posto, sala, listaSpettacoli, true);
	}
	
	/**
	 * Setta indisponibile il posto della sala specificato
	 * @param posto posto da rendere indisponibile
	 * @param sala sala che contiene il post
	 * @param listaClienti lista di tutti i clienti
	 * @param listaSpettacoli lista di tutti gli spettacoli
	 */
	public void setIndisponibile(Posto posto, Sala sala, ArrayList<Cliente> listaClienti, ArrayList<Spettacolo> listaSpettacoli) {
		posto.rendiIndisponibile();
		rimuoviPrenotazioniCliente(posto, sala, listaClienti);
		aggiornaSpettacoli(posto, sala, listaSpettacoli, false);
		//rimuoviPostiSpettacoli(posto, sala, listaSpettacoli);
	}
	
	/**
	 * Rimuove tutte le prenotazioni con il posto secificato nella sala specificata
	 * @param posto posto da cercare
	 * @param sala sala da cercare
	 * @param listaClienti lista di tutti i clienti
	 */
	public void rimuoviPrenotazioniCliente(Posto posto, Sala sala, ArrayList<Cliente> listaClienti) {
		for (Cliente cliente : listaClienti)
		{
			//PRENDI LA LISTA DI PRENOTAZIONI DEL CLIENTE
			PrenotazioniCliente lista = getPrenotazioniCliente(cliente);
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
						cliente.removePrenotazione();
					}
				}
			}
		}
	}
	
	/**
	 * Setta il posto di tutti gli spettacoli che si svolgono nella sala specificata come disponibile o indisponibile
	 * @param posto posto da rendere disponibile o indisponibile
	 * @param sala sala nella quale è contenuto il posto
	 * @param listaSpettacoli lista di tutti gli spettacoli
	 * @param disponibile flag che viene impostato a true se il posto deve diventare disponibile, a false se indisponibile
	 */
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
	
	/**
	 * Imposta una prenotazione di un cliente come acquistata
	 * @param cliente cliente proprietario della prenotazione
	 * @param prenotazione prenotazione da impostare come acquistata
	 * @throws PostoNonDisponibileException se il posto è di un altro utente
	 */
	public void acquista(Cliente cliente, Prenotazione prenotazione) throws PostoNonDisponibileException{
		if (controlloProprietà(cliente, prenotazione.getPosto(), prenotazione.getSpettacolo()) == null)
			throw new PostoNonDisponibileException("Il posto è di un altro utente!");
		PrenotazioniCliente lista = getPrenotazioniCliente(cliente);
		if (lista != null)
		{
			//System.out.println("Cancello");
			lista.rimuoviPrenotazione(prenotazione);
		}
		else
			lista = new PrenotazioniCliente(cliente);
		
		prenotazione.setPagato();
		prenotazione.getPosto().acquistaPosto();
		//System.out.println(prenotazione);
		lista.aggiungiPrenotazione(prenotazione);
	}
	
	/**
	 * Controlla se un cliente ha prenotato il posto specificato
	 * @param cliente cliente da controllare
	 * @param posto posto da controllare
	 * @param spettacolo spettacolo da controllare
	 * @return
	 */
	public Posto controlloProprietà(Cliente cliente, Posto posto, Spettacolo spettacolo) {
		PrenotazioniCliente pren;
		if ((pren = getPrenotazioniCliente(cliente)) == null) return null;
		/*for (int i = 0 ; i < pren.size(); i++)
			System.out.println(pren.getPrenotazione(i));*/
		
		return pren.searchPrenotazione(posto, spettacolo);
	}

}
