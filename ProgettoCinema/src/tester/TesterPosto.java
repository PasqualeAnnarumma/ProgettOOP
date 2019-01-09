package tester;

import eccezioni.PostoNonDisponibileException;
import gestoreSale.Posto;

public class TesterPosto {

	public static void main(String[] args)
	{
		Posto posto = new Posto('A', 10);
		System.out.println("Riga posto : " + posto.getRiga());
		System.out.println("Colonna posto : " + posto.getColonna());
		System.out.println("Controllo se è occupato : " + posto.isOccupato());
		System.out.println("Controllo se è disponibile : " + posto.isDisponibile());
		System.out.println("Controllo se è acquistato : " + posto.isAcquistato());
		System.out.println("posto : " + posto);
		
		try {
			System.out.println("Occupo il posto");
			posto.occupaPosto();
			System.out.println("posto : " + posto);
			System.out.println("Acquisto il posto");
			posto.acquistaPosto();
			System.out.println("Provo ad acquistare un posto gia acquistato");
			posto.acquistaPosto();
			System.out.println("posto : " + posto);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		try {
			System.out.println("Provo ad occupare un posto già occupato");
			System.out.println("posto : " + posto);
			posto.occupaPosto();
		} catch (PostoNonDisponibileException e3) {
			System.err.println(e3.getMessage());
		}
		
		try {
			System.out.println("Libero il posto");
			posto.liberaPosto();
			System.out.println("posto : " + posto);
		} catch (PostoNonDisponibileException e2) {
			System.err.println(e2.getMessage());
		}
		System.out.println("posto : " + posto);
		
		System.out.println("Rendo indisponibile il posto");
		posto.rendiIndisponibile();
		System.out.println("posto : " + posto);
		try {
			System.out.println("Provo ad acquistare un posto indisponibile");
			posto.acquistaPosto();
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				System.err.println(e1.getMessage());
			}
		}
		
		try {
			System.out.println("Provo ad occupare un posto non disponibile");
			posto.occupaPosto();
			System.out.println("posto : " + posto);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Rendo disponibile il posto");
		posto.rendiDisponibile();
		System.out.println("posto : " + posto);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			System.err.println(e1.getMessage());
		}
		try {
			System.out.println("Provo a liberare un posto già libero");
			posto.liberaPosto();
			System.out.println("posto : " + posto);
		} catch (PostoNonDisponibileException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Clono il posto");
		Posto clone = posto.clone();
		System.out.println("Eseguo equals : " + posto.equals(clone));
		System.out.println("Eseguo equals con un oggetto nullo");
		System.out.println(posto.equals(null));
		System.out.println("Eseguo equals con un oggeto di tipo diverso (String)");
		String s = "";
		System.out.println(posto.equals(s));
		System.out.println("Creo un posto diverso");
		Posto posto2 = new Posto('B', 10);
		System.out.println("Eseguo equals su questi due posti");
		System.out.println(posto);
		System.out.println(posto2);
		System.out.println(posto.equals(posto2));
		
	}
}
