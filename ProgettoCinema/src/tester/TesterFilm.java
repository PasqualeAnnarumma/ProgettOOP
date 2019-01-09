package tester;

import gestoreProgrammazione.Film;

public class TesterFilm {

	public static void main(String[] args) 
	{
		System.out.println("Creo il film");
		Film film = new Film("Inception", "2:30", "Mario", "Inception");
		System.out.println("Nome film : " + film.getNome());
		System.out.println("Regista : " + film.getRegista());
		System.out.println("Durata : " + film.getDurata());
		System.out.println("Copertina : " + film.getCopertina());
		System.out.println("toString : " + film);
	}
}
