package tester;

import java.util.ArrayList;

import Eccezioni.AccountGiaEsistenteException;
import GestoreLogin.Cliente.Categoria;
import GestoreLogin.Cliente;
import GestoreLogin.GestoreLogin;
import GestoreLogin.Utente;

public class TesterGestoreLogin {

	public static void main(String[] args)
	{
		System.err.println("Creo il gestore dei login");
		GestoreLogin gestore = new GestoreLogin();
		try {
			System.out.println("Registro un amministratore e un cliente");
			gestore.aggiungiCliente("cliente", "123", "08/03/1997", Categoria.STUDENTE);
			gestore.aggiungiAmministratore("admin", "123");
		} catch (AccountGiaEsistenteException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Registro un utente già esistente");
		try {
			gestore.aggiungiCliente("cliente", "123", "08/03/1997", Categoria.NESSUNO);
		} catch (AccountGiaEsistenteException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("\nProvo ad effettuare il login per il cliente");
		Utente login = gestore.login("cliente", "123");
		if (login != null) System.out.println("Login effettuato con successo");
		else System.out.println("Errore login");
		System.out.println("\nProvo ad effettuare il login con un utente che non esiste");
		Utente ges = gestore.login("c", "");
		gestore.remove(ges);
		if (login != null) System.out.println("Login effettuato con successo");
		else System.out.println("Errore login");
		System.out.println("Ottengo la lista dei clienti");
		ArrayList<Cliente> clienti = gestore.getListaClienti();
		System.out.println("--- Inizio lista ---");
		for (Cliente c : clienti)
			System.out.println(c.getUsername());
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Rimuovo il cliente");
		gestore.remove(login);
		System.out.println("Ottengo la lista dei clienti");
		clienti = gestore.getListaClienti();
		System.out.println("--- Inizio lista ---");
		for (Cliente c : clienti)
			System.out.println(c.getUsername());
		System.out.println("--- Fine lista ---\n");
	}
}