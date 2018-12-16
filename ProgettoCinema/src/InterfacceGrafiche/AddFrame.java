package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import GestoreLogin.Cinema;
import GestoreProgrammazione.Film;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Sala;

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
	
	public AddFrame(Cinema cin) {
		super("Aggiungi spettacolo");
		setLocation(500, 100);
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		cinema = cin;
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
	
	public JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JButton button = new JButton("Inserisci");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		
		JPanel pane = new JPanel();
		pane.add(button);
		panel.add(pane, BorderLayout.WEST);
		return panel;
	}
	
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
	
	public void popolaCombo() {
		ArrayList<Sala> listaSpettacoli = cinema.getListaSale();
		for (Sala sala : listaSpettacoli)
			comboSala.addItem(sala);
	}
	
	public void popolaFilm() {
		ArrayList<Film> listaFilm = cinema.getListaFilm();
		for (Film film : listaFilm)
			comboFilm.addItem(film);
	}
	
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
	
	public void popolaOra() {
		for (int i = 1; i <= 24; i++)
		{
			String s = i + "";
			if (i < 10)
				s = "0" + i;
			comboOre.addItem(s);
		}
		
		for (int i = 1; i <= 59; i++)
		{
			String s = i + "";
			if (i < 10)
				s = "0" + i;
			comboMinuti.addItem(s);
		}
	}
	
}