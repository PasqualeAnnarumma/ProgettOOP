package GestorePrenotazioni;

import GestoreLogin.Cliente;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;

public class Prenotazione {
	
	private Spettacolo spettacolo;
	private Cliente cliente;
	private Posto posto;
	private boolean pagato;
	private double prezzo;
	
	public Prenotazione(Spettacolo show, Posto p, Cliente cliente) {
		spettacolo = show;
		this.cliente = cliente;
		posto = p;
		pagato = false;
		prezzo = 0;
	}
	
	public String toString() {
		return getClass().getSimpleName() + "[spettacolo=" + spettacolo + ",posto=" + posto + ",cliente=" + cliente.getUsername() + ",pagato=" + pagato + ",prezzo=" + prezzo + "]";
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Prenotazione p = (Prenotazione) obj;
		return p.spettacolo.equals(spettacolo) && posto.equals(p.posto) && cliente.equals(p.cliente) && pagato == p.pagato && prezzo == p.prezzo;
	}
	
	public Posto getPosto() {
		return posto;
	}
	
	public void setPosto(Posto p) {
		posto = p;
	}
	
	public Spettacolo getSpettacolo() {
		return spettacolo;
	}
	
	public boolean isPagato() {
		return pagato;
	}
	
	public double getPrezzoPagato() {
		return prezzo;
	}
	
	public void setPagato() {
		pagato = true;
		setPrezzo(spettacolo.getPrezzo());
	}
	
	public void setNonPagato() {
		pagato = false;
		prezzo = 0;
	}
	
	public void setPrezzo(double price) {
		prezzo = price;
	}
}
