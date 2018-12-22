package GestoreLogin;

public class Cliente extends Utente {
	
	int eta;
	
	public Cliente(String usr, String pwd, int eta) {
		super(usr, pwd);
		this.eta = eta;
	}
	
	public int getEta() {
		return eta;
	}
}
