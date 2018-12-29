package tester;

import GestoreLogin.Utente;

public class TesterUtente {

	public static void main(String[] args)
	{
		System.out.println("Creo un utente");
		Utente utente = new Utente("u", "123");
		System.out.println("Username dell'utente creato : " + utente.getUsername());
		System.out.println("Password dell'utente creato : " + utente.getPassword());
		System.out.println("Clono l'utente");
		Utente clone = utente.clone();
		System.out.println("Confronto l'utente originale con il clone");
		System.out.println(utente.equals(clone));
		System.out.println("utente : " + utente.getUsername() + ", " + utente.getPassword() +
						   "\nclone : " + clone.getUsername() + ", " + clone.getPassword());
		System.out.println("Cambio la password dell'utente originale");
		utente.setPassword("nuovaPassword");
		System.out.println("utente : " + utente.getUsername() + ", " + utente.getPassword() +
				           "\nclone : " + clone.getUsername() + ", " + clone.getPassword());
		System.out.println("Rieseguo il confronto tra l'utente originale e il clone");
		System.out.println(utente.equals(clone));
		System.out.println("Eseguo il confronto con un oggetto nullo");
		System.out.println(utente.equals(null));
		System.out.println("Eseguo il confronto con un oggetto di tipo diverso");
		String s = "Ciao";
		System.out.println(utente.equals(s));
	}

}
