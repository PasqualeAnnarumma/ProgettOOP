package Starter;

import javax.swing.JFrame;

import Eccezioni.AccountGiaEsistenteException;
import GestoreLogin.Cinema;
import InterfacceGrafiche.LoginFrame;

public class Starter {

	public static void main(String[] args)
	{
		Cinema cinema = new Cinema();
		try {
			cinema.registraCliente("user", "123");
			cinema.registraAmministratore("root", "123");
		} catch (AccountGiaEsistenteException e) {
			System.out.println(e);
		}
		
		cinema.aggiungiSala(5, 10);
		cinema.aggiungiSala(8, 10);
		cinema.aggiungiSala(3, 2);
		
		LoginFrame login = new LoginFrame(cinema);
		login.setLocation(500, 100);
		login.setSize(350, 300);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);
	}

}
