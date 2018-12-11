package testers;

import multisala.Film;

public class TesterFilm {
	public static void main(String [] args) {
	Film f=new Film("Animali Fantastici 2","Seguito attesissimo del primo", 110, 6);
	System.out.println("Oggetto appena istanziato: "+f);
	System.out.println("Titolo: "+f.getTitolo()+"\nDescrizione: "+f.getDescrizione()+"\nDurata: "+f.getDurata()+"\nPrezzo: "+f.getPrezzo());
	System.out.println("\nOra aumento l'incasso e il prezzo del film\nIncasso attuale del film: "+f.getIncassoFilm());
	f.setPrezzo(10);
	f.addIncassoFilm(10);
	System.out.println("Prezzo: "+f.getPrezzo()+"\nIncasso: "+f.getIncassoFilm());
	f.resetIncassoFilm();
	if(f.getIncassoFilm()==0)
		System.out.println("Incasso resettato");
	Film f2=f.clone();
	if(f2.equals(f))
		System.out.println("Ho creato un clone e ora lo modifico");
	f2.setDescrizione("In arrivo a dicembre");
	f2.setNome("Dragon Ball Super: Broly");
	if(!f2.equals(f))
		System.out.println("Non sono più uguali: \nExClone: "+f2+"\nF:"+f);
	}
}
