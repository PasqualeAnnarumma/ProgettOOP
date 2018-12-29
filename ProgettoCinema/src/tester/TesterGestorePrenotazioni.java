package tester;

import java.util.ArrayList;

import Eccezioni.PostoNonDisponibileException;
import GestoreLogin.Cliente;
import GestoreLogin.Cliente.Categoria;
import GestorePrenotazioni.GestorePrenotazioni;
import GestorePrenotazioni.Prenotazione;
import GestorePrenotazioni.PrenotazioniCliente;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;
import GestoreSale.Sala;

public class TesterGestorePrenotazioni {

	public static void main(String[] args)
	{
		GestorePrenotazioni gestore = new GestorePrenotazioni();
		
		Sala[] sale = {new Sala(0, 15, 15), new Sala(1, 20, 20), new Sala(2, 30, 30)};
		System.out.println("Stampo le sale");
		System.out.println("--- Inizio stampa");
		for (int i = 0; i < sale.length; i++)
			System.out.println(sale[i]);
		System.out.println("--- Fine stampa ---");
		
		Film[] film = {new Film("Inception", "2:30", "Martin Scorsese", "Inception"),
					   new Film("Un sacchetto pieno di biglie", "1:50", "Christian Duguay", "Un sacchetto pieno di biglie"),
					   new Film("L'ora più buia", "2:00", "Joe Wright", "L'ora più buia")};
		System.out.println("Stampo i film");
		System.out.println("--- Inizio stampa");
		for (int i = 0; i < film.length; i++)
			System.out.println(film[i]);
		System.out.println("--- Fine stampa ---");
		
		ArrayList<Spettacolo> listaSpettacoli = new ArrayList<Spettacolo>();
		Spettacolo[] spettacoli = {new Spettacolo(sale[0], film[0], 31, 12, 2018, "20:30", 10.5),
								   new Spettacolo(sale[1], film[1], 24, 12, 2018, "22:30", 8.5),
								   new Spettacolo(sale[2], film[2], 28, 12, 2018, "16:45", 9.5),
								   new Spettacolo(sale[2], film[1], 31, 12, 2018, "22:30", 10)};
		System.out.println("Stampo gli spettacoli");
		System.out.println("--- Inizio stampa");
		for (int i = 0; i < spettacoli.length; i++)
		{
			listaSpettacoli.add(spettacoli[i]);
			System.out.println(spettacoli[i]);
		}
		System.out.println("--- Fine stampa ---");
		
		Posto[] posti = {new Posto('A', 0), new Posto('A', 1), new Posto('A', 2)};
		
		Cliente cliente = new Cliente("c", "123", "08/03/1997", Categoria.STUDENTE);
		ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
		listaClienti.add(cliente);
		Prenotazione[] prenotazioni = {new Prenotazione(spettacoli[0], posti[0].clone(), cliente),
									   new Prenotazione(spettacoli[1], posti[1].clone(), cliente),
									   new Prenotazione(spettacoli[2], posti[2].clone(), cliente),
									   new Prenotazione(spettacoli[0], posti[1].clone(), cliente)};
		
		try {
			for (int i = 0; i < prenotazioni.length; i++)
				gestore.aggiungiPrenotazione(cliente, prenotazioni[i]);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Ottengo la lista delle prenotazioni");
		ArrayList<PrenotazioniCliente> lista = gestore.getListaPrenotazioni();
		System.out.println("--- Inizio stampa ---");
		for (PrenotazioniCliente pc : lista)
		{
			for (int i = 0; i < pc.size(); i++)
				System.out.println(pc.getPrenotazione(i));
		}
		System.out.println("--- Fine stampa ---\n");
		
		System.out.println("\nOttengo la lista delle prenotazioni per il film " + film[0].getNome());
		ArrayList<Prenotazione> listaPrenotazioni = gestore.getListaPrenotazioni(film[0]);
		System.out.println("--- Inizio stampa ---");
		for (Prenotazione p : listaPrenotazioni)
			System.out.println(p);
		System.out.println("--- Fine stampa ---\n");
		
		System.out.println("Provo ad acquistare un posto di un altro utente");
		try {
			gestore.acquista(new Cliente("c", "", "08/03/1997", Categoria.NESSUNO), listaPrenotazioni.get(0));
		} catch (PostoNonDisponibileException ex) {
			System.err.println(ex.getMessage());
		}
		
		System.out.println("Acquisto tutte le prenotazioni");
		try {
			for (Prenotazione p : listaPrenotazioni)
				gestore.acquista(cliente, p);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Riprendo la lista delle prenotazioni per il film " + film[0].getNome());
		listaPrenotazioni = gestore.getListaPrenotazioni(film[0]);
		System.out.println("--- Inizio stampa ---");
		for (Prenotazione p : listaPrenotazioni)
			System.out.println(p);
		System.out.println("--- Fine stampa ---\n");
		
		System.out.println("Rendo indisponibile il posto " + posti[1] + "\nTutte le prenotazioni per quel posto verranno cancellate");
		gestore.setIndisponibile(posti[1], sale[0], listaClienti, listaSpettacoli);
		System.out.println("Riprendo la lista delle prenotazioni per il film " + film[0].getNome());
		listaPrenotazioni = gestore.getListaPrenotazioni(film[0]);
		System.out.println("--- Inizio stampa ---");
		for (Prenotazione p : listaPrenotazioni)
			System.out.println(p);
		System.out.println("--- Fine stampa ---\n");
		
		System.out.println("Provo a prenotare il posto che è stato appena reso indisponibile\n" + posti[1]);
		try {
			gestore.aggiungiPrenotazione(cliente, new Prenotazione(spettacoli[0], posti[1], cliente));
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				System.out.println(e1.getMessage());
			}
		}
		
		System.out.println("\nRendo disponibile il posto " + posti[1]);
		gestore.setDisponibile(posti[1], sale[0], listaSpettacoli);
		System.out.println("Provo a prenotare il posto che è stato appena reso indisponibile\n" + posti[1]);
		try {
			gestore.aggiungiPrenotazione(cliente, new Prenotazione(spettacoli[0], posti[1], cliente));
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Riprendo la lista delle prenotazioni");
		listaPrenotazioni = gestore.getListaPrenotazioni(film[0]);
		System.out.println("Riprendo la lista delle prenotazioni per il film " + film[0].getNome());
		listaPrenotazioni = gestore.getListaPrenotazioni(film[0]);
		System.out.println("--- Inizio stampa ---");
		for (Prenotazione p : listaPrenotazioni)
			System.out.println(p);
		System.out.println("--- Fine stampa ---\n");
		
		System.out.println("Rimuovo tutte le prenotazioni");
		try {
			for (Prenotazione p : listaPrenotazioni)
				gestore.rimuoviPrenotazione(p, cliente);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Provo a rimuovere una prenotazione di un altro utente");
		try {
			gestore.rimuoviPrenotazione(listaPrenotazioni.get(0), new Cliente("c", "", "08/03/1997", Categoria.NESSUNO));
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Provo a rimuovere una prentazione nulla");
		try {
			gestore.rimuoviPrenotazione(null, new Cliente("c", "", "08/03/1997", Categoria.NESSUNO));
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Riprendo la lista delle prenotazioni");
		listaPrenotazioni = gestore.getListaPrenotazioni(film[0]);
		System.out.println("Riprendo la lista delle prenotazioni per il film " + film[0].getNome());
		listaPrenotazioni = gestore.getListaPrenotazioni(film[0]);
		System.out.println("--- Inizio stampa ---");
		for (Prenotazione p : listaPrenotazioni)
			System.out.println(p);
		System.out.println("--- Fine stampa ---\n");
	}

}
