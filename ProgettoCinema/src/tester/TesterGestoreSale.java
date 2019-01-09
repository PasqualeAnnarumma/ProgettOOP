package tester;

import java.util.ArrayList;

import gestoreSale.GestoreSale;
import gestoreSale.Sala;

public class TesterGestoreSale {

	public static void main(String[] args)
	{
		System.out.println("Creo il gestore delle sale");
		GestoreSale gestore = new GestoreSale();
		Sala[] sale = {new Sala(0, 10, 10), new Sala(1, 20, 20), new Sala(2, 30, 30)};
		for (int i = 0; i < sale.length; i++)
		{
			System.out.println("Aggiungo la sala " + sale[i]);
			gestore.aggiungiSala(sale[i].getRighe(), sale[i].getColonne());
		}
		
		System.out.println("Ottengo la lista delle sale");
		ArrayList<Sala> listaSale = gestore.getListaSale();
		System.out.println("--- Inizio lista");
		for (int i = 0; i < listaSale.size(); i++)
			System.out.println(listaSale.get(i));
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Ottengo la prima sala della lista");
		System.out.println(gestore.getSala(0));
		System.out.println("Numero sale : " + gestore.size());
		System.out.println("Rimuovo la prima sala della lista");
		gestore.rimuoviSala(gestore.getListaSale().get(0));
		listaSale = gestore.getListaSale();
		System.out.println("--- Inizio lista");
		for (int i = 0; i < listaSale.size(); i++)
			System.out.println(listaSale.get(i));
		System.out.println("--- Fine lista ---\n");
	}
}
