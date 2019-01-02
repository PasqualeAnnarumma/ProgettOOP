package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import Eccezioni.PostoNonDisponibileException;
import cinema.Cinema;
import GestoreLogin.Cliente;
import GestorePrenotazioni.Prenotazione;
import GestoreProgrammazione.Spettacolo;
import GestoreSale.Posto;
import GestoreSale.Sala;

/**
 * FrameSala è il frame che si occupa della visualizzazione della sala
 * @author MarioELT
 *
 */
public class FrameSala extends JFrame {
	
	private static final long serialVersionUID = 1L;
	final Color prenotato = Color.ORANGE;
	final Color acquistato = Color.RED;
	final Color indisponibile = Color.GRAY;
	final Color disponibile = Color.GREEN;
	private Cinema cinema;
	private Cliente cliente;
	private Spettacolo spettacolo;
	private Prenotazione prenotazione;
	private JRadioButton prenota;
	private JRadioButton acquisto;
	private JRadioButton cancellazione;
	
	/**
	 * Costruisce il frame
	 * @param cinema oggetto cinema del sistema
	 * @param show spettacolo da visualizzare
	 */
	public FrameSala(Cinema cinema, Spettacolo show) {
		super(show.getFilm().getNome() + " - (" + show.getOra() + ")");
		this.cinema = cinema;
		spettacolo = show;
		cliente = (Cliente) cinema.getUtente();
		setResizable(false);
		Image img = Toolkit.getDefaultToolkit().getImage("src//iconeFinestra//ticket.png");
		setIconImage(img);
		prenotazione = new Prenotazione(show, new Posto('A', 0), (Cliente) cinema.getUtente());
		setLocation(500, 100);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel body = createBody();
		add(body);
		pack();
	}
	
	/**
	 * Crea il corpo centrale per la visualizzazione della sala
	 * @return pannello centrale
	 */
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
	
	/**
	 * Crea il menu a tendina
	 * @return menu a tendina
	 */
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
	
	/**
	 * Crea il pannello per la visualizzazione della sala
	 * @return pannello visualizzazione sala
	 */
	public JPanel createSala() {
		JPanel panel = new JPanel();
		Sala sala = spettacolo.getSala();
		//panel.setLayout(new GridLayout(sala.getRighe()+1, sala.getColonne()));
		panel.setLayout(new BorderLayout());
		JPanel header = createHeader(sala);
		JPanel box = createBox(sala);
		panel.add(header, BorderLayout.NORTH);
		panel.add(box, BorderLayout.CENTER);
		/*for (int i = 0; i < sala.getRighe(); i++)
		{
			pa = new JPanel();
			pa.add(new JLabel(i + ""));
			panel.add(pa);
			for (int j = 0; j < sala.getColonne(); j++)
			{
				JPanel p = createPosto(posti[i][j]);
				p.add(label);
				panel.add(p);
			}
		}*/
		return panel;
	}
	
	/**
	 * Crea il panello header. Cioè contenente la sequenza di numeri che identifica le colonne
	 * @param sala sala per le colonne
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
	 * Crea il box per la visualizzazione della sala
	 * @param sala sala per la visualizzazione
	 * @return pannello box
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
				JPanel p = createPosto(posti[i][j], prenotazione);
				JLabel label = new JLabel(" ");
				p.add(label);
				panel.add(p);
			}
		}
		return panel;
	}
	
	/**
	 * Crea il pannello per la visualizzazione dei controlli: prenota, acquista, cacella
	 * @return pannello visualizzazione controlli
	 */
	public JPanel createControl() {
		JPanel control = new JPanel();
		prenota = new JRadioButton("Prenota");
		acquisto = new JRadioButton("Acquisto");
		cancellazione = new JRadioButton("Cancellazione");
		if (!spettacolo.isPrenotable())
		{
			prenota.setEnabled(false);
			acquisto.setSelected(true);
		}
		else
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
	
	/**
	 * Crea il pannello per la visualizzazione del posto
	 * @param posto posto da visualizzare
	 * @param prenotazione prenotazione per controllare lo stato del posto
	 * @return pannello per la visualizzazione del posto
	 */
	public JPanel createPosto(Posto posto, Prenotazione prenotazione) {
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
		//Prenotazione prenotazione = new Prenotazione(spettacolo, p, cliente);
		
		panel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) {}
			
			public void mouseExited(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseClicked(MouseEvent e) {
				Prenotazione prenotazione = new Prenotazione(spettacolo, posto, cliente);
				try {
					if (!posto.isDisponibile()) JOptionPane.showMessageDialog(null, "Posto non disponibile!", "ATTENZIONE!", JOptionPane.ERROR_MESSAGE, new ImageIcon("src//iconeFinestra//errore.png"));
					else if (prenota.isSelected())
					{
						cinema.aggiungiPrenotazione(cliente, prenotazione);
						String sedile = ("posto_prenotato");
						iconaPosto.setIcon(creaPosto(sedile));
					}
					else if (cancellazione.isSelected())
					{
						Prenotazione pren = cinema.cercaPrenotazione(posto, spettacolo);
						if (pren != null) pren.setPosto(posto);
						cinema.rimuoviPrenotazione(cliente, pren);
						String sedile = ("posto_libero");
						iconaPosto.setIcon(creaPosto(sedile));
					}
					else if (acquisto.isSelected())
					{
						cinema.acquistaPosto(cliente, prenotazione, posto);
						String sedile = ("posto_occupato");
						iconaPosto.setIcon(creaPosto(sedile));
					}
				} catch (PostoNonDisponibileException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ATTENZIONE!", JOptionPane.ERROR_MESSAGE, new ImageIcon("src//iconeFinestra//errore.png"));
				}
			}
		});
		panel.add(iconaPosto);
		return panel;
	}
	
	/**
	 * Crea l'icona per il posto
	 * @param sedile nome dell'immagine
	 * @return icona posto
	 */
	public ImageIcon creaPosto(String sedile) {
		ImageIcon myPicture = new ImageIcon("src\\icone\\" + sedile + ".png");
		return myPicture;
	}
}
