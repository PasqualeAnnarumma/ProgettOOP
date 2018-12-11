package gestione;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import eccezioni.AccountGiaPresenteException;
import eccezioni.AccountNonPresenteException;
import eccezioni.CampiVuotiException;
import eccezioni.CartaNonValidaException;
import eccezioni.PasswordErrataException;
import utente.Account;
import utente.Account.Livello;
import utente.AccountCliente;
import utente.AccountCliente.Gruppo;

public class GestoreCentrale {
	private Sistema sistema;
	private File db;

	public GestoreCentrale() throws FileNotFoundException, IOException, ClassNotFoundException {
		db = new File("sistema.db");
		if (!db.exists()) {
			sistema = new Sistema();
			salvaSistema();
		}
		else {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(db));
			sistema = (Sistema) in.readObject();
			in.close();
		}
	}
	
	public Sistema getSistema() {
		return sistema;
	}

	public void salvaSistema() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(db));
		out.writeObject(sistema);
		out.close();
	}

	public Account autentica(String username, String password) throws AccountNonPresenteException, PasswordErrataException, CampiVuotiException {
		if (username.length() == 0 || password.length() == 0)
			throw new CampiVuotiException();
		Account account = sistema.getAccount(username);
		if (!account.getPassword().equals(password))
			throw new PasswordErrataException();
		else
			return account;
	}

	public void registraCliente(String username, String password, Gruppo gruppo, String numeroCartaDiCredito) throws CartaNonValidaException, AccountGiaPresenteException, CampiVuotiException {
		if (username.length() == 0 || password.length() == 0)
			throw new CampiVuotiException();
		sistema.aggiungiAccount(new AccountCliente(username, password, Livello.CLIENTE, gruppo, numeroCartaDiCredito));
	}

}
