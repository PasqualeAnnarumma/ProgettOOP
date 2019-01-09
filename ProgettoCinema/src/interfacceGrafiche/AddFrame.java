package interfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import cinema.Cinema;
import gestoreProgrammazione.Film;
import gestoreProgrammazione.Spettacolo;
import gestoreSale.Sala;

/**
 * AddFrame rappresenta il frame per l'aggiunta degli spettacoli nella programmazione
 * @author MarioELT
 *
 */
public class AddFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Cinema cinema;
	private JComboBox<Sala> comboSala;
	private JComboBox<Film> comboFilm;
	private JComboBox<String> comboGiorno;
	private JComboBox<String> comboMese;
	private JComboBox<String> comboAnno;
	private JComboBox<String> comboOre;
	private JComboBox<String> comboMinuti;
	private JTextField prezzoField;
	private ArrayList<Spettacolo> listaSpettacoli;
	
	/**
	 * Costruisce il frame
	 * @param cin cinema
	 * @param spettacoli lista degli spettacoli
	 */
	public AddFrame(Cinema cin, ArrayList<Spettacolo> spettacoli) {
		super("Aggiungi spettacolo");
		setLocation(500, 100);
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		Image img = Toolkit.getDefaultToolkit().getImage("src//iconeFinestra//addFilm.png");
		setIconImage(img);
		cinema = cin;
		listaSpettacoli = spettacoli;
		comboSala = new JComboBox<Sala>();
		comboFilm = new JComboBox<Film>();
		comboGiorno = new JComboBox<String>();
		comboMese = new JComboBox<String>();
		comboAnno = new JComboBox<String>();
		comboOre = new JComboBox<String>();
		comboMinuti = new JComboBox<String>();
		prezzoField = new JTextField(10);
		JPanel body = creaProgrammazione();
		add(body);
	}
	
	/**
	 * Crea il pannello per la programmazione
	 * @return pannello programmazione
	 */
	public JPanel creaProgrammazione() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 1));
		JPanel comboPanel = createComboPanel();
		JPanel comboSpettacolo = createComboFilm();
		JPanel panelData = creaPanelData();
		JPanel panelOra = createPanelOra();
		JPanel prezzoPanel = creaPrezzoPanel();
		JPanel buttonPanel = createButtonPanel();
		
		panel.add(comboPanel);
		panel.add(comboSpettacolo);
		panel.add(panelData);
		panel.add(panelOra);
		panel.add(prezzoPanel);
		panel.add(buttonPanel);
		return panel;
	}
	
	/**
	 * Crea il pannello con i pulsanti
	 * @return pannello con i pulsanti
	 */
	public JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JButton button = new JButton("Inserisci");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (prezzoField.getText().length() > 0)
				{
					Sala sala = (Sala) comboSala.getSelectedItem();
					Film film = (Film) comboFilm.getSelectedItem();
					int giorno = Integer.parseInt(comboGiorno.getSelectedItem().toString());
					int mese = Integer.parseInt(comboMese.getSelectedItem().toString());
					int anno = Integer.parseInt(comboAnno.getSelectedItem().toString());
					int ore = Integer.parseInt(comboOre.getSelectedItem().toString());
					int minuti = Integer.parseInt(comboMinuti.getSelectedItem().toString());
					String hh = "";
					String mm = "";
					if (ore < 10) hh = 0 + "" + ore + ":";
					else hh = ore + ":";
					if (minuti < 10) mm = 0 + "" + minuti + "";
					else mm = minuti + "";
					String ora = hh + mm;
					double prezzo = Double.parseDouble(prezzoField.getText());
					Spettacolo spettacolo = new Spettacolo(sala, film, giorno, mese, anno, ora, prezzo);
					cinema.aggiungiSpettacolo(spettacolo);
					listaSpettacoli.add(spettacolo);
					JOptionPane.showMessageDialog(null, "Spettacolo aggiunto con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src//iconeFinestra//success.png"));
					prezzoField.setText("");
				}
				else
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi!", "ATTENZIONE!", JOptionPane.ERROR_MESSAGE, new ImageIcon("src//iconeFinestra//errore.png"));
			}
		});
		
		JPanel pane = new JPanel();
		pane.add(button);
		panel.add(pane, BorderLayout.WEST);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'inserimento del prezzo
	 * @return pannello per il prezzo
	 */
	public JPanel creaPrezzoPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel prezzo = new JLabel("Prezzo : ");
		
		JPanel prezzop = new JPanel();
		prezzop.add(prezzo);
		prezzop.add(prezzoField);
		
		panel.add(prezzop, BorderLayout.WEST);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'inserimento dell'ora di inizio
	 * @return pannello inserimento ora
	 */
	public JPanel createPanelOra() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		popolaOra();
		JLabel ore = new JLabel("Ore : ");
		JLabel minuti = new JLabel("Minuti : ");
		
		JPanel pane = new JPanel();		
		pane.add(ore);
		pane.add(comboOre);
		pane.add(minuti);
		pane.add(comboMinuti);
		
		panel.add(pane, BorderLayout.WEST);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'inserimento della data
	 * @return pannello per la data
	 */
	public JPanel creaPanelData() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		popolaData();
		JLabel giorno = new JLabel("Giorno : ");
		JLabel mese = new JLabel("Mese : ");
		JLabel anno = new JLabel("Anno : ");
		
		JPanel pane = new JPanel();
		pane.add(giorno);
		pane.add(comboGiorno);
		pane.add(mese);
		pane.add(comboMese);
		pane.add(anno);
		pane.add(comboAnno);
		
		panel.add(pane, BorderLayout.WEST);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'inserimento della sala
	 * @return pannello per la sala
	 */
	public JPanel createComboPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel salaLabel = new JLabel("Sala : ");
		popolaCombo();
		
		JPanel pane = new JPanel();
		pane.add(salaLabel);
		pane.add(comboSala);
		
		panel.add(pane, BorderLayout.WEST);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'inserimento del film
	 * @return pannello per il film
	 */
	public JPanel createComboFilm() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel spettacoloLabel = new JLabel("Spettacolo : ");
		popolaFilm();
		
		JPanel pane = new JPanel();		
		pane.add(spettacoloLabel);
		pane.add(comboFilm);
		
		panel.add(pane, BorderLayout.WEST);
		return panel;
	}
	
	/**
	 * Riempie la comboBox per la sala
	 */
	public void popolaCombo() {
		ArrayList<Sala> listaSpettacoli = cinema.getListaSale();
		for (Sala sala : listaSpettacoli)
			comboSala.addItem(sala);
	}
	
	/**
	 * Riempie la comboBox per i film
	 */
	public void popolaFilm() {
		ArrayList<Film> listaFilm = cinema.getListaFilm();
		for (Film film : listaFilm)
			comboFilm.addItem(film);
	}
	
	/**
	 * Riempie le comboBox per la data
	 */
	public void popolaData() {
		for (int i = 1; i <= 31; i++)
		{
			String s = i + "";
			if (i < 10)
				s = "0" + i;
			comboGiorno.addItem(s);
		}
		
		for (int i = 1; i <= 12; i++)
		{
			String s = i + "";
			if (i < 10)
				s = "0" + i;
			comboMese.addItem(s);
		}
		
		for (int i = 0; i < 100; i++)
			comboAnno.addItem((2018 + i) + "");
	}
	
	/**
	 * Riempie le comboBox per l'ora
	 */
	public void popolaOra() {
		for (int i = 1; i <= 24; i++)
		{
			String s = i + "";
			if (i < 10)
				s = "0" + i;
			comboOre.addItem(s);
		}
		
		for (int i = 0; i <= 59; i++)
		{
			String s = i + "";
			if (i < 10)
				s = "0" + i;
			comboMinuti.addItem(s);
		}
	}
	
}
