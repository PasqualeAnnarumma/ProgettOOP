package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import GestoreLogin.Cinema;
import GestoreProgrammazione.Film;

/**
 * FrameFilm rappresenta l'interfaccia grafica per l'aggiunta dei film
 * @author MarioELT
 *
 */
public class FrameFilm extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Cinema cinema;
	private JTextField nomeFilm;
	private JTextField nomeProduttore;
	private JComboBox<Integer> comboOre;
	private JComboBox<Integer> comboMinuti;
	
	/**
	 * Costruisce il frame
	 * @param cin oggetto cinema del sistema
	 */
	public FrameFilm(Cinema cin) {
		super("Aggiungi film");
		cinema = cin;
		setLocation(500, 100);
		setSize(300, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel body = createBody();
		add(body, BorderLayout.WEST);
		pack();
	}
	
	/**
	 * Crea il pannello per il corpo centrale
	 * @return pannello corpo centrale
	 */
	public JPanel createBody() {
		JPanel body = new JPanel(new GridLayout(4, 1));
		JPanel panelName = createPanelName();
		JPanel panelDurata = createPanelDurata();
		JPanel panelProduttore = createPanelProduttore();
		
		JButton aggiungi = new JButton("Aggiungi");
		
		aggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String durata = comboOre.getSelectedItem() + ":" + comboMinuti.getSelectedItem();
				Film film = new Film(nomeFilm.getText(), durata, nomeProduttore.getText());
				cinema.aggiungiFilm(film);
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(aggiungi);
		
		body.add(panelName);
		body.add(panelDurata);
		body.add(panelProduttore);
		body.add(buttonPanel);
		return body;
	}
	
	/**
	 * Crea il pannello per l'inserimento del nome del film
	 * @return pannello inserimento film
	 */
	public JPanel createPanelName() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Nome film : ");
		nomeFilm = new JTextField(20);
		panel.add(label);
		panel.add(nomeFilm);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'inserimento della durata
	 * @return panello inserimento durata
	 */
	public JPanel createPanelDurata() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Durata : ");
		comboOre = new JComboBox<Integer>();
		comboMinuti = new JComboBox<Integer>();
		for (int i = 1; i <= 5; i++)
			comboOre.addItem(i);
		
		for (int i = 0; i < 60; i++)
			comboMinuti.addItem(i);
		
		panel.add(label);
		panel.add(comboOre);
		panel.add(comboMinuti);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'inserimento del produttore
	 * @return pannello inserimento produttore
	 */
	public JPanel createPanelProduttore() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Nome Produttore : ");
		nomeProduttore = new JTextField(20);
		panel.add(label);
		panel.add(nomeProduttore);
		return panel;
	}
	
}
