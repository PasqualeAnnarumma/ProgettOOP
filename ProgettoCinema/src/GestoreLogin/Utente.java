package GestoreLogin;

public class Utente implements Cloneable {
	
	private String username;
	private String password;
	
	public Utente(String usr, String pwd) {
		username = usr;
		password = pwd;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String usr) {
		username = usr;
	}
	
	public void setPassword(String pwd) {
		password = pwd;
	}
	
	public Utente clone() {
		try {
			return (Utente) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
