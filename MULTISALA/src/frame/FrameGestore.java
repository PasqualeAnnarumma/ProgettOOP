package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import gestione.GestoreMultisala;
import multisala.Multisala;

public class FrameGestore extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 500;	
	
	private JFrame parentFrame;
	private GestoreMultisala gestoreMultisala;
	
	public FrameGestore(JFrame parentFrame, Multisala multisala) {
		this.parentFrame = parentFrame;
		gestoreMultisala = new GestoreMultisala(multisala);
		
		setTitle("Cinema - Gestione");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		add(createTabs(), BorderLayout.CENTER);
		createCloseOperation();
	}
	
	
	private JComponent createTabs() {
		JTabbedPane tabs = new JTabbedPane();

		tabs.add("Programma", createProgrammaTab());
		tabs.add("Film", createFilmTab());
		tabs.add("Sale", createSaleTab());
		tabs.add("Incassi", createIncassiTab());

		return tabs;
	}
	
	
	private JPanel createProgrammaTab() {
		JPanel outerPane = new JPanel();
		outerPane.setBorder(new LineBorder(Color.BLACK));
		outerPane.setLayout(new BorderLayout());
		
		JPanel innerPane = new JPanel();//Implementare
		innerPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		
		
		outerPane.add(innerPane, BorderLayout.CENTER);
		return outerPane;
	}
	
	private JPanel createFilmTab() {
		JPanel outerPane = new JPanel();
		outerPane.setBorder(new LineBorder(Color.BLACK));
		outerPane.setLayout(new BorderLayout());
		
		JPanel innerPane = new JPanel();//Implementare
		innerPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		JButton aggiungiFilm = new JButton("Aggiungi");
		aggiungiFilm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog frame = new FrameNuovoFilm(FrameGestore.this, gestoreMultisala.getMultisala());
				frame.setLocationRelativeTo(FrameGestore.this);
				frame.setVisible(true);
			}
		});
				
		outerPane.add(innerPane, BorderLayout.CENTER);
		outerPane.add(aggiungiFilm, BorderLayout.NORTH);
		return outerPane;
	}
	
	private JPanel createSaleTab() {
		JPanel outerPane = new JPanel();
		outerPane.setBorder(new LineBorder(Color.BLACK));
		outerPane.setLayout(new BorderLayout());
		
		JPanel innerPane = new JPanel();//Implementare
		innerPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		outerPane.add(innerPane, BorderLayout.CENTER);
		return outerPane;
	}
	
	private JPanel createIncassiTab() {
		JPanel outerPane = new JPanel();
		outerPane.setBorder(new LineBorder(Color.BLACK));
		outerPane.setLayout(new BorderLayout());
		
		JPanel innerPane = new JPanel();
		innerPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		outerPane.add(innerPane, BorderLayout.CENTER);
		return outerPane;
	}
	
	private void createCloseOperation() {
		FrameGestore.this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				parentFrame.setLocationRelativeTo(FrameGestore.this);
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
}

