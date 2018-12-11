package InterfacceGrafiche;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import GestoreLogin.Utente;
import GestorePrenotazioni.GestorePrenotazioni;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.GestoreProgrammazione;
import GestoreProgrammazione.ProgrammaSettimanale;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Sala;

public class FrameUtente extends JFrame{
	
	private static final long serialVersionUID = 1L;
	Utente utente;
	GestoreProgrammazione gestoreProgrammazione;
	GestorePrenotazioni gestorePrenotazioni;
	Spettacolo spettacoloSelezionato;
	
	public FrameUtente(Utente user, GestoreProgrammazione gestoreProg, GestorePrenotazioni gestorePren) {
		super("Prenotazione posto");
		utente = user;
		gestoreProgrammazione = gestoreProg;
		gestorePrenotazioni = gestorePren;
		setLocation(500, 100);
		setSize(450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		JPanel filmPanel = createFilmPanel();
		body.add(filmPanel);
		return body;
	}
	
	public JPanel createFilmPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		for (int i = 0; i < gestoreProgrammazione.size(); i++)
		{
			ProgrammaSettimanale listaProgrammiSettimanali = gestoreProgrammazione.getProgrammaSettimanale(i);
			for (int j = 0; j < listaProgrammiSettimanali.size(); j++)
			{
				Spettacolo show = listaProgrammiSettimanali.getSpettacolo(j);
				JPanel slot = createSlotFilm(show);
				panel.add(slot);
			}
		}
		return panel;
	}
	
	public JPanel createSlotFilm(Spettacolo show) {
		JPanel slot = new JPanel();
		Film film = show.getFilm();
		Sala sala = show.getSala();
		JLabel nomeFilm = new JLabel(film.getNome());
		JLabel nomeRegista = new JLabel(film.getRegista());
		JLabel durata = new JLabel(film.getDurata());
		JLabel data = new JLabel(show.getData());
		JLabel ora = new JLabel(show.getOra());
		JLabel prezzo = new JLabel(show.getPrezzo() + "€");
		JLabel numeroSala = new JLabel(sala.getNumeroSala() + "");
		slot.add(nomeFilm);
		slot.add(nomeRegista);
		slot.add(durata);
		slot.add(data);
		slot.add(ora);
		slot.add(prezzo);
		slot.add(numeroSala);
		
		slot.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) {}
			
			public void mouseExited(MouseEvent e) {
				if (slot.getBackground() != Color.DARK_GRAY)
					slot.setBackground(null);
			}
			
			public void mouseEntered(MouseEvent e) {
				if (slot.getBackground() != Color.DARK_GRAY)
					slot.setBackground(Color.CYAN);
			}
			
			public void mouseClicked(MouseEvent e) {
			}
			
		});
		
		return slot;
	}
}
