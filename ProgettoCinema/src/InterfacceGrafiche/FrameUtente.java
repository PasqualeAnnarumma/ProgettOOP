package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import GestoreLogin.Cinema;
import GestoreLogin.Cliente;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Sala;

/**
 * Il FrameUtente è l'interfaccia grafica che si occupa della visualizzazione del frame per l'utente
 * @author MarioELT
 *
 */
public class FrameUtente extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private final Color colore = Color.LIGHT_GRAY;
	private final Color coloreSelezionato = Color.CYAN;
	private Cliente utente;
	//private Spettacolo spettacoloSelezionato;
	private Cinema cinema;
	private JScrollPane center;
	private JComboBox<String> combo;
	private JPanel body;
	//private JPanel currSlot;
	private JComboBox<String> comboOrdina;
	
	/**
	 * Costruisce il frame dell'utente
	 * @param user utente che ha effettuato il login
	 * @param cinema oggetto cinema del sistema
	 */
	public FrameUtente(Cliente user, Cinema cinema) {
		super("Prenotazione posto (" + user.getUsername() + ": " + user.getEta() + ")");
		utente = user;
		this.cinema = cinema;
		setResizable(false);
		comboOrdina = new JComboBox<String>();
		comboOrdina.addItem("Cronologicamente");
		comboOrdina.addItem("Sala crescente");
		comboOrdina.addItem("Alfabeticamente");
		combo = new JComboBox<String>();
		combo.addItem("Tutte");
		for (int i = 0; i < cinema.getGestoreSale().size(); i++)
		{
			Sala sala = cinema.getGestoreSale().getSala(i);
			combo.addItem(""+sala.getNumeroSala());
		}
		setLocation(500, 100);
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		body = createBody();
		add(body);
	}
	
	/**
	 * Crea il pannello centrale
	 * @return panello centrale
	 */
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		JPanel toolPanel = createToolPanel();
		center = createCenterPanel();
		body.add(toolPanel, BorderLayout.NORTH);
		body.add(center, BorderLayout.CENTER);
		return body;
	}
	
	/**
	 * Crea il pannello per il menù a tendina
	 * @return pannello menù a tendina
	 */
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
	
	/**
	 * Crea il pannello per la ricerca degli spettacoli
	 * @return pannello ricerca spettacoli
	 */
	public JPanel createToolPanel() {
		JPanel toolPanel = new JPanel();
		toolPanel.setLayout(new BorderLayout());
		JPanel optionPanel = createOptionPanel();
		JMenuBar toolBar = createToolBar();
		JButton cerca = new JButton("Cerca");
		
		//BOTTONE CERCA 
		cerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		
		JPanel panel = new JPanel();
		panel.add(cerca);
		toolPanel.add(toolBar, BorderLayout.NORTH);
		toolPanel.add(optionPanel, BorderLayout.CENTER);
		toolPanel.add(panel, BorderLayout.SOUTH);
		return toolPanel;
	}
	
	/**
	 * Crea il pannello con tutti i film
	 * @return pannello con i film
	 */
	public JScrollPane createCenterPanel() {
		ArrayList<Spettacolo> listaSpettacoli = new ArrayList<Spettacolo>();
		if (comboOrdina.getSelectedItem().equals("Cronologicamente"))
			listaSpettacoli = cinema.getSpettacoliFruibili(cinema.ordineCronologico, combo.getSelectedItem().toString());
		else if (comboOrdina.getSelectedItem().equals("Sala crescente"))
			listaSpettacoli = cinema.getSpettacoliFruibili(cinema.salaCrescente, combo.getSelectedItem().toString());
		else
			listaSpettacoli = cinema.getSpettacoliFruibili(cinema.titoloAlfabetico, combo.getSelectedItem().toString());
		
		JScrollPane filmPanel = createFilmPanel(listaSpettacoli);
		return filmPanel;
	}
	
	/**
	 * Crea lo scrollPane con tutti i film
	 * @param listaSpettacoli lista degli spettacoli da inserire
	 * @return JScrollPane con gli spettacoli
	 */
	public JScrollPane createFilmPanel(ArrayList<Spettacolo> listaSpettacoli) {
		JPanel panel = new JPanel();
		JScrollPane scroll = new JScrollPane(panel);
		int righe = 3;
		if (listaSpettacoli.size() > righe) righe = listaSpettacoli.size();
		panel.setLayout(new GridLayout(righe, 1));
		for (int i = 0; i < listaSpettacoli.size(); i++)
		{
			Spettacolo show = listaSpettacoli.get(i);
			JPanel slot = createSlotSpettacolo(show);
			panel.add(slot);
		}
		return scroll;
	}
	
	/**
	 * Crea il pannello per le opzioni di ricerca
	 * @return pannello opzioni di ricerca
	 */
	public JPanel createOptionPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel sx = createProgPanel();
		JPanel dx = createSalaPanel();
		panel.add(sx, BorderLayout.WEST);
		panel.add(dx, BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * Crea il pannello per la selezione del tipo di ordinamento
	 * @return pannello ordinamento
	 */
	public JPanel createProgPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Ordinamento"));
		panel.setLayout(new BorderLayout());
		panel.add(comboOrdina, BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * Crea il pannello per la selezione della sala
	 * @return pannello selezione sala
	 */
	public JPanel createSalaPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Sala"));
		panel.setLayout(new BorderLayout());;
		panel.add(combo);
		return panel;
	}
	
	/**
	 * Crea il pannello per lo slot dello spettacolo
	 * @param show spettacolo da visualizzare
	 * @return pannello visualizzazione spettacolo
	 */
	public JPanel createSlotSpettacolo(Spettacolo show) {
		JPanel slot = new JPanel();
		//slot.setBorder(new EtchedBorder());
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
			prezz *= 1000;
			prezz = (float) Math.floor(prezz);
			prezz /= 1000;
			prezzo = new JLabel("<html>"
								+ "<span style=\"text-decoration: line-through;\" color = \"red\";>"
									+ show.getPrezzo() + "€"
								+ "</span> "
					
								+ "<span  style=\"font-size:11px\"; color=\"#23B505\";>"
									+ "<b>" + prezz + "€</b>"
								+ "</span>"
							+ "</html>");
		}
		JLabel numeroSala = new JLabel("Sala " + sala.getNumeroSala());
		JLabel miniatura = new JLabel();
		String copertina = show.getFilm().getCopertina();
		miniatura.setIcon(new ImageIcon("src\\copertine\\" + copertina + ".jpg"));
		slot.add(nomeFilm);
		slot.add(nomeRegista);
		slot.add(durata);
		slot.add(data);
		slot.add(ora);
		slot.add(prezzo);
		slot.add(numeroSala);
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel imgPanel = new JPanel();
		imgPanel.add(miniatura);
		panel.add(imgPanel, BorderLayout.WEST);
		panel.add(slot, BorderLayout.CENTER);
		
		panel.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) {}
			
			public void mouseExited(MouseEvent e) {
				if (slot.getBackground() != coloreSelezionato)
				{
					imgPanel.setBackground(null);
					slot.setBackground(null);
				}
			}
			
			public void mouseEntered(MouseEvent e) {
				if (slot.getBackground() != coloreSelezionato)
				{
					imgPanel.setBackground(colore);
					slot.setBackground(colore);
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				FrameSala frame = new FrameSala(cinema, show);
				frame.addWindowListener(new RefreshListener());
				frame.setVisible(true);
			}
			
		});
		return panel;
	}
	
	/**
	 * Aggiorna il frame
	 */
	public void refresh() {
		body.removeAll();
		body.repaint();
		body.add(createBody());
		body.revalidate();
		body.repaint();
	}
	
	/**
	 * RefreshListener è un WindowListener che aggiorna il frame alla chiusura di un altro
	 * @author MarioELT
	 *
	 */
	class RefreshListener implements WindowListener{

		public void windowOpened(WindowEvent e) {}
			
		public void windowIconified(WindowEvent e) {}
			
		public void windowDeiconified(WindowEvent e) {}
			
		public void windowDeactivated(WindowEvent e) {}
			
		public void windowClosing(WindowEvent e) {}
			
		public void windowClosed(WindowEvent e) {
			refresh();
		}
			
		public void windowActivated(WindowEvent e) {}
	}
}
