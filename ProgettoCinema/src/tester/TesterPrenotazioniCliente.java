package tester;

import java.util.ArrayList;

import GestoreLogin.Cliente;
import GestoreLogin.Cliente.Categoria;
import GestorePrenotazioni.Prenotazione;
import GestorePrenotazioni.PrenotazioniCliente;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;
import GestoreSale.Sala;

public class TesterPrenotazioniCliente {

	public static void main(String[] args)
	{
		System.out.println("Creo un cliente");
		Cliente cliente = new Cliente("user", "123", "08/03/1997", Categoria.STUDENTE);
		System.out.println("Cliente : " + cliente.getUsername());
		System.out.println("Creo PrenotazioniCliente");
		PrenotazioniCliente pc = new PrenotazioniCliente(cliente);
		System.out.println("Creo una prenotazione");
		Sala sala = new Sala(0, 15, 15);
		Film film = new Film("Inception", "2:30", "Regista", "Inception");
		Spettacolo spettacolo = new Spettacolo(sala, film, 31, 12, 2018, "20:30", 10);
		Posto posto = new Posto('A', 10);
		Prenotazione prenotazione = new Prenotazione(spettacolo, posto, cliente);
		System.out.println("Prenotazione : " + prenotazione);
		System.out.println("Aggiungo la prenotazione a PrenotazioniCliente");
		pc.aggiungiPrenotazione(prenotazione);
		
		System.out.println("Ottengo la lista di prenotazioni");
		ArrayList<Prenotazione> lista = pc.getListaPrenotazioni();
		System.out.println("--- Inizio lista ---");
		for (Prenotazione p : lista)
			System.out.println(p);
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Ottengo una singola prenotazione con il metodo getPrenotazione(i)");
		Prenotazione pren = pc.getPrenotazione(0);
		System.out.println("pren : " + pren);
		
		System.out.println("\nControllo se il cliente ha prenotato il posto + " + (posto.getRiga() + "") + posto.getColonna() + " per lo spettacolo " + spettacolo);
		Posto p = pc.searchPrenotazione(posto, spettacolo);
		System.out.println("posto : " + p);
		
		System.out.println("\nControllo se il cliente ha prenotato il posto \"null\" per lo spettacolo " + spettacolo);
		p = pc.searchPrenotazione(null, spettacolo);
		System.out.println("posto : " + p);
		
		System.out.println("Cliente proprietario delle prenotazioni : " + pc.getCliente());
		System.out.println("Numero di prenotazioni : " + pc.size());
		System.out.println("Rimuovo la prenotazione");
		pc.rimuoviPrenotazione(prenotazione);
		
		System.out.println("Ottengo la lista di prenotazioni");
		lista = pc.getListaPrenotazioni();
		System.out.println("--- Inizio lista ---");
		for (Prenotazione pr : lista)
			System.out.println(pr);
		System.out.println("--- Fine lista ---\n");
	}

}
