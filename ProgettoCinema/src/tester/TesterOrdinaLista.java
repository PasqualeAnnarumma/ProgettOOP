package tester;

import java.util.ArrayList;
import GestoreLogin.Comparatore;
import GestoreLogin.OrdinaLista;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Sala;

public class TesterOrdinaLista {

	public static void main(String[] args)
	{
		System.out.println("Creo un coparatore per ordine cronologico");
		Comparatore<Spettacolo> ordineCronologico = (Spettacolo s1, Spettacolo s2) -> {
			if (s1.stringDate().compareTo(s2.stringDate()) < 0) return -1;
			if (s1.stringDate().compareTo(s2.stringDate()) > 0) return 1;
			return s1.getOra().compareTo(s2.getOra());
		};
		
		System.out.println("Creo le sale, i film e gli spettacoli");
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
		
		Spettacolo[] spettacoli = {new Spettacolo(sale[0], film[0], 31, 12, 2018, "20:30", 10.5),
								   new Spettacolo(sale[1], film[1], 24, 12, 2018, "22:30", 8.5),
								   new Spettacolo(sale[2], film[2], 28, 12, 2018, "16:45", 9.5),
								   new Spettacolo(sale[2], film[1], 31, 12, 2018, "22:30", 10)};
		System.out.println("Stampo gli spettacoli");
		System.out.println("--- Inizio stampa");
		for (int i = 0; i < spettacoli.length; i++)
			System.out.println(spettacoli[i]);
		System.out.println("--- Fine stampa ---");
		
		OrdinaLista<Spettacolo> ordinatore = new OrdinaLista<>(ordineCronologico);
		for (int i = 0; i < spettacoli.length; i++)
			ordinatore.add(spettacoli[i]);
		
		System.out.println("Ottengo la lista degli spettacoli ordinata cronologicamente");
		ArrayList<Spettacolo> lista = ordinatore.getList();
		for (Spettacolo s : lista)
			System.out.println(s);
	}
}
