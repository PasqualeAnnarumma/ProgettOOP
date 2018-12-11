package Starter;

import javax.swing.JFrame;

import Eccezioni.AccountGiaEsistenteException;
import GestoreLogin.Cinema;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.GestoreProgrammazione;
import GestoreProgrammazione.ProgrammaSettimanale;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.GestoreSale;
import InterfacceGrafiche.LoginFrame;

public class Starter {

	public static void main(String[] args)
	{
		Cinema cinema = new Cinema();
		GestoreSale gestoreSale = cinema.getGestoreSale();
		GestoreProgrammazione gestoreProgrammazione = cinema.getGestoreProgrammazione();
		ProgrammaSettimanale pg = new ProgrammaSettimanale();
		gestoreProgrammazione.aggiungiProgrammaSettimanale(pg);
		try {
			cinema.registraCliente("user", "123");
			cinema.registraAmministratore("root", "123");
		} catch (AccountGiaEsistenteException e) {
			System.out.println(e);
		}
		
		cinema.aggiungiSala(5, 10);
		cinema.aggiungiSala(8, 10);
		cinema.aggiungiSala(3, 2);
		
		Film film = new Film("Una poltrone per due", "2:05", "Mario");
		Spettacolo spettacolo = new Spettacolo(gestoreSale.getListaSale().get(0), film, "11/12/2018", "20:30", 9.7);
		pg.aggiungiSpettacolo(spettacolo);
		film = new Film("Il grinch", "1:35", "Pako");
		spettacolo = new Spettacolo(gestoreSale.getListaSale().get(0), film, "12/12/2018", "20:30", 9.7);
		pg.aggiungiSpettacolo(spettacolo);
		film = new Film("Chiamatemi Bisogno", "0:15", "Antonio Bisogno");
		spettacolo = new Spettacolo(gestoreSale.getListaSale().get(0), film, "25/12/2018", "20:30", 5.0);
		pg.aggiungiSpettacolo(spettacolo);
		
		/*for (int i = 0; i < pg.size(); i++)
			System.out.println(pg.getListaSpettacoli().get(i).getFilm().getNome());*/
		
		
		LoginFrame login = new LoginFrame(cinema);
		login.setLocation(500, 100);
		login.setSize(350, 300);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);
	}

}
