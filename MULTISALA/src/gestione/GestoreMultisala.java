package gestione;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import eccezioni.SalaNonEsistenteException;
import interfacce.Comparatore;
import interfacce.Selettore;
import multisala.Film;
import multisala.Multisala;
import multisala.Posto;
import multisala.Sala;
import multisala.Spettacolo;

public class GestoreMultisala {
	private Multisala multisala;
	/**
	 * 
	 * @param multisala
	 */
	public GestoreMultisala(Multisala multisala) {
		this.multisala = multisala;
	}
	
	/**
	 * Restituisce il multisala.
	 * @return il multisala attuale.
	 */
	public Multisala getMultisala() {
		return multisala;
	}
	/**
	 * Restituisce gli spettacoli della settimana.
	 * @return gli spettacoli della settimana.
	 */
	public ArrayList<Spettacolo> getProgrammazioneSettimanale(){
		return multisala.getProgrammazione().getProgrammazioneSettimanale();
	}
	/**
	 * Restituisce gli spettacoli di una sala.
	 * @param sala
	 * @return gli spettacoli relativi ad una sala.
	 */
	public ArrayList<Spettacolo> getProgrammazioneSelettore(Selettore<Spettacolo> selettore){
		return multisala.getProgrammazione().cercaSpettacoliCriterio(selettore); 
	}
	/**
	 * Restituisce gli spettacoli ordinati in base ad un criterio.
	 * @param comparatore
	 * @return gli spettacoli ordinati.
	 */
	public ArrayList<Spettacolo> getSpettacoli(Comparatore<Spettacolo> comparatore){
		return multisala.getProgrammazione().ordinaSpettacoli(comparatore);
	}
	/**
	 * Permette di rendere un posto di una sala indisponibile.
	 * @param sala
	 * @param posto
	 */
	public void setPostoIndisponibile(Sala sala, Posto posto) {
		ArrayList<Sala>	sale = multisala.getListaSala();
		for(int  i = 0; i < sale.size();i++) {
			ArrayList<Posto> posti=sale.get(i).getPosti();
			for(int j = 0; j< posti.size(); j++)
				if(posti.get(i).equals(posto))
					posti.get(i).setStato(Posto.Stato.INDISPONIBILE);
		}
	}
	/**
	 * Aggiunge uno spettacolo in una sala.
	 * @param film
	 * @param sala
	 * @param giorno
	 * @param ora
	 * @param minuti
	 * @throws SalaNonEsistenteException
	 */
	public void aggiungiSpettacolo(Film film , Sala sala, String giorno,String ora,String minuti) throws SalaNonEsistenteException {
		ArrayList<Sala> sale = multisala.getListaSala();
		boolean trovato = false;
		for(int i = 0; i < sale.size(); i++)
			if(sale.get(i).equals(sala)) {
				trovato = true;
				break;
			}
		if(!trovato)
			throw new SalaNonEsistenteException();
		
		DayOfWeek day = null;
		switch(giorno) {
			case "Lunedì": day = DayOfWeek.MONDAY;
			break;
			case "Martedì": day = DayOfWeek.TUESDAY;
			break;
			case "Mercoledì": day = DayOfWeek.WEDNESDAY;
			break;
			case "Giovedì": day = DayOfWeek.THURSDAY;
			break;
			case "Venerdì": day = DayOfWeek.FRIDAY;
			break;
			case "Sabato": day = DayOfWeek.SATURDAY;
			break;
			case "Domenica": day = DayOfWeek.SUNDAY;
			break;
		}
		//Lorenzo Carpentieri si oppone all'utilizzo di localDateTime:"Sono stato costretto ad implementarlo".
		LocalDateTime data = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().with(TemporalAdjusters.next(day)).getDayOfYear(), Integer.parseInt(ora), Integer.parseInt(minuti));
		Selettore<Spettacolo> selettore = (spettacolo) -> spettacolo.getSala().equals(sala);
		ArrayList<Spettacolo> spettacoli = getProgrammazioneSelettore(selettore);
		for(int i = 0; i < spettacoli.size();i++)
			if(data.getDayOfMonth() == spettacoli.get(i).getData().getDayOfMonth())
				//finire il controllo della data ho fatto gia l eccezione SalaGiaOccupata.
		multisala.getProgrammazione().aggiungiSpettacolo(new Spettacolo(film, sala, data));
	}
	/**
	 * Permette di aggiungere un film al multisala.
	 * @param titolo
	 * @param descrizione
	 * @param durata
	 */
	public void aggiungiFilm(String titolo, String descrizione, int durata, double prezzo) {
		Film film = new Film(titolo, descrizione, durata,prezzo);
		multisala.getListaFilm().add(film);
	}
}
