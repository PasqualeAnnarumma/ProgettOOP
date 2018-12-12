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
	
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		Utente ut = (Utente) obj;
		return ut.getUsername().equals(username) && ut.getPassword().equals(password);
	}

}
