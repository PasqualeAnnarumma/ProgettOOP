package Starter;

import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Eccezioni.AccountGiaEsistenteException;
import cinema.Cinema;
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
		
		File file = new File("dati.txt");
		Cinema cin = new Cinema();
		if (file.exists())
		{
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				cin = (Cinema) in.readObject();
				in.close();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Errore durante l'apertura del file!", "Errore!", JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Errore durante l'apertura del file!", "Errore!", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			GestoreSale gestoreSale = cin.getGestoreSale();
			GestoreProgrammazione gestoreProgrammazione = cin.getGestoreProgrammazione();
			GestoreSconti gestoreSconti = cin.getGestoreSconti();
			
			try {
				cin.registraCliente("user", "123", "08/03/1997", Categoria.NESSUNO);
				cin.registraCliente("studente", "123", "23/12/2000", Categoria.STUDENTE);
				cin.registraCliente("pensionato", "123", "04/07/1961", Categoria.PENSIONATO);
				cin.registraAmministratore("root", "123");
			} catch (AccountGiaEsistenteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
			}
			
			cin.aggiungiSala(5, 10);
			cin.aggiungiSala(8, 10);
			cin.aggiungiSala(7, 8);

			
			Film film = new Film("Shutter island", "2:18", "Martin Scorsese", "Shutter island");
			Spettacolo spettacolo = new Spettacolo(gestoreSale.getListaSale().get(0), film, 29, 12, 2018, "06:02", 9.7);
			gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
			film = new Film("L'ora più buia", "2:50", "Joe Wright", "L'ora più buia");
			spettacolo = new Spettacolo(gestoreSale.getListaSale().get(1), film, 29, 12, 2018, "23:30", 10.5);
			gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
			film = new Film("Inception", "2:28", "Christopher Nolan", "Inception");
			spettacolo = new Spettacolo(gestoreSale.getListaSale().get(0), film, 30, 12, 2018, "20:30", 10.5);
			gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
			film = new Film("Un sacchetto pieno di biglie", "1:50", "Christian Duguay", "Un sacchetto pieno di biglie");
			spettacolo = new Spettacolo(gestoreSale.getListaSale().get(2), film, 30, 12, 2018, "22:32", 8.7);
			gestoreProgrammazione.aggiungiSpettacolo(spettacolo);
			
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
		}
				
		
		Cinema cinema = cin;
		LoginFrame login = new LoginFrame(cinema);
		login.setLocation(500, 100);
		login.setSize(350, 300);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		login.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			
			public void windowIconified(WindowEvent e) {}
			
			public void windowDeiconified(WindowEvent e) {}
			
			public void windowDeactivated(WindowEvent e) {}
			
			public void windowClosing(WindowEvent e) {
				//salva
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
					out.writeObject(cinema);
					out.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Errore durante il salvataggio del file!", "Errore!", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errore durante il salvataggio del file!", "Errore!", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			public void windowClosed(WindowEvent e) {}
			
			public void windowActivated(WindowEvent e) {}
		});
		
		login.setVisible(true);
		login.pack();
	}

}
