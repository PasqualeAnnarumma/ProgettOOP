package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import GestoreLogin.Cinema;
import GestoreLogin.Cliente;
import GestoreProgrammazione.Criterio;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Sala;

public class FrameUtente extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private final Color colore = Color.LIGHT_GRAY;
	private final Color coloreSelezionato = Color.CYAN;
	private Cliente utente;
	private Spettacolo spettacoloSelezionato;
	Cinema cinema;
	private JScrollPane center;
	private JRadioButton progTot;
	private JRadioButton progSett;
	private JComboBox<String> combo;
	private JPanel body;
	private JPanel currSlot;
	
	public FrameUtente(Cliente user, Cinema cinema) {
		super("Prenotazione posto");
		utente = user;
		this.cinema = cinema;
		setResizable(false);
		progTot = new JRadioButton("totale");
		progSett = new JRadioButton("settimanale");
		progTot.setSelected(true);
		combo = new JComboBox<String>();
		combo.addItem("Tutte");
		for (int i = 0; i < cinema.getGestoreSale().size(); i++)
		{
			Sala sala = cinema.getGestoreSale().getSala(i);
			combo.addItem(""+sala.getNumeroSala());
		}
		setLocation(500, 100);
		setSize(450, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	
	public JMenuBar createToolBar() {
		JMenuBar toolBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem logout = new JMenuItem("Logout");
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		file.add(logout);
		toolBar.add(file);
		return toolBar;
	}
	
	public JPanel createToolPanel() {
		JPanel toolPanel = new JPanel();
		toolPanel.setLayout(new BorderLayout());
		JPanel optionPanel = createOptionPanel();
		JMenuBar toolBar = createToolBar();
		JButton cerca = new JButton("Cerca");
		
		//BOTTONE CERCA 
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
		toolPanel.add(toolBar, BorderLayout.NORTH);
		toolPanel.add(optionPanel, BorderLayout.CENTER);
		toolPanel.add(panel, BorderLayout.SOUTH);
		return toolPanel;
	}
	
	Criterio settimana = (Spettacolo s1) -> {
		Calendar dataSpettacolo = s1.getData();
		Calendar nowDate = Calendar.getInstance();
		int r;
		if ((r = s1.compareCalendar(dataSpettacolo, nowDate)) <= 7 && r >= 0)
			return true;
		return false;
	};
	
	Criterio sala = (Spettacolo s1) -> {
		String combos = combo.getSelectedItem().toString();
		if (combos.equals("Tutte"))
			return true;
		else if (s1.getSala().getNumeroSala() == Integer.parseInt(combos))
			return true;
		return false;
	};
	
	public JScrollPane createCenterPanel() {
		ArrayList<Spettacolo> listaSpettacoli = new ArrayList<Spettacolo>();
		if (progTot.isSelected())
			listaSpettacoli = cinema.getListaSpettacoli(cinema.sempre, sala);
		else
			listaSpettacoli = cinema.getListaSpettacoli(settimana, sala);
		
		JScrollPane filmPanel = createFilmPanel(listaSpettacoli);
		return filmPanel;
	}
	
	public JScrollPane createFilmPanel(ArrayList<Spettacolo> listaSpettacoli) {
		JPanel panel = new JPanel();
		JScrollPane scroll = new JScrollPane(panel);
		panel.setLayout(new GridLayout(listaSpettacoli.size(), 1));
		for (int i = 0; i < listaSpettacoli.size(); i++)
		{
			Spettacolo show = listaSpettacoli.get(i);
			JPanel slot = createSlotFilm(show);
			panel.add(slot);
		}
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
		panel.add(combo);
		return panel;
	}
	
	public JPanel createSlotFilm(Spettacolo show) {
		JPanel slot = new JPanel();
		slot.setBorder(new EtchedBorder());
		slot.setLayout(new GridLayout(4, 2));
		Film film = show.getFilm();
		Sala sala = show.getSala();
		JLabel nomeFilm = new JLabel(film.getNome());
		nomeFilm.setFont(new Font("font1", Font.BOLD, 15));
		JLabel nomeRegista = new JLabel(film.getRegista());
		JLabel durata = new JLabel(film.getDurata());
		JLabel data = new JLabel(show.stringDate());
		JLabel ora = new JLabel(show.getOra());
		float sconto = cinema.cercaSconto(utente, show);
		float prezz = (float) (show.getPrezzo() - (show.getPrezzo() * sconto));
		JLabel prezzo = new JLabel(prezz + "€");
		if (sconto != 0)
		{
			prezzo.setFont(new Font("Barrato", Font.BOLD, 15));
			Color verde = new Color(35, 181, 5);
			prezzo.setForeground(verde);
		}
		JLabel numeroSala = new JLabel("Sala " + sala.getNumeroSala());
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
				if (slot.getBackground() != coloreSelezionato)
					slot.setBackground(null);
			}
			
			public void mouseEntered(MouseEvent e) {
				if (slot.getBackground() != coloreSelezionato)
					slot.setBackground(colore);
			}
			
			public void mouseClicked(MouseEvent e) {
				/*if (slot.getBackground() != coloreSelezionato)
				{
					if (currSlot != null)
						currSlot.setBackground(null);
					slot.setBackground(coloreSelezionato);
					currSlot = slot;
				}
				else
					slot.setBackground(null);*/
				FrameSala frame = new FrameSala(cinema, show);
				frame.setVisible(true);
			}
			
		});
		
		return slot;
	}
}
