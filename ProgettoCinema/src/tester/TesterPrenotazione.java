package tester;

import GestoreLogin.Cliente;
import GestoreLogin.Cliente.Categoria;
import GestorePrenotazioni.Prenotazione;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;
import GestoreSale.Sala;

public class TesterPrenotazione {

	public static void main(String[] args)
	{
		System.out.println("Creo la prenotazione");
		Sala sala = new Sala(0, 15, 15);
		Film film = new Film("Inception", "2:30", "Regista", "Inception");
		Spettacolo spettacolo = new Spettacolo(sala, film, 31, 12, 2018, "20:30", 10);
		Posto posto = new Posto('A', 10);
		Cliente cliente = new Cliente("user", "123", "08/03/1997", Categoria.STUDENTE);
		Prenotazione prenotazione = new Prenotazione(spettacolo, posto, cliente);
		System.out.println("Prenotazione creata : " + prenotazione);
		System.out.println("Cliente : " + prenotazione.getCliente());
		System.out.println("Posto : " + prenotazione.getPosto());
		System.out.println("Spettacolo : " + prenotazione.getSpettacolo());
		System.out.println("Controllo se è stato pagato (isPagato)");
		System.out.println("isPagato : " + prenotazione.isPagato());
		System.out.println("Lo pago");
		prenotazione.setPagato();
		System.out.println("isPagato : " + prenotazione.isPagato());
		System.out.println("Prezzo pagato : " + prenotazione.getPrezzoPagato());
		System.out.println("Creo una nuova prenotazione");
		Prenotazione prenotazione2 = new Prenotazione(spettacolo, posto, cliente);
		System.out.println("Controllo se sono uguali");
		System.out.println(prenotazione.equals(prenotazione2));
		System.out.println("Imposto la prima prenotazione come non pagata");
		prenotazione.setNonPagato();
		System.out.println("Controllo se sono uguali");
		System.out.println(prenotazione.equals(prenotazione2));
		Posto posto2 = new Posto('B', 10);
		System.out.println("Cambio il posto della seconda prenotazione con " + (posto2.getRiga() + "") + posto2.getColonna());
		prenotazione2.setPosto(posto2);
		System.out.println(prenotazione);
		System.out.println(prenotazione2);
		System.out.println("Controllo se sono uguali");
		System.out.println(prenotazione.equals(prenotazione2));
		System.out.println("Faccio il confronto con un oggetto nullo");
		System.out.println(prenotazione.equals(null));
		System.out.println("Faccio il confronto con un oggetto di tipo diverso");
		String s = "";
		System.out.println(prenotazione.equals(s));
	}
}
