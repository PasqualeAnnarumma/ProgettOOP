package tester;

import java.util.ArrayList;
import java.util.Calendar;

import gestoreLogin.Cliente;
import gestoreLogin.Cliente.Categoria;
import gestoreProgrammazione.Film;
import gestoreProgrammazione.Spettacolo;
import gestoreSale.Sala;
import gestoreSconti.GestoreSconti;
import gestoreSconti.Scontatore;
import gestoreSconti.Sconto;

public class TesterGestoreSconti {

	public static void main(String[] args)
	{
		GestoreSconti gestore = new GestoreSconti();
		Scontatore<Cliente> scontoEta = (Cliente cliente) -> {
			if (cliente.getEta() < 12) return 0.25f;
			return 0;
		};
		
		Scontatore<Cliente> scontoCompleanno = (Cliente cliente) -> {
			Calendar today = Calendar.getInstance();
			String dataOggi = today.get(Calendar.DAY_OF_MONTH) + "/" + (today.get(Calendar.MONTH) + 1);
			String dataCliente = cliente.getDataNascita().substring(0, 5);
			if (dataOggi.equals(dataCliente)) return 1;
			return 0;
		};
		
		Scontatore<Spettacolo> scontoNatale = (Spettacolo spett) -> {
			Calendar cal = spett.getData();
			int giorno = cal.get(Calendar.DAY_OF_MONTH);
			int mese = cal.get(Calendar.MONTH) + 1;
			String data = giorno + "/" + mese;
			if (data.equals("25/12")) return 0.3f;
			return 0;
		};
		
		Scontatore<Spettacolo> scontoMercoledì = (Spettacolo spett) -> {
			Calendar c = spett.getData();
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) return 0.15f;
			return 0;
		};
		
		Scontatore<Film> scontoFilmsacchetto = (Film f) -> {
			if (f.getNome().equals("Un sacchetto pieno di biglie")) return 0.4f;
			return 0;
		};
		
		Scontatore<Cliente> scontoStudenti = (Cliente c) -> {
			if (c.getCategoria().equals(Categoria.STUDENTE)) return 0.10f;
			return 0;
		};
		
		Sconto<Cliente> scontoCliente = new Sconto<Cliente>(scontoEta, "Minori 12 anni - 25%");
		gestore.aggiungiScontoCliente(scontoCliente);
		scontoCliente = new Sconto<Cliente>(scontoCompleanno, "Sconto compleanno - 100%");
		gestore.aggiungiScontoCliente(scontoCliente);
		scontoCliente = new Sconto<Cliente>(scontoStudenti, "Sconto studenti - 10%");
		gestore.aggiungiScontoCliente(scontoCliente);
		
		Sconto<Spettacolo> scontoSpettacolo = new Sconto<Spettacolo>(scontoNatale, "Sconto di natale - 30%");
		gestore.aggiungiScontoSpettacolo(scontoSpettacolo);
		scontoSpettacolo = new Sconto<Spettacolo>(scontoMercoledì, "Sconto Mercoledì - 15%");
		gestore.aggiungiScontoSpettacolo(scontoSpettacolo);
		
		Sconto<Film> scontoFilm = new Sconto<Film>(scontoFilmsacchetto, "Sonto sul film \"Un sacchetto pieno di biglie\" - 40%");
		gestore.aggiungiScontoFilm(scontoFilm);
		
		System.out.println("Ottengo la lista degli sconti per gli spettacoli");
		ArrayList<Sconto<Spettacolo>> scontiSpettacoli = gestore.getScontiSpettacolo();
		System.out.println("--- Inizio lista ---");
		for (Sconto<Spettacolo> s : scontiSpettacoli)
			System.out.println(s);
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Ottengo la lista degli sconti per i clienti");
		ArrayList<Sconto<Cliente>> scontiCliente = gestore.getScontiCliente();
		System.out.println("--- Inizio lista ---");
		for (Sconto<Cliente> s : scontiCliente)
			System.out.println(s);
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Ottengo la lista degli sconti per i film");
		ArrayList<Sconto<Film>> scontiFilm = gestore.getScontiFilm();
		System.out.println("--- Inizio lista ---");
		for (Sconto<Film> s : scontiFilm)
			System.out.println(s);
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Ottengo il primo sconto spettacolo");
		Sconto<Spettacolo> sconto = gestore.getScontoSpettacolo(0);
		System.out.println("Sconto cliente : " + sconto);
		System.out.println("Lo cancello");
		gestore.rimuoviScontoSpettacolo(0);
		
		System.out.println("Ottengo il primo sconto cliente");
		Sconto<Cliente> sconto2 = gestore.getScontoCliente(0);
		System.out.println("Sconto cliente : " + sconto2);
		System.out.println("Lo cancello");
		gestore.rimuoviScontoCliente(0);
		
		System.out.println("Ottengo il primo sconto film");
		Sconto<Film> sconto3 = gestore.getScontoFilm(0);
		System.out.println("Sconto cliente : " + sconto3);
		
		System.out.println("Creo un cliente");
		Cliente cliente = new Cliente("user", "123", "08/03/1997", Categoria.STUDENTE);
		System.out.println("Creo uno spettacolo");
		Sala sala = new Sala(0, 10, 10);
		Film film = new Film("Inception", "2:30", "Regista", "Inception");
		Spettacolo spettacolo = new Spettacolo(sala, film, 31, 12, 2018, "20:30", 10);
		System.out.println("Cerco lo sconto più conveniente per il cliente \"" + cliente.getUsername() + "\"");
		System.out.println(gestore.cercaSconto(cliente, spettacolo));
		
		System.out.println("Il cliente \"" + cliente.getUsername() + "\" utilizza lo sconto");
		cliente.addPrenotazione();
		
		System.out.println("Cerco lo sconto più conveniente per il cliente \"" + cliente.getUsername() + "\"");
		System.out.println(gestore.cercaSconto(cliente, spettacolo));
		
		System.out.println("Cancello lo sconto per il film");
		gestore.rimuoviScontoFilm(0);
	}
}
