package GestoreLogin;

import java.util.ArrayList;

import Eccezioni.AccountGiaEsistenteException;
import GestoreLogin.Cliente.Categoria;

public class GestoreLogin {
	
	ArrayList<Utente> listaUtenti;
	
	public GestoreLogin() {
		listaUtenti = new ArrayList<Utente>();
	}
	
	public void aggiungiCliente(String usr, String pwd, String compleanno, Categoria category) throws AccountGiaEsistenteException{
		Cliente nuovo = new Cliente(usr, pwd, compleanno, category);
		for (Utente ut : listaUtenti)
			if (ut.getUsername().equals(usr)) throw new AccountGiaEsistenteException();
		
		listaUtenti.add(nuovo);
	}
	
	public void aggiungiAmministratore(String usr, String pwd) throws AccountGiaEsistenteException{
		for (Utente ut : listaUtenti)
			if (ut.getUsername().equals(usr)) throw new AccountGiaEsistenteException("Amministratore già esistente");
		
		listaUtenti.add(new Amministratore(usr, pwd));
	}
	
	public void remove(Utente user) {
		listaUtenti.remove(user);
	}
	
	public Utente login(String usr, String pwd) {
		for(Utente user : listaUtenti)
			if (user.getUsername().equals(usr) && user.getPassword().equals(pwd)) return user;
		
		return null;
	}
	
	public ArrayList<Cliente> getListaClienti() {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		for (int i = 0; i < listaUtenti.size(); i++)
			if (listaUtenti.get(i) instanceof Cliente) lista.add((Cliente) listaUtenti.get(i));
		
		return lista;
	}
	
}
