package GestoreLogin;

import java.util.ArrayList;

public class GestoreLogin {
	
	ArrayList<Utente> listaUtenti;
	
	public GestoreLogin() {
		listaUtenti = new ArrayList<Utente>();
	}
	
	public void add(Utente user) {
		listaUtenti.add(user);
	}
	
	public void remove(Utente user) {
		listaUtenti.remove(user);
	}
	
	public Utente login(String usr, String pwd) {
		for(Utente user : listaUtenti)
			if (user.getUsername().equals(usr) && user.getPassword().equals(pwd)) return user;
		
		return null;
	}
	
}
