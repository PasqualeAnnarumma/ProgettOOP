package GestoreLogin;

public class Cinema {
	
	GestoreLogin gestoreLogin;
	Utente utente;
	
	public Cinema() {
		gestoreLogin = new GestoreLogin();
	}
	
	public void registraUtente(String usr, String pwd) {
		Utente user = new Utente(usr, pwd);
		gestoreLogin.add(user);
	}
	
	public void registraAmministratore(String usr, String pwd) {
		Amministratore administrator = new Amministratore(usr, pwd);
		gestoreLogin.add(administrator);
	}
	
	public boolean login(String usr, String pwd) {
		utente = gestoreLogin.login(usr, pwd);
		if (utente != null) return true;
		return false;
	}
}
