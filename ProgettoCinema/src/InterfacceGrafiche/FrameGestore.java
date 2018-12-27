package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import Eccezioni.PostoNonDisponibileException;
import GestoreLogin.Amministratore;
import GestoreLogin.Cinema;
import GestoreLogin.Cliente;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;
import GestoreSale.Sala;
import GestoreSconti.Sconto;

/**
 * Il FrameGestore rappresenta l'interfaccia grafica per la visualizzazione del frame per il gestore
 * @author MarioELT
 *
 */
public class FrameGestore extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Cinema cinema;
	private JTable tabella;
	private JTable incasso;
	private JPanel body;
	private JComboBox<Sala> comboSale;
	private JRadioButton disponibile;
	private JRadioButton nonDisponibile;
	private JTabbedPane tab;
	private ArrayList<Spettacolo> listaSpettacoli;
	private ArrayList<Film> listaFilm;
	
	/**
	 * Listener è un action listener che esegue il refresh del form
	 * @author MarioELT
	 *
	 */
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			refresh();
		}	
	}
	
	/**
	 * Costruisce il frame
	 * @param admin amministratore che ha eseguito l'accesso
	 * @param cin oggetto cinema del sistema
	 */
	public FrameGestore(Amministratore admin, Cinema cin) {
		super("Gestore");
		setLocation(500, 100);
		setSize(400, 400);
		setMaximumSize(new Dimension(100, 100));
		setResizable(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cinema = cin;
		listaSpettacoli = cinema.getListaSpettacoli(cinema.postiDisponibili, "Tutte");
		listaFilm = cinema.getListaFilm();
		createCombo();
		body = createBody();
		add(body);
		pack();
	}
	
	/**
	 * Esegue un refresh del form
	 */
	public void refresh() {
		int index = tab.getSelectedIndex();
		boolean state = false;
		if (nonDisponibile.isSelected()) state = true;
		body.removeAll();
		body.repaint();
		body.add(createBody());
		body.revalidate();
		body.repaint();
		tab.setSelectedIndex(index);
		if (state) nonDisponibile.setSelected(true);
	}
	
	/**
	 * Riempie la comboBox per le sale
	 */
	public void createCombo() {
		comboSale = new JComboBox<Sala>();
		ArrayList<Sala> listaSale = cinema.getListaSale();
		for (Sala sala : listaSale)
			comboSale.addItem(sala);
		comboSale.addActionListener(new Listener());
	}
	
	/**
	 * Crea la tabella per la visualizzazione della programmazione
	 * @return tabella visualizzazione programmazione
	 */
	public JTable createTable() {
		String[] intestazione = {"Titolo", "Sala", "Durata", "Posti", "Data", "Prezzo"};
		String[][] data =  new String[listaSpettacoli.size()][6];
		for (int i = 0; i < listaSpettacoli.size(); i++)
		{
			Spettacolo spettacolo = listaSpettacoli.get(i);
			for (int j = 0; j < 6; j++)
			{
				String s = new String();
				switch (j)
				{
					case 0: s = new String(spettacolo.getFilm().getNome());
						break;
					case 1: s = new String(spettacolo.getSala().getNumeroSala() + "");
						break;
					case 2: s = new String(spettacolo.getFilm().getDurata());
						break;
					case 3: s = new String(spettacolo.getPostiLiberi()+ "/" + spettacolo.getSala().getPostiDisponibili() );
						break;
					case 4 : s = new String(spettacolo.stringDate());
						break;
					case 5: s = new String(spettacolo.getPrezzo() + "€");
						break;
					default: s = new String("NULL");
				}
				data[i][j] = s;
			}
		}
		JTable table = new JTable(data, intestazione);
		return table;
	}
	
	/**
	 * Crea il pannello per i controlli "Disponibile" e "Indisponibile"
	 * @return pannello controlli
	 */
	public JPanel createControl() {
		JPanel control = new JPanel();
		disponibile = new JRadioButton("Disponibile");
		nonDisponibile = new JRadioButton("Indisponibile");
		disponibile.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(disponibile);
		group.add(nonDisponibile);
		control.add(disponibile);
		control.add(nonDisponibile);
		return control;
	}
	
	/**
	 * Crea il pannello per la visualizzazione della programmazione
	 * @return pannello visualizzazione programmazione
	 */
	public JPanel createProgrammazioneView() {
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		tabella = createTable();
		JScrollPane scroll = new JScrollPane(tabella);
		JPanel buttonPanel = createButtonPanel();
		body.add(scroll, BorderLayout.NORTH);
		body.add(buttonPanel, BorderLayout.CENTER);
		return body;
	}
	
	/**
	 * Crea il pannello per il corpo centrale
	 * @return pannello corpo centrale
	 */
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		tab = createTabbedPane();
		body.add(tab, BorderLayout.NORTH);	
		return body;
	}
	
	/**
	 * Crea il pannello con il JTabbedPane
	 * @return pannello JTabedPane
	 */
	public JTabbedPane createTabbedPane() {
		JTabbedPane tab = new JTabbedPane();
		tab.add("Programmazione", createProgrammazioneView());
		tab.add("Sale", createSalaTab());
		tab.add("Incasso", createIncassoView());
		tab.add("Sconti", createScontiView());
		return tab;
	}
	
	/**
	 * Crea il pannello per la visualizzazione degli sconti
	 * @return pannello visualizzazione sconti
	 */
	public JPanel createScontiView() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(10, 1));
		ArrayList<Sconto<Cliente>> listaScontiCliente = cinema.getGestoreSconti().getScontiCliente();
		ArrayList<Sconto<Spettacolo>> listaScontiSpettacolo = cinema.getGestoreSconti().getScontiSpettacolo();
		ArrayList<Sconto<Film>> listaScontiFilm = cinema.getGestoreSconti().getScontiFilm();
		
		for (int i = 0; i < listaScontiCliente.size(); i++)
		{
			JPanel line = lineSconti(listaScontiCliente.get(i));
			panel.add(line);
		}
		
		for (int i = 0; i < listaScontiSpettacolo.size(); i++)
		{
			JPanel line = lineSconti(listaScontiSpettacolo.get(i));
			panel.add(line);
		}
		
		for (int i = 0; i < listaScontiFilm.size(); i++)
		{
			JPanel line = lineSconti(listaScontiFilm.get(i));
			panel.add(line);
		}
		
		return panel;
	}
	
	/**
	 * Crea il pannello per la visualizzazione di un singolo sconto
	 * @param sconto sconto da visualizzare
	 * @return pannello per lo sconto
	 */
	public JPanel lineSconti(Sconto<?> sconto) {
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder());
		panel.setLayout(new GridLayout(1, 2));
		JLabel label = new JLabel(sconto.getDescrizione());
		label.setFont(new Font("Font", Font.BOLD, 15));
		JPanel bottoni = creaBottoni(sconto);
		panel.add(label);
		panel.add(bottoni);
		return panel;
	}
	
	/**
	 * Crea il pannello per la visualizzazione dei bottoni
	 * @param sconto sconto da attivare o disattivare
	 * @return pannello per la visualizzazione dei bottoni
	 */
	public JPanel creaBottoni(Sconto<?> sconto) {
		JPanel panel = new JPanel();
		String dstring = "Disattiva";
		String astring = "Attiva";
		JButton disattiva = new JButton(dstring);
		JButton attiva = new JButton(astring);
		if (sconto.isAttivo())
		{
			disattiva.setEnabled(true);
			attiva.setEnabled(false);
		}
		else if (!sconto.isAttivo())
		{
			disattiva.setEnabled(false);
			attiva.setEnabled(true);
		}
		
		disattiva.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sconto.disattiva();
				disattiva.setEnabled(false);
				attiva.setEnabled(true);
			}
		});
		
		attiva.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sconto.attiva();
				disattiva.setEnabled(true);
				attiva.setEnabled(false);
			}
		});
		
		panel.add(disattiva);
		panel.add(attiva);
		return panel;
	}
	
	/**
	 * Crea il pannello per la visualizzazione dell'incasso
	 * @return pannello visualizzazione incasso
	 */
	public JPanel createIncassoView() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		incasso = createIncassoPanel();
		JScrollPane scroll = new JScrollPane(incasso);
		JLabel label = new JLabel("Incasso totale : " + cinema.getIncasso(cinema.getListaSpettacoli(cinema.settimana)) + " €");
		panel.add(scroll, BorderLayout.NORTH);
		JPanel sud = new JPanel(new BorderLayout());
		JPanel bottoni = createPanelBottoni();
		sud.add(label, BorderLayout.WEST);
		sud.add(bottoni, BorderLayout.CENTER);
		panel.add(sud, BorderLayout.SOUTH);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'aggiunta di un film
	 * @return pannello aggiunta film
	 */
	public JPanel createPanelBottoni() {
		JPanel panel = new JPanel();
		JButton aggiungi = new JButton("Aggiungi");
		JButton rimuovi = new JButton("Rimuovi");
		
		//BOTTONE AGGIUNGI
		aggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameFilm frame = new FrameFilm(cinema);
				frame.addWindowListener(new WindowListener() {
					public void windowOpened(WindowEvent e) {}
					
					public void windowIconified(WindowEvent e) {}
					
					public void windowDeiconified(WindowEvent e) {}

					public void windowDeactivated(WindowEvent e) {}
					
					public void windowClosing(WindowEvent e) {}
					
					public void windowClosed(WindowEvent e) {
						refresh();
					}
					
					public void windowActivated(WindowEvent e) {}
				});
				
				frame.setVisible(true);
			}
		});
		
		//BOTTONE RIMUOVI
		rimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] film = incasso.getSelectedRows();
				if (film.length == 0) JOptionPane.showMessageDialog(null, "Seleziona un elemento", "Attenzione!", JOptionPane.WARNING_MESSAGE);
				else
				{
					for (int i = 0; i < film.length; i++)
						try {
							cinema.rimuoviFilm(listaFilm.get(film[i]-i));
						} catch (PostoNonDisponibileException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Attenzione!", JOptionPane.WARNING_MESSAGE);
						}
					
					listaSpettacoli = cinema.getListaSpettacoli(cinema.postiDisponibili, "Tutte");
					refresh();
				}
			}
		});
		
		panel.add(aggiungi);
		panel.add(rimuovi);
		return panel;
	}
	
	/**
	 * Crea il pannello per la visualizzazione dell'incasso
	 * @return pannello visualizzazione incasso
	 */
	public JTable createIncassoPanel() {	
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		String[] intestazione = {"Film", "Regista", "Durata", "Incasso"};
		String[][] corpo = new String[listaFilm.size()][6];
		for (int i = 0; i < listaFilm.size(); i++)
		{
			Film film = listaFilm.get(i);
			for (int j = 0; j < 6; j++)
			{
				String s = "";
				switch (j)
				{
					case 0 : s = film.getNome();
					break;
					case 1 : s = film.getRegista();
					break;
					case 2 : s = film.getDurata();
					break;
					case 3 : s = cinema.getIncasso(film) + "€";
					break;
				}
				corpo[i][j] = s;
			}
		}
		
		JTable incasso = new JTable(corpo, intestazione);
		return incasso;
	}
	
	/**
	 * Crea il pannello per la gestione delle sale
	 * @return pannello gestione sale
	 */
	public JPanel createSalaTab() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel box = createSalaBox();
		JPanel view = createSala((Sala) comboSale.getSelectedItem());
		panel.add(box, BorderLayout.NORTH);
		panel.add(view, BorderLayout.CENTER);
		panel.add(createControl(), BorderLayout.SOUTH);
		return panel;
	}
	
	/**
	 * Crea il pannello per la visualizzazione della comboBox delle sale
	 * @return pannello comboBox sale
	 */
	public JPanel createSalaBox() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel salaLabel = new JLabel("Sala :");
		JPanel pane = new JPanel();
		pane.add(salaLabel);
		pane.add(comboSale);
		panel.add(pane, BorderLayout.WEST);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'aggiunta di uno spettacolo
	 * @return pannello aggiunta spettacolo
	 */
	public JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		JButton aggiungi = new JButton("Aggiungi");
		JButton rimuovi = new JButton("Rimuovi");
		
		//BOTTONE AGGIUNGI
		aggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFrame frame = new AddFrame(cinema, listaSpettacoli);
				frame.setVisible(true);
				frame.addWindowListener(new RefreshListener());
			}
		});
		
		//BOTTONE RIMUOVI
		rimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] righe = tabella.getSelectedRows();
				if (righe.length == 0) JOptionPane.showMessageDialog(null, "Seleziona un elemento", "Attenzione!", JOptionPane.WARNING_MESSAGE);
				else
				{
					for (int i = 0; i < righe.length; i++)
					{
						try {
							cinema.rimuoviSpettacolo(listaSpettacoli.get(righe[i]-i));
							listaSpettacoli.remove(righe[i]-i);
						} catch (PostoNonDisponibileException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Attenzione", JOptionPane.WARNING_MESSAGE);
						}
					}
					refresh();
				}
			}
		});
		
		panel.add(aggiungi);
		panel.add(rimuovi);
		return panel;
	}
	
	/**
	 * Crea il frame per la visualizzazione della sala
	 * @param sala da visualizzare
	 * @return pannello visualizzazione sala
	 */
	public JPanel createSala(Sala sala) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel header = createHeader(sala);
		JPanel box = createBox(sala);
		panel.add(header, BorderLayout.NORTH);
		panel.add(box, BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * Crea l'header per la visualizzazione della sala. Cioè la sequenza di numeri che identifica le colonne
	 * @param sala sala sala per la visualizzazione delle colonne
	 * @return pannello header
	 */
	public JPanel createHeader(Sala sala) {
		JPanel header = new JPanel();
		header.setLayout(new GridLayout(1, sala.getColonne()+2));
		header.add(new JPanel().add(new JLabel(" ")));
		for (int i = 0; i < sala.getColonne(); i++)
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel(i + "");
			panel.add(label);
			header.add(panel);
		}
		return header;
	}
	
	/**
	 * Crea il pannello contenente i posti
	 * @param sala sala per la visualizzazione dei posti
	 * @return pannello visualizzazione posti
	 */
	public JPanel createBox(Sala sala) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(sala.getRighe(), sala.getColonne()+1));
		Posto[][] posti = sala.getPosti();
		for (int i = 0; i < sala.getRighe(); i++)
		{
			char ch = (char) ('A' + i);
			JPanel pa = new JPanel();
			pa.add(new JLabel(ch + ""));
			panel.add(pa);
			for (int j = 0; j < sala.getColonne(); j++)
			{
				JPanel p = createPosto(posti[i][j]);
				JLabel label = new JLabel(" ");
				p.add(label);
				panel.add(p);
			}
		}
		return panel;
	}
	
	/**
	 * Crea il pannello per il posto
	 * @param posto posto da visualizzare
	 * @return pannello per la visualizzazione del posto
	 */
	public JPanel createPosto(Posto posto) {
		JPanel panel = new JPanel();
		String sedile = "";
		if (!posto.isDisponibile())
			sedile = ("posto_indisponibile");
		else if (!posto.isOccupato())
			sedile = ("posto_libero");
		else if (posto.isAcquistato())
			sedile = ("posto_occupato");
		else if (posto.isOccupato())
			sedile = ("posto_prenotato");
		
		JLabel iconaPosto = new JLabel();
		iconaPosto.setIcon(creaPosto(sedile));
		
		panel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) {}
			
			public void mouseExited(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseClicked(MouseEvent e) {
				try {
					if (disponibile.isSelected())
					{
						cinema.setDisponibile(posto, (Sala) comboSale.getSelectedItem());
						String sedile = ("posto_libero");
						iconaPosto.setIcon(creaPosto(sedile));
					}
					else if (nonDisponibile.isSelected())
					{
						cinema.setIndisponibile(posto,(Sala) comboSale.getSelectedItem());
						String sedile = ("posto_indisponibile");
						iconaPosto.setIcon(creaPosto(sedile));
					}
					refresh();
				} catch (PostoNonDisponibileException ex) {
					System.out.println(ex);
				}
			}
		});
		panel.add(iconaPosto);
		return panel;
	}
	
	/**
	 * Crea l'icona per il posto
	 * @param sedile nome immagine
	 * @return icona del posto
	 */
	public ImageIcon creaPosto(String sedile) {
		ImageIcon myPicture = new ImageIcon("src\\icone\\" + sedile + ".png");
		return myPicture;
	}
	
	/**
	 * RefreshListener è un WindowListener che aggiorna il frame quando la finestra viene chiusa
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
