package GestoreLogin;

public class Cliente extends Utente {
	
	private int eta;
	private String compleanno;
	int prenotazioni;
	
	public Cliente(String usr, String pwd, int eta, String birth) {
		super(usr, pwd);
		this.eta = eta;
		compleanno = birth;
		prenotazioni = 0;
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
		prenotazioni--;
	}
}
