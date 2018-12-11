package frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import multisala.Film;
import multisala.Multisala;

public class FrameNuovoFilm extends JDialog {
	private static final long serialVersionUID = 1L;

	private Multisala multisala;
	private JTextField titolo;
	private JTextField desc;
	private JTextField durata;
	private JTextField prezzo;
	
	public FrameNuovoFilm(JFrame gc, Multisala multisala) {
		super(gc, true);
		this.multisala = multisala;
		
		setTitle("Nuovo Film");
		setSize(250, 250);
		setResizable(false);
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel grid = new JPanel(new GridLayout(4, 1));
		
		JPanel titoloPane = new JPanel();
		titoloPane.add(new JLabel("Titolo:"));
		titolo = new JTextField(10);
		titoloPane.add(titolo);
		
		JPanel descPane = new JPanel();
		descPane.add(new JLabel("Descrizione:"));
		desc = new JTextField(10);
		descPane.add(desc);
		
		JPanel durataPane = new JPanel();
		durataPane.add(new JLabel("Durata:"));
		durata = new JTextField(10);
		durataPane.add(durata);
		
		JPanel prezzoPane = new JPanel();
		prezzoPane.add(new JLabel("Prezzo:"));
		prezzo = new JTextField(10);
		prezzoPane.add(prezzo);
		
		grid.add(titoloPane);
		grid.add(descPane);
		grid.add(prezzoPane);
		grid.add(prezzoPane);
		
		panel.add(grid, BorderLayout.CENTER);
		JPanel panelButton = new JPanel();
		panelButton.add(createButton());
		panel.add(panelButton, BorderLayout.SOUTH);
		add(panel);
	}
	
	private JButton createButton() {
		JButton confirmButton = new JButton("Conferma");
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// +++ da cambiare +++ multisala.aggiungiFilm(new Film(titolo.getText(), desc.getText(), Integer.parseInt(durata.getText()), Double.parseDouble(prezzo.getText())));
			}
		});
		
		return confirmButton;
	}
}
