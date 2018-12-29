package tester;

import GestoreLogin.Cliente;
import GestoreLogin.Cliente.Categoria;

public class TesterCliente {
	
	public static void main(String args[])
	{
		System.out.println("Creo un cliente");
		Cliente cliente = new Cliente("cliente", "123", "30/03/1997", Categoria.STUDENTE);
		System.out.println("Categoria del cliente : " + cliente.getCategoria());
		System.out.println("Età del cliente : " + cliente.getEta());
		System.out.println("Data di nascita del cliente : " + cliente.getDataNascita());
		System.out.println("Incremento il conteggio delle prenotazioni");
		cliente.addPrenotazione();
		System.out.println("Controllo se il cliente ha usufruito dello sconto");
		System.out.println(cliente.haUsatoSconto());
		System.out.println("Decremento il conteggio delle prenotazioni");
		cliente.removePrenotazione();
		System.out.println("Controllo se il cliente ha usufruito dello sconto");
		System.out.println(cliente.haUsatoSconto());
		System.out.println("Decremento il conteggio delle prenotazioni");
		cliente.removePrenotazione();
		
	}
	
}
