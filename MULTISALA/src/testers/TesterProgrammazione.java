package testers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import multisala.Film;
import multisala.Programmazione;
import multisala.Sala;
import multisala.Spettacolo;

public class TesterProgrammazione {

	public static void main(String[] args) {
		Sala sala=new Sala(0, 12, 12);
		Spettacolo s1=new Spettacolo(new Film("1","", 100, 10),sala , LocalDateTime.now());
		Spettacolo s2=new Spettacolo(new Film("2","", 100, 10),sala , LocalDateTime.now());
		Spettacolo s3=new Spettacolo(new Film("1","", 100, 10),sala , LocalDateTime.now());
		System.out.println("Istanzio una Programmazione e aggiungo tre spettacoli:");
		Programmazione programma=new Programmazione();
		programma.aggiungiSpettacolo(s1);
		programma.aggiungiSpettacolo(s2);
		programma.aggiungiSpettacolo(s3);
		System.out.println(programma);
		ArrayList<Spettacolo> lista=programma.cercaSpettacoliCriterio((e)->true);
		Spettacolo ritornato=programma.cercaSpettacolo(s1);
		System.out.println("Programmazione:");
		for(Spettacolo s: lista)
			System.out.println(s);
		if(ritornato.equals(s1))
			System.out.println("Oggetto trovato");
		System.out.println("Ricerca degli Spettacoli aventi titolo film 1");
		lista=programma.cercaSpettacoliCriterio((e)->e.getFilm().getTitolo().equals("1"));
		System.out.println("Spettacoli aventi titolo film 1:");
		for(Spettacolo s: lista)
			System.out.println(s);
		programma.ordinaSpettacoli((x,y)->x.getFilm().getTitolo().compareTo(y.getFilm().getTitolo()));
		System.out.println("\nOrdino gli Spettacoli in base al nome del film");
		System.out.println(programma);
		System.out.println("\nElimino uno spettacolo");
		programma.rimuoviSpettacolo(ritornato);
		lista=programma.cercaSpettacoliCriterio((e)->true);
		for(Spettacolo s: lista)
			System.out.println(s);
	}

}
