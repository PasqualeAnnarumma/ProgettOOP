package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import Eccezioni.PostoNonDisponibileException;
import GestoreLogin.Amministratore;
import GestoreLogin.Cinema;
import GestoreProgrammazione.Criterio;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;
import GestoreSale.Sala;

public class FrameGestore extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final Color colore = Color.CYAN;
	private final Color coloreSelezionato = Color.LIGHT_GRAY;
	private Amministratore amministratore;
	private Cinema cinema;
	private JTable tabella;
	private JTable incasso;
	private JPanel body;
	private JComboBox<Sala> comboSale;
	private JRadioButton disponibile;
	private JRadioButton nonDisponibile;
	private JTabbedPane tab;
	
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			refresh();
		}	
	}
	
	public FrameGestore(Amministratore admin, Cinema cin) {
		super("Gestore");
		setLocation(500, 100);
		setSize(400, 400);
		setMaximumSize(new Dimension(100, 100));
		setResizable(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cinema = cin;
		createCombo();
		body = createBody();
		add(body);
		pack();
	}
	
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
	
	public void createCombo() {
		comboSale = new JComboBox<Sala>();
		ArrayList<Sala> listaSale = cinema.getListaSale();
		for (Sala sala : listaSale)
			comboSale.addItem(sala);
		comboSale.addActionListener(new Listener());
	}
	
	public JTable createTable() {
		String[] intestazione = {"Titolo", "Sala", "Durata", "Posti", "Data", "Prezzo"};
		ArrayList<Spettacolo> listaSpettacoli = cinema.getListaSpettacoli();
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
	
	public JPanel createProgrammazioneView() {
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		tabella = createTable();
		JScrollPane scroll = new JScrollPane(tabella);
		JPanel buttonPanel = createButtonPAnel();
		body.add(scroll, BorderLayout.NORTH);
		body.add(buttonPanel, BorderLayout.CENTER);
		return body;
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		tab = createTabbedPane();
		body.add(tab, BorderLayout.NORTH);	
		return body;
	}
	
	public JTabbedPane createTabbedPane() {
		JTabbedPane tab = new JTabbedPane();
		tab.add("Programmazione", createProgrammazioneView());
		tab.add("Sale", createSalaTab());
		tab.add("Incasso", createIncassoView());
		tab.add("Sconti", createProgrammazioneView());
		return tab;
	}
	
	public JPanel createIncassoView() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		incasso = createIncassoPanel();
		JScrollPane scroll = new JScrollPane(incasso);
		JLabel label = new JLabel("Incasso totale : " + cinema.getIncasso(cinema.getListaSpettacoli(settimana, cinema.sempre)) + " €");
		panel.add(scroll, BorderLayout.NORTH);
		panel.add(label, BorderLayout.SOUTH);
		return panel;
	}
	
	Criterio settimana = (Spettacolo s1) -> {
		Calendar dataSpettacolo = s1.getData();
		Calendar nowDate = Calendar.getInstance();
		int r;
		if ((r = s1.compareCalendar(dataSpettacolo, nowDate)) <= 7 && r >= 0)
			return true;
		return false;
	};
	
	public JTable createIncassoPanel() {	
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		ArrayList<Spettacolo> listaSpettacoli = cinema.getListaSpettacoli(settimana, cinema.sempre);
		String[] intestazione = {"Film", "Regista", "Durata", "Incasso"};
		String[][] corpo = new String[listaSpettacoli.size()][6];
		for (int i = 0; i < listaSpettacoli.size(); i++)
		{
			Spettacolo spettacolo = listaSpettacoli.get(i);
			for (int j = 0; j < 6; j++)
			{
				String s = "";
				switch (j)
				{
					case 0 : s = spettacolo.getFilm().getNome();
					break;
					case 1 : s = spettacolo.getFilm().getRegista();
					break;
					case 2 : s = spettacolo.getFilm().getDurata();
					break;
					case 3 : s = cinema.getIncasso(spettacolo.getFilm()) + "€";
					break;
				}
				corpo[i][j] = s;
			}
		}
		
		JTable incasso = new JTable(corpo, intestazione);
		return incasso;
	}
	
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
	
	public JPanel createSalaView() {
		JPanel panel = new JPanel();
		return panel;
	}
	
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
	
	public JPanel createButtonPAnel() {
		JPanel panel = new JPanel();
		JButton aggiungi = new JButton("Aggiungi");
		JButton rimuovi = new JButton("Rimuovi");
		
		aggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFrame frame = new AddFrame(cinema);
				frame.setVisible(true);
				
				frame.addWindowListener(new WindowListener() {
					
					public void windowOpened(WindowEvent e) {}
					
					public void windowIconified(WindowEvent e) {}
					
					public void windowDeiconified(WindowEvent e) {}
					
					public void windowDeactivated(WindowEvent e) {}
					
					public void windowClosing(WindowEvent e) {}
					
					public void windowClosed(WindowEvent e) {
						body.removeAll();
						body.repaint();
						body.add(createBody());
						body.revalidate();
						body.repaint();
					}
					
					public void windowActivated(WindowEvent e) {}
				});
			}
		});
		
		rimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] riga = tabella.getSelectedRows();
				for (int i = 0; i< riga.length; i++)
					System.out.println(riga[i]);
				//int colonna = tabella.getSelectedColumn();
			}
		});
		
		panel.add(aggiungi);
		panel.add(rimuovi);
		return panel;
	}
	
	public JPanel createSala(Sala sala) {
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(sala.getRighe()+1, sala.getColonne()));
		panel.setLayout(new BorderLayout());
		JPanel header = createHeader(sala);
		JPanel box = createBox(sala);
		panel.add(header, BorderLayout.NORTH);
		panel.add(box, BorderLayout.CENTER);
		return panel;
	}
	
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
	
	public JPanel createPosto(Posto p) {
		JPanel panel = new JPanel();
		String sedile = "";
		if (!p.isDisponibile())
			sedile = ("posto_indisponibile");
		else if (!p.isOccupato())
			sedile = ("posto_libero");
		else if (p.isAcquistato())
			sedile = ("posto_occupato");
		else if (p.isOccupato())
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
						cinema.setDisponibile(p, (Sala) comboSale.getSelectedItem());
						String sedile = ("posto_libero");
						iconaPosto.setIcon(creaPosto(sedile));
					}
					else if (nonDisponibile.isSelected())
					{
						cinema.setIndisponibile(p,(Sala) comboSale.getSelectedItem());
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
	
	public ImageIcon creaPosto(String sedile) {
		ImageIcon myPicture = new ImageIcon("src\\icone\\" + sedile + ".png");
		return myPicture;
	}
	
}
