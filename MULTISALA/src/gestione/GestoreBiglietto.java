package gestione;

import java.util.ArrayList;

import eccezioni.BigliettoNonCancellabileException;
import eccezioni.PostoIndisponibileException;
import multisala.Film;
import multisala.Multisala;
import multisala.Posto;
import multisala.Spettacolo;
import sconti.Sconto;
import sconti.ScontoMercoledi;
import sconti.ScontoOver60;
import sconti.ScontoStudenti;
import sconti.ScontoUnder14;
import utente.AccountCliente;
import utente.Biglietto;
import utente.Biglietto.Stato;

public class GestoreBiglietto {
	private ArrayList<Sconto> sconti;
	private Multisala multisala;
	
	
	public GestoreBiglietto(Multisala multisala) {
		sconti = new ArrayList<Sconto>();
		this.popolaSconti();
		this.multisala = multisala;
	}
	
	private void popolaSconti() {
		sconti.add(new ScontoMercoledi(20, true));
		sconti.add(new ScontoOver60(10, true));
		sconti.add(new ScontoStudenti(15, true));
		sconti.add(new ScontoUnder14(20, true));
	}
	
	/**
	 * Restituisce il multisala.
	 * @return il multisala attuale.
	 */
	public Multisala getMultisala() {
		return multisala;
	}
	/**
	 * Cerca lo sconto milgiore tra quelli attivi. 
	 * @param prezzo base dello spettacolo.
	 * @return il prezzo dello sconto migliore.
	 */
	private double cercaScontoMigliore(double prezzo) {
		int i;
		Sconto scontoMigliore = null;
		for(i = 0; i<sconti.size();i++)
			if(sconti.get(i).getStato()==true) {
				scontoMigliore = sconti.get(i);
				break;
			}
		if(i==sconti.size())
			return prezzo;
		else {
			for(; i < sconti.size();i++)
				if(sconti.get(i).getStato() == true && sconti.get(i).getsconto(prezzo)<scontoMigliore.getsconto(prezzo))
					scontoMigliore = sconti.get(i);
			return scontoMigliore.getsconto(prezzo);
		}
	}
	/**
	 * Permette di acquistare un biglietto se il posto è disponibile  o se è stato prenotato dal cliente che effettua l'acquisto.
	 * @param spettacolo
	 * @param cliente
	 * @param posto
	 */
	public void acquisto(Spettacolo spettacolo, AccountCliente cliente, Posto posto) {
		//Acquisto il posto se il posto è disponibile oppure se è stato prenotato da quel cliente.
		if(posto.getStato() == Posto.Stato.DISPONIBILE) {
			double  prezzoBase = spettacolo.getFilm().getPrezzo();
			double prezzoScontato = this.cercaScontoMigliore(prezzoBase);	
			multisala.aggiornaIncasso(prezzoScontato);
			spettacolo.getFilm().addIncassoFilm(prezzoScontato);
			cliente.aggiungiBiglietto(new Biglietto(spettacolo, posto,prezzoScontato, Stato.ACQUISTATO));
		}
		else if(posto.getStato()==Posto.Stato.PRENOTATO && cliente.getBigliettoSpettacolo(spettacolo).getPosto().equals(posto)) {
			cliente.getBigliettoSpettacolo(spettacolo).setStato(Biglietto.Stato.ACQUISTATO);
		}
		else 
			throw new PostoIndisponibileException();
	}
	/**
	 * Restituisce l'incasso del multisala.
	 * @return l'incasso del multisala.
	 */
	public double getIncassoMultisala() {
		return multisala.getIncasso();
	}
	/**
	 * Restituisce l'incasso totale di un certo film.
	 * @param film
	 * @return l'incasso totale del film.
	 */
	public double getIncassoFilm(Film film) {
		return film.getIncassoFilm();
	}
	/**
	 * Permette di effettuare una prenotazione se il posto è diponibile.
	 * @param spettacolo
	 * @param cliente
	 * @param posto
	 */
	public void prenotazione(Spettacolo spettacolo, AccountCliente cliente, Posto posto) {
		if(posto.getStato() == Posto.Stato.DISPONIBILE) {
			double  prezzoBase = spettacolo.getFilm().getPrezzo();
			double prezzoScontato = this.cercaScontoMigliore(prezzoBase);	
			cliente.aggiungiBiglietto(new Biglietto(spettacolo, posto, prezzoScontato, Biglietto.Stato.PRENOTATO));
		}
		else
			throw new PostoIndisponibileException();	
	}
	/**
	 * Cancella la prenotazione relativa ad un posto di uno specifico spettacolo.
	 * @param posto
	 */
	public void cancellaPrenotazione(AccountCliente cliente, Biglietto biglietto) throws BigliettoNonCancellabileException{
		if(biglietto.getStato() == Biglietto.Stato.PRENOTATO) {
			cliente.rimuoviBiglietto(biglietto);
			biglietto.getPosto().setStato(Posto.Stato.DISPONIBILE);
		}
			
		else 
			throw new BigliettoNonCancellabileException();
	}
}
