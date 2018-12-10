package Starter;

import GestoreLogin.Cinema;

public class Starter {

	public static void main(String[] args)
	{
		Cinema cinema = new Cinema();
		cinema.registraUtente("Mariomamo", "Ciaociao123");
		if(cinema.login("Mariomamo", "Ciaociao123"))
			System.out.println("Login effettuato con successo");
		else
			System.out.println("Mbroz");
	}

}
