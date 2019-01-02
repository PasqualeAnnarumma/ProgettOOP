package tester;

import java.util.ArrayList;

import Eccezioni.AccountGiaEsistenteException;
import Eccezioni.PostoNonDisponibileException;
import GestoreLogin.Amministratore;
import cinema.Cinema;
import GestoreLogin.Cliente;
import GestoreLogin.Cliente.Categoria;
import GestoreLogin.GestoreLogin;
import GestorePrenotazioni.GestorePrenotazioni;
import GestorePrenotazioni.Prenotazione;
import GestorePrenotazioni.PrenotazioniCliente;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.GestoreProgrammazione;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.GestoreSale;
import GestoreSale.Posto;
import GestoreSale.Sala;
import GestoreSconti.GestoreSconti;

public class TesterCinema {

	public static void main(String[] args)
	{
		System.out.println("Creo il cinema");
		Cinema cinema = new Cinema();
		System.out.println("Creo un utente e un amministratore");
		Amministratore amm = new Amministratore("admin", "123");
		try {
			cinema.registraCliente("Utente", "123", "08/03/1997", Categoria.NESSUNO);
			cinema.registraAmministratore("admin", "123");
		} catch (AccountGiaEsistenteException e) {
			System.out.println(e);
		}
		
		try {
			System.out.println("Registro un utente già creato");
			cinema.registraCliente("Utente", "123", "08/03/1997", Categoria.NESSUNO);
		} catch (AccountGiaEsistenteException e2) {
			System.err.println(e2.getMessage());
		}
		
		System.out.println("Aggiungo una sala");
		cinema.aggiungiSala(20, 30);
		System.out.println("Numero sale : " + cinema.getNumeroSale());
		System.out.println("Ottengo la lista delle sale");
		ArrayList<Sala> listaSale = cinema.getListaSale();
		System.out.println("--- Inizio lista ---");
		for (Sala s : listaSale)
			System.out.println("Sala " + s);
		System.out.println("-- Fine lista ---\n");
		
		System.out.println("Creo il film \"Un sacchetto pieno di biglie\"");
		Film film = new Film("Un saccheto pieno di biglie", "2:20", "Cristopher", "Un sacchetto pieno di biglie");
		System.out.println("Aggiungo il film \"" + film + "\"");
		cinema.aggiungiFilm(film);
		
		System.out.println("Creo il film \"Inception\"");
		Film film2 = new Film("Inception", "2:00", "Robin", "Inception");
		System.out.println("Aggiungo il film \"" + film2 + "\"");
		cinema.aggiungiFilm(film2);
		
		ArrayList<Film> listaFilm = cinema.getListaFilm();
		System.out.println("Ottengo la lista dei film");
		System.out.println("Stampo la lista dei film");
		System.out.println("--- Inizio lista ---");
		for (Film f : listaFilm)
			System.out.println(f);
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Creo uno spettacolo");
		Spettacolo spettacolo = new Spettacolo(listaSale.get(0), film, 3, 1, 2019, "22:30", 10.5);
		System.out.println("Spettacolo creato : " + spettacolo);
		System.out.println("Aggiungo lo spettacolo al cinema");
		cinema.aggiungiSpettacolo(spettacolo);
		
		System.out.println("Creo uno spettacolo");
		spettacolo = new Spettacolo(listaSale.get(0), film2, 3, 1, 2019, "20:30", 10.5);
		System.out.println("Spettacolo creato : " + spettacolo);
		System.out.println("Aggiungo lo spettacolo al cinema");
		cinema.aggiungiSpettacolo(spettacolo);
		
		System.out.println("Ottengo la lista degli spettacoli");
		ArrayList<Spettacolo> listaSpettacoli = cinema.getListaSpettacoli();
		System.out.println("--- Inizio lista ---");
		for (Spettacolo sp : listaSpettacoli)
			System.out.println(sp);
		System.out.println("--- Fine lsta ---\n");
		
		System.out.println("Ottengo la lista degli spettacoli nella sala 0, per ordine cronologico");
		listaSpettacoli = cinema.getListaSpettacoli(cinema.ordineCronologico, "0");
		System.out.println("--- Inizio lista ---");
		for (Spettacolo sp : listaSpettacoli)
			System.out.println(sp);
		System.out.println("--- Fine lsta ---\n"); 
		
		System.out.println("Ottengo la lista degli spettacoli utilizzando un il selettore \"settimana\"");
		listaSpettacoli = cinema.getListaSpettacoli(cinema.settimana);
		System.out.println("--- Inizio lista ---");
		for (Spettacolo sp : listaSpettacoli)
			System.out.println(sp);
		System.out.println("--- Fine lsta ---\n"); 
		
		System.out.println("Ottengo la lista degli spettacoli fruibili, ordinati per sala crescente");
		listaSpettacoli = cinema.getSpettacoliFruibili(cinema.salaCrescente, "Tutte");
		System.out.println("--- Inizio lista ---");
		for (Spettacolo sp : listaSpettacoli)
			System.out.println(sp);
		System.out.println("--- Fine lsta ---\n"); 
		
		System.out.println("Ottengo la lista degli spettacoli fruibili, ordinati alfabeticamente");
		listaSpettacoli = cinema.getSpettacoliFruibili(cinema.titoloAlfabetico, "Tutte");
		System.out.println("--- Inizio lista ---");
		for (Spettacolo sp : listaSpettacoli)
			System.out.println(sp);
		System.out.println("--- Fine lsta ---\n");
		
		System.out.println("Ottengo la lista degli spettacoli fruibili, ordinati per posti disponibili");
		listaSpettacoli = cinema.getSpettacoliFruibili(cinema.postiDisponibili, "Tutte");
		System.out.println("--- Inizio lista ---");
		for (Spettacolo sp : listaSpettacoli)
			System.out.println(sp);
		System.out.println("--- Fine lsta ---\n"); 
		
		System.out.println("Effettuo il login come amministratore");
		cinema.login("admin", "123");
		System.out.println("Ottengo l'utente loggato nel sistema (cinema.getUtente)");
		Amministratore admin = (Amministratore) cinema.getUtente();
		System.out.println("Utente loggato : " + admin.getUsername());
		System.out.println("Effettuo il login come utente");
		cinema.login("Utente", "123");
		System.out.println("Ottengo l'utente loggato nel sistema (cinema.getUtente)");
		Cliente cliente = (Cliente) cinema.getUtente();
		System.out.println("Utente loggato : " + cliente.getUsername());
		System.out.println("");
		
		System.out.println("Rimuovo l'account dell'amministratore");
		cinema.rimuoviUtente(amm);
		
		Sala sala = cinema.getListaSale().get(0);
		Posto posto = sala.getPurePosto('A'-65, 10);
		System.out.println("Imposto il posto " + posto + ", come indisponibile nella sala 0");
		try {
			cinema.setIndisponibile(posto, sala);
			System.out.println(posto);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		Posto[][] posti = sala.getPosti();
		System.out.println("--- Stampo i posti ---");
		for (int i = 0; i < sala.getRighe(); i++)
			for (int j = 0; j < sala.getColonne(); j++)
				if (posti[i][j].isDisponibile()) System.out.println((posti[i][j].getRiga() + "") + posti[i][j].getColonna() + " -> disponibile");
				else System.out.println((posti[i][j].getRiga() + "") + posti[i][j].getColonna() + " -> NON disponibile");
		System.out.println("--- Fine stampa ---\n");
		
		System.out.println("Imposto il posto " + posto + ", come disponibile nella sala 0");
		try {
			cinema.setDisponibile(posto, sala);
			System.out.println(posto);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("--- Stampo i posti ---");
		for (int i = 0; i < sala.getRighe(); i++)
			for (int j = 0; j < sala.getColonne(); j++)
				if (posti[i][j].isDisponibile()) System.out.println((posti[i][j].getRiga() + "") + posti[i][j].getColonna() + " -> disponibile");
				else System.out.println((posti[i][j].getRiga() + "") + posti[i][j].getColonna() + " -> NON disponibile");
		System.out.println("--- Fine stampa ---\n");
		
		System.out.println("Ottengo l'incasso complessivo");
		float incasso = cinema.getIncasso(listaSpettacoli);
		System.out.println("Ottengo l'incasso del film " + film.getNome());
		float incasso2 = cinema.getIncasso(film);
		System.out.println("Incasso totale : " + incasso + ", incasso del film " + film.getNome() + " :" + incasso2);
		
		System.out.println("Creo una prenotazione");
		Prenotazione prenotazione = new Prenotazione(spettacolo, posto, cliente);
		System.out.println("Prenotazione creata : " + prenotazione);
		System.out.println("Aggiungo una prenotazione");
		try {
			cinema.aggiungiPrenotazione(cliente, prenotazione);
		} catch (PostoNonDisponibileException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Ottengo la lista delle prenotazioni");
		ArrayList<PrenotazioniCliente> listaPrenotazioni = cinema.getListaPrenotazioni();
		System.out.println("--- Inizio lista");
		for (PrenotazioniCliente pc : listaPrenotazioni)
		{
			for (int i = 0; i < pc.size(); i++)
				System.out.println(pc.getPrenotazione(i));
		}
		System.out.println("--- Fine lista---\n");
		
		System.out.println("Ottengo la lista delle prenotazioni per il film " + film2.getNome());
		ArrayList<Prenotazione> listaPrenotazioni2 = cinema.getListaPrenotazioni(film2);
		System.out.println("--- Inizio lista");
		for (Prenotazione p : listaPrenotazioni2)
			System.out.println(p);
		System.out.println("--- Fine lista---\n");
		
		System.out.println("\nNumero spettacoli : " + cinema.getNumeroSpettacoli());
		System.out.println("Cerco la prenotazione per il posto " + posto);
		prenotazione = cinema.cercaPrenotazione(posto, spettacolo);
		System.out.println("Prenotazione trovata : " + prenotazione);
		
		System.out.println("\nControllo se l'utente è il proprietario del posto " + posto);
		if (cinema.controlloProprietà(cliente, posto, spettacolo) != null) System.out.println(cliente.getUsername() + " è il proprietario del posto");
		else System.out.println(cliente.getUsername() + " non è il proprietario del posto");
		
		System.out.println("Acquisto il posto " + posto + " per il cliente " + cliente.getUsername());
		try {
			cinema.acquistaPosto(cliente, prenotazione, posto);
			System.out.println(posto);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("--- Stampo i posti ---");
		for (int i = 0; i < sala.getRighe(); i++)
			for (int j = 0; j < sala.getColonne(); j++)
				if (posti[i][j].isAcquistato()) System.out.println((posti[i][j].getRiga() + "") + posti[i][j].getColonna() + " -> acquistato");
				else System.out.println((posti[i][j].getRiga() + "") + posti[i][j].getColonna() + " -> NON acquistato");
		System.out.println("--- Fine stampa ---\n");
		
		posto = sala.getPurePosto(2, 4);
		System.out.println("Acquisto il posto " + posto + " per il cliente " + cliente.getUsername());
		try {
			prenotazione = new Prenotazione(spettacolo, posto, cliente);
			cinema.acquistaPosto(cliente, prenotazione, posto);
			System.out.println(posto);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("--- Stampo i posti ---");
		for (int i = 0; i < sala.getRighe(); i++)
			for (int j = 0; j < sala.getColonne(); j++)
				if (posti[i][j].isAcquistato()) System.out.println((posti[i][j].getRiga() + "") + posti[i][j].getColonna() + " -> acquistato");
				else System.out.println((posti[i][j].getRiga() + "") + posti[i][j].getColonna() + " -> NON acquistato");
		System.out.println("--- Fine stampa ---\n");
		

		System.out.println("Incasso : " + cinema.getIncasso(listaSpettacoli));
		System.out.println("Incasso : " + cinema.getIncasso(film2));
		
		System.out.println("\nCerco lo sconto migliore");
		System.out.println("Sconto migliore : " + cinema.cercaSconto(cliente, spettacolo));
		
		System.out.println("\nRimuovo la prenotazione per il film " + film2);
		try {
			cinema.rimuoviPrenotazione(cliente, prenotazione);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Rimuovo tutte le prenotazioni per il film " + film);
		try {
			cinema.rimuoviPrenotazioniFilm(film);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("\nRimuovo lo spettacolo " + spettacolo);
		try {
			cinema.rimuoviSpettacolo(spettacolo);
		} catch (PostoNonDisponibileException e1) {
			System.err.println(e1.getMessage());
		}
		
		System.out.println("\nRimuovo il film " + film2);
		try {
			cinema.rimuoviFilm(film2);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("\nOttengo tutti i gestori");
		GestoreLogin login = cinema.getGestoreLogin();
		GestorePrenotazioni prenotazioni = cinema.getGestorePrenotazioni();
		GestoreProgrammazione programmazione = cinema.getGestoreProgrammazione();
		GestoreSale sale = cinema.getGestoreSale();
		GestoreSconti sconti = cinema.getGestoreSconti();
		System.out.println(login + "" + prenotazioni + programmazione + sale + sconti);
	}
}
