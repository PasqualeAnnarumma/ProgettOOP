package testers;

import java.time.LocalDateTime;

import multisala.Film;
import multisala.Sala;
import multisala.Spettacolo;
import sconti.Sconto;
import sconti.ScontoMercoledi;
import sconti.ScontoOver60;
import sconti.ScontoStudenti;
import sconti.ScontoUnder14;
import utente.Account.Livello;
import utente.AccountCliente;
import utente.AccountCliente.Gruppo;

public class TesterSconto {

	public static void main(String[] args) {
		Sconto under14=new ScontoUnder14(10, false),over60=new ScontoOver60(30),studenti=new ScontoStudenti(40, true),mer=new ScontoMercoledi(20);
		AccountCliente c=new AccountCliente("Giorgio", "pass",Livello.CLIENTE,Gruppo.STUDENTE,"Carta12345678910192921");
		Spettacolo s=new Spettacolo(new Film("Avengers", "EndGame", 120, 20), new Sala(1, 20, 10),LocalDateTime.now());
		System.out.println("Istanziato le quattro politiche:\n"+under14+"\n"+over60+"\n"+studenti+"\n"+mer);
		System.out.println("Ora vediamo se risultano applicabili");
		if(!under14.applicabile(c, s))
			System.out.println("Lo sconto "+under14.getNomeSconto()+" "+under14.getDescrizione()+" non è applicabile");
		if(!over60.applicabile(c, s))
			System.out.println("Lo sconto "+over60.getNomeSconto()+" "+over60.getDescrizione()+" non è applicabile");
		if(!mer.applicabile(c, s))
			System.out.println("Lo sconto "+mer.getNomeSconto()+" "+mer.getDescrizione()+" non è applicabile");
		if(studenti.applicabile(c, s))
			System.out.println("Lo sconto "+studenti.getNomeSconto()+" "+studenti.getDescrizione()+" è applicabile");
		System.out.println("Il prezzo da pagare è "+s.getFilm().getPrezzo()+"\nIl prezzo scontato è "+studenti.getsconto(s.getFilm().getPrezzo()));
		System.out.println("Ora clono la politica di sconto e cambio lo stato");
		Sconto cloned=studenti.clone();
		cloned.disattiva();
		System.out.println("Clone: "+cloned+"\nStudenti: "+studenti);
		if(studenti.equals(cloned))
			System.out.println("Sono uguali");
		cloned.attiva();
		System.out.println("Lo stato di studenti è "+studenti.getStato());
		System.out.println("Ora lo disattivo");
		studenti.disattiva();
		System.out.println("Lo stato di studenti è "+studenti.getStato());
		System.out.println("Ora lo riattivo");
		studenti.attiva();
		System.out.println("Lo stato di studenti è "+studenti.getStato());
		
	}

}
