package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import GestoreLogin.Utente;
import GestorePrenotazioni.GestorePrenotazioni;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.GestoreProgrammazione;
import GestoreProgrammazione.ProgrammaSettimanale;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.GestoreSale;
import GestoreSale.Sala;

public class FrameUtente extends JFrame{
	
	private static final long serialVersionUID = 1L;
	Utente utente;
	GestoreProgrammazione gestoreProgrammazione;
	GestorePrenotazioni gestorePrenotazioni;
	GestoreSale gestoreSale;
	Spettacolo spettacoloSelezionato;
	JScrollPane center;
	JRadioButton progTot;
	JRadioButton progSett;
	JComboBox<String> combo;
	JPanel body;
	
	public FrameUtente(Utente user, GestoreProgrammazione gestoreProg, GestorePrenotazioni gestorePren, GestoreSale gestoreSa) {
		super("Prenotazione posto");
		utente = user;
		gestoreProgrammazione = gestoreProg;
		gestorePrenotazioni = gestorePren;
		gestoreSale = gestoreSa;
		progTot = new JRadioButton("totale");
		progSett = new JRadioButton("settimanale");
		progTot.setSelected(true);
		setLocation(500, 100);
		setSize(450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		JPanel toolPanel = createToolPanel();
		center = createCenterPanel();
		body.add(toolPanel, BorderLayout.NORTH);
		body.add(center, BorderLayout.CENTER);
		return body;
	}
	
	public JPanel createToolPanel() {
		JPanel toolPanel = new JPanel();
		toolPanel.setLayout(new BorderLayout());
		JPanel optionPanel = createOptionPanel();
		JButton cerca = new JButton("Cerca");
		
		cerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				body.removeAll();
				body.repaint();
				body.add(createBody());
				body.revalidate();
				body.repaint();
			}
		});
		
		JPanel panel = new JPanel();
		panel.add(cerca);
		toolPanel.add(optionPanel, BorderLayout.NORTH);
		toolPanel.add(panel, BorderLayout.SOUTH);
		return toolPanel;
	}
	
	public JScrollPane createCenterPanel() {
		if (progTot.isSelected())
		{
			JScrollPane filmPanel = createTotFilmPanel();
			System.out.println("Tot");
			return filmPanel;
		}
		else
		{
			JScrollPane filmPanel = createSetFilmPanel();
			System.out.println("Set");
			return filmPanel;
		}
	}
	
	public JScrollPane createTotFilmPanel() {
		JPanel panel = new JPanel();
		JScrollPane scroll = new JScrollPane(panel);
		panel.setLayout(new GridLayout(gestoreProgrammazione.conteggioTotale(), 1));
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
		return scroll;
	}
	
	public JScrollPane createSetFilmPanel() {
		JPanel panel = new JPanel();
		JScrollPane scroll = new JScrollPane(panel);
		return scroll;
	}
	
	public JPanel createOptionPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel sx = createProgPanel();
		JPanel dx = createSalaPanel();
		panel.add(sx, BorderLayout.WEST);
		panel.add(dx, BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel createProgPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Programmazione"));
		panel.setLayout(new BorderLayout());
		ButtonGroup group = new ButtonGroup();
		group.add(progTot);
		group.add(progSett);
		panel.add(progTot, BorderLayout.WEST);
		panel.add(progSett, BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel createSalaPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Sala"));
		panel.setLayout(new BorderLayout());;
		combo = new JComboBox<String>();
		combo.addItem("Tutte");
		for (int i = 0; i < gestoreSale.size(); i++)
		{
			Sala sala = gestoreSale.getSala(i);
			combo.addItem("sala " + sala.getNumeroSala());
		}
		panel.add(combo);
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
