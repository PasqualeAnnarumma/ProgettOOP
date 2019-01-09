package tester;

import gestoreProgrammazione.Film;
import gestoreProgrammazione.Spettacolo;
import gestoreSale.Sala;

public class TesterSpettacolo {

	public static void main(String[] args)
	{
		System.out.println("Creo uno spettacolo");
		Sala sala = new Sala(0, 'A', 10);
		Film film = new Film("Inception", "2:30", "Regista", "Inception");
		Spettacolo spettacolo = new Spettacolo(sala, film, 31, 12, 2018, "22:30", 7.5);
		System.out.println("Sala spettacolo : " + spettacolo.getSala());
		System.out.println("Film : " + spettacolo.getFilm());
		System.out.println("Data : " + spettacolo.getData());
		System.out.println("Posti disponibili : " + spettacolo.getPostiDisponibili());
		System.out.println("Posti liberi : " + spettacolo.getPostiLiberi());
		System.out.println("stringDate : " + spettacolo.stringDate());
		System.out.println("Ora : " + spettacolo.getOra());
		System.out.println("Prezzo : " + spettacolo.getPrezzo());
		System.out.println("isPrenotable : " + spettacolo.isPrenotable());
		System.out.println("Clono lo spettacolo");
		Spettacolo s2 = spettacolo.clone();
		System.out.println("spettacolo.equals(s2) : " + spettacolo.equals(s2));
		System.out.println("s2 : " + s2);
		System.out.println("spettacolo : " + spettacolo);
		System.out.println("compareCalendar : " + spettacolo.compareCalendar(spettacolo.getData(), s2.getData()));
		System.out.println("Eseguo il confronto con un elemento nullo");
		System.out.println(spettacolo.equals(null));
		System.out.println("Eseguo il confronto con un oggetto di tipo diverso");
		String s = "";
		System.out.println(spettacolo.equals(s));
		System.out.println("Eseguo il confronto con uno spettacolo diverso");
		s2 = new Spettacolo(sala, film, 30, 12, 2018, "8:30", 10);
		System.out.println(spettacolo.equals(s2));
		System.out.println("Eseguo compareCalendar su questi due spettacoli");
		System.out.println(spettacolo);
		System.out.println(s2);
		System.out.println("Risultato : " + spettacolo.compareCalendar(spettacolo.getData(), s2.getData()));
		System.out.println("Creo uno spettacolo non prenotabile");
		Spettacolo s3 = new Spettacolo(sala, film, 29, 12, 2018, "00:00", 10);
		System.out.println("Eseguo isPrenotable su " + s3);
		System.out.println(s3.isPrenotable());
		System.out.println("Eseguo isFruibile su " + s3);
		System.out.println(s3.isFruibile());
	}
}
