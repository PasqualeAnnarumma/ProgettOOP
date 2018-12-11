package testers;

import multisala.Posto;
import multisala.Sala;

public class TesterSala {

	public static void main(String[] args) {
		Sala s=new Sala(1,10,5);
		System.out.println("Istanzio una nuova Sala di 10 file e 20 colonne\nSala: "+s);
		Sala cloned=s.clone();
		System.out.println("La sala "+s.getCodice()+" ha "+s.getNumeroPosti()+" posti totali di cui "+s.numeroPostiDisponibili()+" disponibili");
		System.out.println("Ha "+s.getNumeroFile()+" file e "+s.getPostiPerFila()+" posti per fila\n");
		System.out.println("Cerco un posto presente");
		if(s.cercaPosto(new Posto("A1"))!=null)
			System.out.println("Posto trovato"+s.cercaPosto(new Posto("A1")));
		System.out.println("Cerco posto non presente");
		if(s.cercaPosto(new Posto("12222A"))==null)
			System.out.println("Posto non trovato ");
		s.getPosti();
		System.out.println("Occupo un posto e lo libero\nPosti disponibili: "+s.numeroPostiDisponibili());
		s.occupaPosto();
		System.out.println("Posti disponibili: "+s.numeroPostiDisponibili());
		s.liberaPosto();
		System.out.println("Posti disponibili: "+s.numeroPostiDisponibili());
		System.out.println("\nCreo un clone");
		if(cloned.equals(s))
			System.out.println("Sono uguali");
		System.out.println("Clone: "+cloned+"\nS: "+s);
	}

}
