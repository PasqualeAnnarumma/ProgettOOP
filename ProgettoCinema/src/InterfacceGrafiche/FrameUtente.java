package InterfacceGrafiche;

import javax.swing.JFrame;
import javax.swing.JPanel;
import GestoreLogin.Utente;
import GestorePrenotazioni.GestorePrenotazioni;
import GestoreProgrammazione.GestoreProgrammazione;

public class FrameUtente extends JFrame{
	
	private static final long serialVersionUID = 1L;
	Utente utente;
	GestoreProgrammazione gestoreProgrammazione;
	GestorePrenotazioni gestorePrenotazioni;
	
	public FrameUtente(Utente user, GestoreProgrammazione gestoreProg, GestorePrenotazioni gestorePren) {
		super("Prenotazione posto");
		utente = user;
		gestoreProgrammazione = gestoreProg;
		gestorePrenotazioni = gestorePren;
		setLocation(500, 100);
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		JPanel filmPanel = createFilmPanel();
		return body;
	}
	
	public JPanel createFilmPanel() {
		JPanel panel = new JPanel();
		
		return panel;
	}
}
