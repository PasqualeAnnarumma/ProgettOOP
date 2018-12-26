package GestoreSconti;

public class Sconto<T> {
	
	Scontatore<T> scontatore;
	String descrizione;
	boolean attivo;
	
	public Sconto(Scontatore<T> sc, String description) {
		scontatore = sc;
		descrizione = description;
		attivo = true;
	}
	
	public boolean isAttivo() {
		return attivo;
	}
	
	public void attiva() {
		attivo = true;
	}
	
	public void disattiva() {
		attivo = false;
	}
	
	public float calcolaSconto(T obj) {
		return scontatore.calcolaSconto(obj);
	}
	
	public String toString() {
		return getClass().getSimpleName() + "[attivo=" + attivo + "]";
	}
	
	public String getDescrizione() {
		return descrizione;
	}
}
