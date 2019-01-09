package tester;

import java.util.ArrayList;

import gestoreProgrammazione.Film;
import gestoreProgrammazione.GestoreProgrammazione;
import gestoreProgrammazione.Spettacolo;
import gestoreSale.Sala;

public class TesterGestoreProgrammazione {

	public static void main(String[] args)
	{
		GestoreProgrammazione gestore = new GestoreProgrammazione();
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
		
		for (int i = 0; i < spettacoli.length; i++)
			gestore.aggiungiSpettacolo(spettacoli[i]);
		
		System.out.println("Ottengo la lista degli spettacoli (" + gestore.getNumeroSpettacoli() + " spettacoli)");
		ArrayList<Spettacolo> lista = gestore.getListaSpettacoli();
		System.out.println("--- Inizio lista ---");
		for (Spettacolo s : lista)
			System.out.println(s);
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Ottengo la lista dei film");
		ArrayList<Film> listaFilm = gestore.getListaFilm();
		System.out.println("--- Inizio lista ---");
		for (Film f : listaFilm)
			System.out.println(f);
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Rimuovo il film " + film[0]);
		System.out.println("Viene rimosso in automatico anche lo spettacolo associato");
		gestore.rimuoviFilm(film[0]);
		
		System.out.println("Ottengo di nuovo la lista degli spettacoli (" + gestore.getNumeroSpettacoli() + " spettacoli)");
		lista = gestore.getListaSpettacoli();
		System.out.println("--- Inizio lista ---");
		for (Spettacolo s : lista)
			System.out.println(s);
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Ottengo di nuovo la lista dei film");
		listaFilm = gestore.getListaFilm();
		System.out.println("--- Inizio lista ---");
		for (Film f : listaFilm)
			System.out.println(f);
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Ottengo lo spettacolo il posizione 0 della lista");
		Spettacolo s = gestore.getSpettacolo(0);
		System.out.println("Spettacolo : " + s);
		System.out.println("Ottengo il film in posizione 0 della lista");
		Film f = gestore.getFilm(0);
		System.out.println("Film : " + f);
	}
}
