package gestione;

import java.io.Serializable;
import java.util.ArrayList;

import eccezioni.AccountGiaPresenteException;
import eccezioni.AccountNonPresenteException;
import multisala.Multisala;
import utente.Account;
import utente.Account.Livello;

public class Sistema implements Serializable {
	private static final long serialVersionUID = 1L;
	private Multisala multisala;
	private ArrayList<Account> listaAccount;
	
	/**
	 * 
	 */
	public Sistema() {	//solo alla prima creazione del Sistema
		multisala = new Multisala();
		listaAccount = new ArrayList<Account>();
		listaAccount.add(new Account("admin", "password", Livello.GESTORE));
	}
	
	/**
	 * 
	 * @return
	 */
	public Multisala getMultisala() {
		return multisala;
	}
	
	/**
	 * 
	 * @param account
	 * @return
	 * @throws AccountNonPresenteException 
	 */
	public Account getAccount(Account account) throws AccountNonPresenteException {
		for(int i = 0; i < listaAccount.size(); i++)
			if(account.equals(listaAccount.get(i)))
				return listaAccount.get(i);
		throw new AccountNonPresenteException();
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 * @throws AccountNonPresenteException 
	 */
	public Account getAccount(String username) throws AccountNonPresenteException {
		for(Account a : listaAccount)
			if(a.getUsername().equals(username))
				return a;
		throw new AccountNonPresenteException();
	}
	
	/**
	 * 
	 * @param account
	 * @throws AccountGi�PresenteException
	 */
	public void aggiungiAccount(Account account) throws AccountGiaPresenteException {
		for(Account a : listaAccount)
			if(a.getUsername().equals(account.getUsername()))
				throw new AccountGiaPresenteException();
		listaAccount.add(account);
	}
}
