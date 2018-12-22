package GestoreSconti;

import java.util.ArrayList;

import GestoreLogin.Cliente;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;

public class GestoreSconti {
	
	private ArrayList<Sconto<Cliente>> listaScontiCliente;
	private ArrayList<Sconto<Spettacolo>> listaScontiSpettacolo;
	private ArrayList<Sconto<Film>> listaScontiFilm;
	
	public GestoreSconti() {
		listaScontiCliente = new ArrayList<Sconto<Cliente>>();
		listaScontiSpettacolo = new ArrayList<Sconto<Spettacolo>>();
		listaScontiFilm = new ArrayList<Sconto<Film>>();
	}
	
	public void aggiungiScontoCliente(Sconto<Cliente> sconto) {
		listaScontiCliente.add(sconto);
	}
	
	public void rimuoviScontoCliente(int i) {
		listaScontiCliente.remove(i);
	}
	
	public Sconto<Cliente> getScontoCliente(int i) {
		return listaScontiCliente.get(i);
	}
	
	public ArrayList<Sconto<Cliente>> getScontiCliente() {
		return listaScontiCliente;
	}
	
	public void aggiungiScontoSpettacolo(Sconto<Spettacolo> sconto) {
		listaScontiSpettacolo.add(sconto);
	}
	
	public void rimuoviScontoSpettacolo(int i) {
		listaScontiSpettacolo.remove(i);
	}
	
	public Sconto<Spettacolo> getScontoSpettacolo(int i) {
		return listaScontiSpettacolo.get(i);
	}
	
	public ArrayList<Sconto<Spettacolo>> getScontiSpettacolo() {
		return listaScontiSpettacolo;
	}
	
	public void aggiungiScontoFilm(Sconto<Film> sconto) {
		listaScontiFilm.add(sconto);
	}
	
	public void rimuoviScontoFilm(int i) {
		listaScontiFilm.remove(i);
	}
	
	public Sconto<Film> getScontoFilm(int i) {
		return listaScontiFilm.get(i);
	}
	
	public ArrayList<Sconto<Film>> getScontiFilm() {
		return listaScontiFilm;
	}
	
	public float cercaSconto(Cliente cliente, Spettacolo show) {
		float sconto = 0;
		
		for (int i = 0; i < listaScontiCliente.size(); i++)
		{
			Sconto<Cliente> s = listaScontiCliente.get(i);
			float temp = s.calcolaSconto(cliente);
			if (sconto < temp && s.isAttivo()) sconto = temp;
		}
		
		for (int i = 0; i < listaScontiSpettacolo.size(); i++)
		{
			Sconto<Spettacolo> s = listaScontiSpettacolo.get(i);
			float temp = s.calcolaSconto(show);
			if (sconto < temp && s.isAttivo()) sconto = temp;
		}
		
		for (int i = 0; i < listaScontiFilm.size(); i++)
		{
			Sconto<Film> s = listaScontiFilm.get(i);
			float temp = s.calcolaSconto(show.getFilm());
			if (sconto < temp && s.isAttivo()) sconto = temp;
		}
		
		return sconto;
	}
	
}
