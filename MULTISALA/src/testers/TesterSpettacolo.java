package testers;

import java.time.LocalDateTime;
import java.time.Month;

import multisala.Film;
import multisala.Sala;
import multisala.Spettacolo;

public class TesterSpettacolo {

	public static void main(String[] args) {
		Spettacolo s=new Spettacolo(new Film("Mr. Bean", "Ancora lui", 90, 5), new Sala(1, 10, 10), LocalDateTime.of(2018, Month.DECEMBER, 8, 23, 07));
		System.out.println("Oggetto istanziato: "+s);
		System.out.println("Film: "+s.getFilm()+"\nDurata: "+s.getDurata()+"\nSala :"+s.getSala().getCodice()+"\nData:"+s.getData());
		System.out.println("\nOra creo un clone");
		Spettacolo cloned=s.clone();
		if(s.equals(cloned))
			System.out.println("Sono uguali ");
		System.out.println("Ora modifico lo spettacolo");
		s.setData(LocalDateTime.now());
		s.setFilm(new Film("Mr.Bean 2", "Sempre lui", 90, 6));
		s.setSala(new Sala(2, 23, 11));
		System.out.println("Spettacolo dopo la modifica:\nFilm: "+s.getFilm()+"\nDurata: "+s.getDurata()+"\nSala :"+s.getSala().getCodice()+"\nData:"+s.getData());
		if(!s.equals(cloned))
			System.out.println("I due spettacoli non sono più uguali ");
	}

}
