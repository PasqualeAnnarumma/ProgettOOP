package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import gestione.GestoreCentrale;
import multisala.Film;
import multisala.Sala;
import multisala.Spettacolo;
import utente.AccountCliente;

public class FrameCliente extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 500;
	private HashMap<DayOfWeek, String> giorni;

	private FrameLogin parentFrame;
	private AccountCliente loggedIn;
	
	private JComboBox<String> giornoBox;

	public FrameCliente(FrameLogin parentFrame, AccountCliente loggedIn) {
		this.parentFrame = parentFrame;
		this.loggedIn = loggedIn;

		setTitle("Cinema");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		addGiorni();
		add(createTabs(), BorderLayout.CENTER);
		createCloseOperation();
	}

	private void addGiorni() {
		giorni = new HashMap<DayOfWeek, String>();
		giorni.put(DayOfWeek.MONDAY, "Lunedì");
		giorni.put(DayOfWeek.TUESDAY, "Martedì");
		giorni.put(DayOfWeek.WEDNESDAY, "Mercoledì");
		giorni.put(DayOfWeek.THURSDAY, "Giovedì");
		giorni.put(DayOfWeek.FRIDAY, "Venerdì");
		giorni.put(DayOfWeek.SATURDAY, "Sabato");
		giorni.put(DayOfWeek.SUNDAY, "Domenica");
	}

	private JComponent createTabs() {
		JTabbedPane tabs = new JTabbedPane();

		tabs.add("Programmazione", createProgramPane());
		tabs.add("Area Cliente", createUserAreaPane());

		return tabs;
	}

	private JPanel createProgramPane() {
		JPanel outerPane = new JPanel();
		outerPane.setBorder(new LineBorder(Color.BLACK));
		outerPane.setLayout(new BorderLayout());
		giornoBox = new JComboBox<String>();
		for (int i = 1; i <= 7; i++)
			giornoBox.addItem(giorni.get(DayOfWeek.of(i)));

		JPanel innerPane = new JPanel();
		innerPane.setBorder(new BevelBorder(BevelBorder.LOWERED));

		outerPane.add(giornoBox, BorderLayout.NORTH);
		outerPane.add(innerPane, BorderLayout.CENTER);

		return outerPane;
	}

	private JPanel createUserAreaPane() {
		JPanel outerPane = new JPanel();
		outerPane.setBorder(new LineBorder(Color.BLACK));
		outerPane.setLayout(new BorderLayout());

		JPanel innerPane = new JPanel();
		innerPane.setBorder(new BevelBorder(BevelBorder.LOWERED));

		outerPane.add(innerPane, BorderLayout.CENTER);

		return outerPane;
	}

	private void createCloseOperation() {
		FrameCliente.this.addWindowListener(new WindowListener() {
			public void windowClosed(WindowEvent e) {
				parentFrame.setLocationRelativeTo(FrameCliente.this);
				parentFrame.setVisible(true);
			}

			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
		});
	}

	public GestoreCentrale getGestore() {
		return parentFrame.getGestore();
	}
}