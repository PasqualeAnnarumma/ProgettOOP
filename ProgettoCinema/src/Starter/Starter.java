package Starter;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Eccezioni.AccountGiaEsistenteException;
import GestoreLogin.Cinema;
import GestoreLogin.Cliente;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.GestoreProgrammazione;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.GestoreSale;
import GestoreSconti.GestoreSconti;
import GestoreSconti.Scontatore;
import GestoreSconti.Sconto;
import InterfacceGrafiche.LoginFrame;

public class Starter {

	public static void main(String[] args)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		Cinema cinema = new Cinema();
		GestoreSale gestoreSale = cinema.getGestoreSale();
		GestoreProgrammazione gestoreProgrammazione = cinema.getGestoreProgrammazione();
		GestoreSconti gestoreSconti = cinema.getGestoreSconti();
		
		try {
			cinema.registraCliente("user", "123", 21);
			cinema.registraAmministratore("root", "123");
		} catch (AccountGiaEsistenteException e) {
			System.out.println(e);
		}
		
		cinema.aggiungiSala(5, 10);
		cinema.aggiungiSala(8, 10);
		cinema.aggiungiSala(3, 2);

		
		Film film = new Film("Una poltrone per due", "2:05", "Regista1");
		Spettacolo spettacolo = new Spettacolo(gestoreSale.getListaSale().get(0), film, 25, 12, 2018, "06:02", 9.7);
		gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
		film = new Film("Il grinch", "1:35", "Regista2");
		spettacolo = new Spettacolo(gestoreSale.getListaSale().get(1), film, 22, 12, 2018, "20:30", 9.7);
		gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
		film = new Film("Inception", "0:15", "Regista3");
		spettacolo = new Spettacolo(gestoreSale.getListaSale().get(0), film, 27, 12, 2018, "20:30", 5.0);
		gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
		film = new Film("Un sacchetto pieno di biglie", "5:15", "Regista3");
		spettacolo = new Spettacolo(gestoreSale.getListaSale().get(2), film, 24, 12, 2018, "20:30", 25.0);
		gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
		
		/*for (int i = 0; i < pg.size(); i++)
			System.out.println(pg.getListaSpettacoli().get(i).getFilm().getNome());*/
		
		Scontatore<Cliente> scontoEta = (Cliente cliente) -> {
			if (cliente.getEta() < 18) return 0.25f;
			return 0;
		};
		
		Scontatore<Cliente> scontoEta2 = (Cliente cliente) -> {
			if (cliente.getEta() < 20) return 0.30f;
			return 0;
		};
		
		Scontatore<Cliente> scontoEta3 = (Cliente cliente) -> {
			if (cliente.getEta() <= 18) return 0.50f;
			return 0;
		};
		
		Scontatore<Spettacolo> scontoNatale = (Spettacolo spett) -> {
			if (spett.stringDate().equals("25/12/2018")) return 0.3f;
			return 0;
		};
		
		Scontatore<Film> scontoFilmsacchetto = (Film f) -> {
			if (f.getNome().equals("Un sacchetto pieno di biglie")) return 0.4f;
			return 0;
		};
		
		Sconto<Cliente> scontoCliente = new Sconto<Cliente>(scontoEta);
		gestoreSconti.aggiungiScontoCliente(scontoCliente);
		scontoCliente = new Sconto<Cliente>(scontoEta2);
		gestoreSconti.aggiungiScontoCliente(scontoCliente);
		scontoCliente = new Sconto<Cliente>(scontoEta3);
		gestoreSconti.aggiungiScontoCliente(scontoCliente);
		Sconto<Spettacolo> scontoSpettacolo = new Sconto<Spettacolo>(scontoNatale);
		Sconto<Film> scontoFilm = new Sconto<Film>(scontoFilmsacchetto);
		
		gestoreSconti.aggiungiScontoSpettacolo(scontoSpettacolo);
		gestoreSconti.aggiungiScontoFilm(scontoFilm);
		
		LoginFrame login = new LoginFrame(cinema);
		login.setLocation(500, 100);
		login.setSize(350, 300);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);
		login.pack();
	}

}
