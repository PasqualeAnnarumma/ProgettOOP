package Starter;

import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Eccezioni.AccountGiaEsistenteException;
import GestoreLogin.Cinema;
import GestoreLogin.Cliente;
import GestoreLogin.Cliente.Categoria;
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
			cinema.registraCliente("user", "123", "08/03/1997", Categoria.NESSUNO);
			cinema.registraCliente("user2", "123", "23/12/2000", Categoria.STUDENTE);
			cinema.registraAmministratore("root", "123");
		} catch (AccountGiaEsistenteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
		}
		
		cinema.aggiungiSala(5, 10);
		cinema.aggiungiSala(8, 10);
		cinema.aggiungiSala(7, 8);

		
		Film film = new Film("Una poltrone per due", "2:05", "Regista1");
		Spettacolo spettacolo = new Spettacolo(gestoreSale.getListaSale().get(0), film, 27, 12, 2018, "06:02", 9.7);
		gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
		film = new Film("Il grinch", "1:35", "Regista2");
		spettacolo = new Spettacolo(gestoreSale.getListaSale().get(1), film, 29, 12, 2018, "23:30", 10.5);
		gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
		film = new Film("Inception", "0:15", "Regista3");
		spettacolo = new Spettacolo(gestoreSale.getListaSale().get(0), film, 28, 12, 2018, "20:30", 10.5);
		gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
		film = new Film("Un sacchetto pieno di biglie", "5:15", "Regista3");
		spettacolo = new Spettacolo(gestoreSale.getListaSale().get(2), film, 30, 12, 2018, "22:32", 25.0);
		gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
		
		/*for (int i = 0; i < pg.size(); i++)
			System.out.println(pg.getListaSpettacoli().get(i).getFilm().getNome());*/
		
		Scontatore<Cliente> scontoEta = (Cliente cliente) -> {
			if (cliente.getEta() < 12) return 0.25f;
			return 0;
		};
		
		Scontatore<Cliente> scontoCompleanno = (Cliente cliente) -> {
			Calendar today = Calendar.getInstance();
			String dataOggi = today.get(Calendar.DAY_OF_MONTH) + "/" + (today.get(Calendar.MONTH) + 1);
			String dataCliente = cliente.getCompleanno().substring(0, 5);
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
		gestoreSconti.aggiungiScontoCliente(scontoCliente);
		scontoCliente = new Sconto<Cliente>(scontoCompleanno, "Sconto compleanno - 100%");
		gestoreSconti.aggiungiScontoCliente(scontoCliente);
		scontoCliente = new Sconto<Cliente>(scontoStudenti, "Sconto studenti - 10%");
		gestoreSconti.aggiungiScontoCliente(scontoCliente);
		
		Sconto<Spettacolo> scontoSpettacolo = new Sconto<Spettacolo>(scontoNatale, "Sconto di natale - 30%");
		gestoreSconti.aggiungiScontoSpettacolo(scontoSpettacolo);
		scontoSpettacolo = new Sconto<Spettacolo>(scontoMercoledì, "Sconto Mercoledì - 15%");
		gestoreSconti.aggiungiScontoSpettacolo(scontoSpettacolo);
		
		Sconto<Film> scontoFilm = new Sconto<Film>(scontoFilmsacchetto, "Sonto sul film \"Un sacchetto pieno di biglie\" - 40%");
		gestoreSconti.aggiungiScontoFilm(scontoFilm);		
		
		LoginFrame login = new LoginFrame(cinema);
		login.setLocation(500, 100);
		login.setSize(350, 300);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);
		login.pack();
	}

}
