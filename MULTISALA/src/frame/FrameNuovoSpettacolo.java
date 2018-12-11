package frame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import gestione.GestoreMultisala;
import multisala.Film;
import multisala.Sala;

public class FrameNuovoSpettacolo extends JDialog{
	private static final long serialVersionUID = 1L;
	private JComboBox<String> film;
	private JLabel durata;
	private JComboBox<Integer> sale;
	private JComboBox<String> ora, minuti;
	private JComboBox<String> giorni;
	private ArrayList<Sala> listaSale;
	private ArrayList<Film> listaFilm;
	/**
	 * Crea un Dialog in cui è possibile creare un nuovo spettacolo
	 * @param parentFrame Frame chiamante
	 * @param gestore GestoreMultisala
	 */
	public FrameNuovoSpettacolo(JFrame parentFrame,GestoreMultisala gestore) {
		super(parentFrame,true);
		setSize(200, 250);
		setResizable(false);
		setTitle("Nuovo Spettacolo");
		//Prendo le liste di Film e Sale
		listaFilm=gestore.getMultisala().getListaFilm();
		listaSale=gestore.getMultisala().getListaSala();
		
		JPanel main=new JPanel(new BorderLayout());
		JPanel panel=new JPanel(new GridLayout(5,1));
		JPanel durataPanel=new JPanel();
		durata=new JLabel("Durata: ");
		durataPanel.add(durata);
		//Aggiunta delle componenti ai pannelli
		panel.add(createFilmBox());
		panel.add(durataPanel);
		panel.add(createSalaBox());
		panel.add(createDataPanel());
		panel.add(createOrarioBox());
		main.add(panel,BorderLayout.CENTER);
		main.add(createButtonPanel(),BorderLayout.SOUTH);
		add(main);
	}
	/**
	 * Crea il Pannello avente la data
	 * @return JPanel
	 */
	private JPanel createDataPanel() {
		String[] listaGiorni= {"Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato","Domenica"};
		JPanel panel=new JPanel();
		JLabel label=new JLabel("Giorno: ");
		label.setFont(new Font("Serif", Font.BOLD, 12));
		panel.add(label);
		giorni=new JComboBox<String>(listaGiorni);			//ComboBox della lista dei giorni
		giorni.setFont(new Font("Serif", Font.BOLD, 12));
		panel.add(giorni);
		return panel;
	}
	/**
	 * Crea il pannello avente la Sala
	 * @return JPanel
	 */
	private JPanel createSalaBox() {
		JPanel panel=new JPanel();
		JLabel label=new JLabel("Sala: ");
		label.setFont(new Font("Serif", Font.BOLD, 12));
		panel.add(label);
		sale=new JComboBox<Integer>();						//ComboBox della lista delle Sale
		for(Sala s: listaSale)
			sale.addItem(s.getCodice());
		sale.setFont(new Font("Serif", Font.BOLD, 12));
		panel.add(sale);
		return panel;
	}
	/**
	 * Crea il pannello avente il pulsante
	 * @return JPanel
	 */
	private JPanel createButtonPanel() {
		JPanel panel=new JPanel();
		JButton createButton=new JButton("Conferma");
		createButton.setFont(new Font("Serif", Font.BOLD, 12));
		panel.add(createButton);
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int indexFilm=film.getSelectedIndex();
				int indexSala=sale.getSelectedIndex();
				int hh=Integer.parseInt((String)ora.getSelectedItem());
				int mm=Integer.parseInt((String)minuti.getSelectedItem());
				String giorno=(String)giorni.getSelectedItem();
			}
		} );
		return panel;
	}
	/**
	 * Crea il pannello avente l'orario
	 * @return JPanel
	 */
	private JPanel createOrarioBox() {
		JPanel panel=new JPanel();
		JLabel label=new JLabel("Orario:");
		label.setFont(new Font("Serif", Font.BOLD, 12));
		panel.add(label);
		ora=new JComboBox<>();							//ComboBox delle ore e dei minuti
		minuti=new JComboBox<>();
		for(int i=0;i<24;i++)
			ora.addItem(String.format("%02d", i));
		for(int i=0;i<60;i++)
			minuti.addItem(String.format("%02d", i));
		ora.setFont(new Font("Serif", Font.BOLD, 12));
		minuti.setFont(new Font("Serif", Font.BOLD, 12));
		panel.add(ora);
		panel.add(new JLabel(":"));
		panel.add(minuti);
		return panel;
	}
	/**
	 * Crea il pannello avente i film
	 * @return JPanel
	 */
	private JPanel createFilmBox() {
		JPanel panel=new JPanel();
		JLabel label=new JLabel("Film:");
		label.setFont(new Font("Serif", Font.BOLD, 12));
		panel.add(label);
		film=new JComboBox<String>();
		for(Film f: listaFilm)
			film.addItem(f.getTitolo());
		panel.add(film);
		film.setFont(new Font("Serif", Font.BOLD, 12));
		//Listener per aggiornare la durata in base al Film
		film.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				durata.setText("Durata: "+listaFilm.get(film.getSelectedIndex()).getDurata()+" min");
			}
		});
		durata.setText("Durata : "+listaFilm.get(0).getDurata()+" min");
		durata.setFont(new Font("Serif", Font.BOLD, 12));
		return panel;
	}
}
