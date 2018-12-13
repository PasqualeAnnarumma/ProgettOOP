package GestoreProgrammazione;

public class Film {
	
	String nome;
	String durata;
	String regista;
	
	public Film (String name, String duration, String producer) {
		nome = name;
		durata = duration;
		regista = producer;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDurata() {
		return durata;
	}
	
	public String getRegista() {
		return regista;
	}
	
	public String toString() {
		return getClass().getSimpleName() + "[nome=" + nome + ",durata=" + durata + ",regista=" + regista + "]";
	}
}
