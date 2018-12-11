package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import multisala.Spettacolo;

public class PanelSpettacolo extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Crea un pannello specializzato nel visualizzare i dettagli di uno spettacolo
	 * relativo ad un film in un determinato giorno
	 * 
	 * @param uno spettacolo di cui si vogliono visualizzare i dettagli
	 */
	public PanelSpettacolo(Spettacolo spettacolo, FrameCliente frameCliente) {
		// da migliorare l'estetica

		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.BLACK));
		JPanel dettagliPane = new JPanel(new GridLayout(2, 1));
		dettagliPane.add(new JLabel("Sala: " + spettacolo.getSala().getCodice()));
		dettagliPane.add(new JLabel("Inizio: " + spettacolo.getData().getHour() + ":"
				+ String.format("%02d", spettacolo.getData().getMinute())));

		add(new JLabel(spettacolo.getFilm().getTitolo()), BorderLayout.NORTH);
		add(dettagliPane, BorderLayout.CENTER);
		
		JButton bottoneDettagli = new JButton("Dettagli");
		bottoneDettagli.addActionListener(e -> {
			JFrame frameSala = new JFrame();
			JPanel pane = new JPanel(new GridLayout(5, 1));
			frameSala.add(pane);
			frameSala.setSize(300, 200);
			frameSala.setResizable(false);
			frameSala.setTitle("Descrizione");
			pane.add(new JLabel("Titolo: " + spettacolo.getFilm().getTitolo()));
			pane.add(new JLabel("Durata: " + spettacolo.getFilm().getDurata()));
			pane.add(new JLabel("Prezzo: " + spettacolo.getFilm().getPrezzo()));
			pane.add(new JLabel("Descrizione: " + spettacolo.getFilm().getDescrizione()));
			pane.add(new JLabel("Incasso Settimanale: " + spettacolo.getFilm().getIncassoFilm()));
			frameSala.setVisible(true);
		});
		
		JButton bottoneAquista = new JButton("Aquista");
		bottoneAquista.addActionListener(e -> {
			FrameAcquista frame = new FrameAcquista(frameCliente,spettacolo.getSala());
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		});
		
		JPanel pane = new JPanel(new GridLayout(2, 1));
		add(pane, BorderLayout.SOUTH);
		pane.add(bottoneDettagli);
		pane.add(bottoneAquista);
	}
	
}
