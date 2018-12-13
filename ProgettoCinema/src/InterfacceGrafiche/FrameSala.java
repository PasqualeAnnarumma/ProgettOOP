package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import Eccezioni.PostoNonDisponibileException;
import GestoreLogin.Cinema;
import GestoreLogin.Cliente;
import GestorePrenotazioni.Prenotazione;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;
import GestoreSale.Sala;

public class FrameSala extends JFrame {
	
	private static final long serialVersionUID = 1L;
	final Color prenotato = Color.ORANGE;
	final Color acquistato = Color.RED;
	final Color indisponibile = Color.GRAY;
	final Color disponibile = Color.GREEN;
	private Cinema cinema;
	private Cliente cliente;
	private Spettacolo spettacolo;
	JRadioButton prenota;
	JRadioButton acquisto;
	JRadioButton cancellazione;
	
	public FrameSala(Cinema cinema, Spettacolo show) {
		super();
		this.cinema = cinema;
		spettacolo = show;
		cliente = (Cliente) cinema.getUtente();
		setLocation(500, 100);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JMenuBar toolBar = createToolBar();
		JPanel sala = createSala();
		JPanel control = createControl();
		panel.add(toolBar, BorderLayout.NORTH);
		panel.add(sala, BorderLayout.CENTER);
		panel.add(control, BorderLayout.SOUTH);
		return panel;
	}
	
	public JMenuBar createToolBar() {
		JMenuBar toolBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem logout = new JMenuItem("indietro");
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		file.add(logout);
		toolBar.add(file);
		return toolBar;
	}
	
	public JPanel createSala() {
		JPanel panel = new JPanel();
		Sala sala = spettacolo.getSala();
		panel.setLayout(new GridLayout(sala.getRighe(), sala.getColonne()));
		Posto[][] posti = sala.getPosti();
		for (int i = 0; i < sala.getRighe(); i++)
		{
			for (int j = 0; j < sala.getColonne(); j++)
			{
				JPanel p = createPosto(posti[i][j]);
				
				JLabel label = new JLabel(posti[i][j].getRiga() + "" + posti[i][j].getColonna());
				p.add(label);
				panel.add(p);
			}
		}
		return panel;
	}
	
	public JPanel createControl() {
		JPanel control = new JPanel();
		prenota = new JRadioButton("Prenota");
		acquisto = new JRadioButton("Acquisto");
		cancellazione = new JRadioButton("Cancellazione");
		prenota.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(prenota);
		group.add(acquisto);
		group.add(cancellazione);
		control.add(prenota);
		control.add(acquisto);
		control.add(cancellazione);
		return control;
	}
	
	public JPanel createPosto(Posto p) {
		JPanel panel = new JPanel();
		if (!p.isDisponibile())
			panel.setBackground(indisponibile);
		else if (!p.isOccupato())
			panel.setBackground(disponibile);
		else if (p.isOccupato())
			panel.setBackground(prenotato);
		if (p.isAcquistato())
			panel.setBackground(acquistato);
		
		panel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) {}
			
			public void mouseExited(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseClicked(MouseEvent e) {
				Prenotazione prenotazione = new Prenotazione(spettacolo, p);
				try {
					if (!p.isDisponibile()) System.out.println("Posto non disponibile");
					else if (prenota.isSelected())
					{
						cinema.aggiungiPrenotazione(cliente, prenotazione);
						panel.setBackground(prenotato);
					}
					else if (cancellazione.isSelected())
					{
						if (cinema.controlloProprietà(cliente, p) != null)
							p.liberaPosto();
						
						cinema.rimuoviPrenotazione(cliente, prenotazione);
						panel.setBackground(disponibile);
					}
					else if (acquisto.isSelected())
					{
						cinema.acquistaPosto(cliente, prenotazione, p);
						panel.setBackground(acquistato);
					}
				} catch (PostoNonDisponibileException ex) {
					System.out.println(ex);
				}
			}
		});
		return panel;
	}
}
