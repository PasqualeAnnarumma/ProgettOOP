package testers;

import multisala.Posto;
import multisala.Posto.Stato;

public class TesterPosto {

	public static void main(String[] args) {
		Posto x= new Posto("1A");
		System.out.print("Istanzio un oggetto posto: "+x+"\n");
		System.out.print("Creo un clone e lo modifico\n"+x+"\n");
		Posto cloned=x.clone();
		System.out.println("Clone :"+cloned);
		if(x.equals(cloned))
			System.out.println("I due oggetti sono uguali prima della modifica\n");
		cloned.setIdPosto("2C");
		if(!x.equals(cloned))
			System.out.println("I due oggetti non sono più uguali dopo della modifica");
		System.out.println("Clone :"+cloned+"\nx:"+x);
		System.out.println("Modifico ora lo stato del posto\nVecchio stato: "+x.getStato()+" del posto "+x.getIdPosto());
		x.setStato(Stato.VENDUTO);
		System.out.println("Nuovo stato: "+x.getStato());
	}

}
