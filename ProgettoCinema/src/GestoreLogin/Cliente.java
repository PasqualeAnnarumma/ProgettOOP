package GestoreLogin;

import java.util.Calendar;

public class Cliente extends Utente {
	
	public enum Categoria {
		NESSUNO(), STUDENTE(), PENSIONATO();
	};
	
	private int eta;
	private String compleanno;
	private Categoria categoria;
	private int prenotazioni;
	
	public Cliente(String usr, String pwd, String birth, Categoria category) {
		super(usr, pwd);
		compleanno = birth;
		prenotazioni = 0;
		categoria = category;
		calcolaEta(birth);
	}
	
	public void calcolaEta(String birth) {
		Calendar today = Calendar.getInstance();
		int giorno = Integer.parseInt(compleanno.substring(0, 2));
		int mese = Integer.parseInt(compleanno.substring(3, 5));
		int anno = Integer.parseInt(compleanno.substring(6, 10));
		int annoOggi = today.get(Calendar.YEAR);
		int meseOggi = today.get(Calendar.MONTH) + 1;
		int giornoOggi = today.get(Calendar.DAY_OF_MONTH);
		//System.out.println(giornoOggi + "/" + meseOggi + "/" + annoOggi);
		eta = annoOggi - anno;
		if (meseOggi < mese) eta -= 1;
		else if (giornoOggi < giorno) eta -= 1;
		//System.out.println(giorno + "/" + mese + "/" + anno + " --- " + eta);
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public int getEta() {
		return eta;
	}
	
	public String getCompleanno() {
		return compleanno;
	}
	
	public boolean haUsatoSconto() {
		if (prenotazioni == 0) return false;
		return true;
	}
	
	public void addPrenotazione() {
		prenotazioni++;
	}
	
	public void removePrenotazione() {
		if (prenotazioni > 0) prenotazioni--;
	}
}
