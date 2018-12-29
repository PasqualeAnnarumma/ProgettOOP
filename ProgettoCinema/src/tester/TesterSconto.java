package tester;

import GestoreLogin.Cliente;
import GestoreLogin.Cliente.Categoria;
import GestoreSconti.Scontatore;
import GestoreSconti.Sconto;

public class TesterSconto {

	public static void main(String[] args)
	{
		System.out.println("Creo uno sconto");
		
		Scontatore<Cliente> scontoEta = (Cliente cliente) -> {
			if (cliente.getEta() < 12) return 0.25f;
			return 0;
		};
		
		Sconto<Cliente> sconto = new Sconto<Cliente>(scontoEta, "Minori 12 anni - 25%");
		System.out.println("Sconto creato : " + sconto);
		System.out.println("Controllo se è attivo : " + sconto.isAttivo());
		System.out.println("Descrizione : " + sconto.getDescrizione());
		System.out.println("Creo un cliente per calcolare lo sconto");
		Cliente cliente = new Cliente("user", "123", "08/03/2010", Categoria.STUDENTE);
		System.out.println("cliente : " + cliente.getUsername() + ", " + cliente.getEta() + " anni");
		System.out.println("Calcolo lo sconto : " + sconto.calcolaSconto(cliente));
		System.out.println("Disattivo lo sconto");
		sconto.disattiva();
		System.out.println(sconto);
		System.out.println("Riattivo lo sconto");
		sconto.attiva();
		System.out.println(sconto);
	}
}
