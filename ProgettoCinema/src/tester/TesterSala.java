package tester;

import java.util.ArrayList;

import GestoreSale.Posto;
import GestoreSale.Sala;

public class TesterSala {

	public static void main(String[] args)
	{
		System.out.println("Creo una sala");
		Sala sala = new Sala(0, 10, 20);
		
		System.out.println("Numero sala : " + sala.getNumeroSala());
		System.out.println("Righe sala : " +sala.getRighe());
		System.out.println("Colonne sala : " + sala.getColonne());
		System.out.println("Posti disponibili : " + sala.getPostiDisponibili());
		System.out.println("Posti totali : " + sala.getPostiTotali());
		System.out.println("Posti occupati : " + sala.getPostiOccupati());
		System.out.println("Recupero la matrice di posti dalla sala");
		Posto[][] posti = sala.getPosti();
		System.out.println("Stampo i posti");
		for (int i = 0; i < sala.getRighe(); i++)
		{
			for (int j = 0; j < sala.getColonne(); j++)
				System.out.print(posti[i][j].isDisponibile() + " ");
			System.out.println();
		}
		System.out.println("Ottengo un clone del posto A4");
		Posto clone = sala.getPosto(0, 4);
		System.out.println(clone);
		System.out.println("Rendo indisponibile il posto");
		clone.rendiIndisponibile();
		System.out.println("Stampo i posti");
		for (int i = 0; i < sala.getRighe(); i++)
		{
			for (int j = 0; j < sala.getColonne(); j++)
				System.out.print(posti[i][j].isDisponibile() + " ");
			System.out.println();
		}
		System.out.println("Ottengo il posto reale A4");
		Posto p = sala.getPurePosto(0, 4);
		System.out.println(p);
		System.out.println("Rendo indisponibile il posto");
		p.rendiIndisponibile();;
		System.out.println("Stampo i posti");
		for (int i = 0; i < sala.getRighe(); i++)
		{
			for (int j = 0; j < sala.getColonne(); j++)
				System.out.print(posti[i][j].isDisponibile() + " ");
			System.out.println();
		}
		
		ArrayList<Posto> postiIndisponibili = sala.getListaPostiIndisponibili();
		System.out.println("Ottengo la lista dei posti indisponibili");
		System.out.println("--- Inizio lista ---");
		for (Posto po : postiIndisponibili)
			System.out.println(po);
		System.out.println("--- Fine lista ---\n");
		
		System.out.println("Eseguo un clone della sala");
		Sala cloneSala = sala.clone();
		System.out.println("sala : " + sala);
		System.out.println("cloneSala : " + cloneSala);
		System.out.println("Eseguo equals : " + sala.equals(cloneSala));
		System.out.println("Creo una nuova matrice di posti tutti settati a true");
		Posto[][] nuovi = new Posto[sala.getRighe()][sala.getColonne()];
		for(int i = 0; i < sala.getRighe(); i++)
		{
			char ch = (char) (i + 65);
			for(int j = 0; j < sala.getColonne(); j++)
				nuovi[i][j] = new Posto(ch, j);
		}
		System.out.println("Stampo la matrice di posti appena creata");
		for (int i = 0; i < sala.getRighe(); i++)
		{
			for (int j = 0; j < sala.getColonne(); j++)
				System.out.print(nuovi[i][j].isDisponibile() + " ");
			System.out.println();
		}
		
		System.out.println("Cambio la matrice di posti ottenuta prima con quella appena creata");
		sala.setPosti(nuovi);
		System.out.println("Stampo la matrice di posti effettiva della sala");
		for (int i = 0; i < sala.getRighe(); i++)
		{
			for (int j = 0; j < sala.getColonne(); j++)
				System.out.print(posti[i][j].isDisponibile() + " ");
			System.out.println();
		}
		
		System.out.println("Eseguo il confronto con un oggetto nullo");
		System.out.println(sala.equals(null));
		System.out.println("Eseguo il confronto con un oggetto di tipo diverso (String)");
		String s = "";
		System.out.println(sala.equals(s));
		Sala sala2 = new Sala(1, 20, 20);
		System.out.println("Eseguo il confronto con una sala diversa (" + sala2 + ")");
		System.out.println(sala.equals(sala2));
	}
}