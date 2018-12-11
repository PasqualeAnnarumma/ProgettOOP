package utente;
import java.io.Serializable;
import java.util.ArrayList;
import eccezioni.CartaNonValidaException;
import multisala.Spettacolo;

/**
 * Un AccountCliente contiene i dati di un cliente.
 */


public class AccountCliente extends Account implements Serializable {
	private static final long serialVersionUID = 1L;
	public static enum Gruppo { UNDER14, ADULTO, OVER60, STUDENTE };
	private ArrayList<Biglietto> biglietti;
	private Gruppo gruppo;
	private String numeroCartaDiCredito;

	/**
	 * Crea una nuova istanza di cliente, dati i parametri per istanziare un Account e in più il numero della carta di credito.
	 * @param username
	 * @param password
	 * @param livelloAutorizzazione
	 * @param numeroCartaDiCredito
	 */
	public AccountCliente(String username, String password, Livello livelloAutorizzazione, String numeroCartaDiCredito) throws CartaNonValidaException {
		super(username, password, livelloAutorizzazione);
		gruppo = Gruppo.ADULTO;
		if(numeroCartaDiCredito.length() < 16)
			throw new CartaNonValidaException();
		this.numeroCartaDiCredito = numeroCartaDiCredito;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param livelloAutorizzazione
	 * @param gruppo
	 * @param numeroCartaDiCredito
	 */
	public AccountCliente(String username, String password, Livello livelloAutorizzazione, Gruppo gruppo, String numeroCartaDiCredito) throws CartaNonValidaException {
		super(username, password, livelloAutorizzazione);
		this.gruppo = gruppo;
		if(numeroCartaDiCredito.length() < 16)
			throw new CartaNonValidaException();
		this.numeroCartaDiCredito = numeroCartaDiCredito;
	}

	/**
	 * 
	 * @return
	 */
	public Gruppo getGruppo() {
		return gruppo;
	}

	/**
	 * 
	 * @return
	 */
	public String getNumeroCartaDiCredito() {
		return numeroCartaDiCredito;
	}

	/**
	 * 
	 * @param gruppo
	 */
	public void setGruppo(Gruppo gruppo) {
		this.gruppo = gruppo;
	}

	/**
	 * 
	 * @param numeroCartaDiCredito
	 */
	public void setNumeroCartaDiCredito(String numeroCartaDiCredito) throws CartaNonValidaException {
		if(numeroCartaDiCredito.length() < 16)
			throw new CartaNonValidaException();
		this.numeroCartaDiCredito = numeroCartaDiCredito;
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public Biglietto getBiglietto(int index) {
		return biglietti.get(index);
	}

	/**
	 * 
	 * @param biglietto
	 */
	public void aggiungiBiglietto(Biglietto biglietto) {
		biglietti.add(biglietto);
	}

	/**
	 * 
	 * @param biglietto
	 * @return
	 */
	public Biglietto rimuoviBiglietto(Biglietto biglietto) {
		for (int i = 0; i < biglietti.size(); i++)
			if (biglietti.get(i).equals(biglietto))
				return biglietti.remove(i);

		return null;
	}

	public String toString() {
		return super.toString() + "[gruppo=" + gruppo + "numeroCartaDiCredito=" + numeroCartaDiCredito + "biglietti=" + biglietti + "]";
	}
	//Un cliente non può prenotare piu posti per lo stesso spettacolo.
	public Biglietto getBigliettoSpettacolo(Spettacolo spettacolo) {
		for(int i = 0; i < biglietti.size(); i++)
			if(biglietti.get(i).getSpettacolo().equals(spettacolo))
				return biglietti.get(i);
		return null;
	}
}
