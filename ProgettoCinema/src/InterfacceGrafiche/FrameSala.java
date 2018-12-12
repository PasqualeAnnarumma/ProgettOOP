package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Eccezioni.PostoNonDisponibileException;
import GestoreLogin.Cliente;
import GestorePrenotazioni.GestorePrenotazioni;
import GestorePrenotazioni.Prenotazione;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;
import GestoreSale.Sala;

public class FrameSala extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private GestorePrenotazioni gestorePrenotazioni;
	private Spettacolo spettacolo;
	private Cliente cliente;
	
	public FrameSala(GestorePrenotazioni gestorePr, Spettacolo show, Cliente cl) {
		super();
		gestorePrenotazioni = gestorePr;
		spettacolo = show;
		cliente = cl;
		setLocation(500, 100);
		setSize(350, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JMenuBar toolBar = createToolBar();
		JPanel sala = createSala();
		panel.add(toolBar, BorderLayout.NORTH);
		panel.add(sala, BorderLayout.CENTER);
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
			System.out.println();
		}
		return panel;
	}
	
	public JPanel createPosto(Posto p) {
		JPanel panel = new JPanel();
		if (!p.isOccupato()) panel.setBackground(Color.green);
		else panel.setBackground(Color.RED);
		
		panel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) {}
			
			public void mouseExited(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseClicked(MouseEvent e) {
				if (p.isDisponibile())
				{
					try {
						p.occupaPosto();
						Prenotazione prenotazione = new Prenotazione(spettacolo);
						gestorePrenotazioni.aggiungiPrenotazione(prenotazione, cliente);
						panel.setBackground(Color.RED);
					} catch (PostoNonDisponibileException e1) {
						System.out.println(e1);
					}
				}
			}
		});
		
		return panel;
	}
}
