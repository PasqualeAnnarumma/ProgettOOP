package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import Eccezioni.AccountGiaEsistenteException;
import GestoreLogin.Amministratore;
import GestoreLogin.Cinema;

public class RegistrationFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField userField;
	private JTextField passwordField;
	private JComboBox<String> groupBox;
	private JTextField giorno;
	private JTextField mese;
	private JTextField anno;
	private JLabel condizioniField;
	private JRadioButton accettoButton;
	//private JPanel condizioniPanel;
	private Amministratore gestore;
	private Cinema cinema;
	
	public RegistrationFrame(Cinema cinema) {
		super("Registrazione");
		this.cinema = cinema;
		setTitle("Cinema - Registrazione");
		setSize(400,300);
		setResizable(false);
		setLocation(400, 200);
		add(createFields(), BorderLayout.CENTER);
		add(createConfirmButton(), BorderLayout.SOUTH);
	}

	private JPanel createFields() {
		JPanel panel = new JPanel(new GridLayout(5, 1));

		userField = new JTextField(10);
		JPanel usernamePanel = new JPanel();
		usernamePanel.add(new JLabel("Username:"));
		usernamePanel.add(userField);

		passwordField = new JTextField(10);
		JPanel passwordPanel = new JPanel();
		passwordPanel.add(new JLabel("Password:"));
		passwordPanel.add(passwordField);

		groupBox = new JComboBox<String>();
		groupBox.setEditable(false);
		groupBox.addItem("Nessuno");
		groupBox.addItem("Studente");
		groupBox.addItem("Pensionato");
		groupBox.setSelectedIndex(0);
		JPanel groupPanel = new JPanel();
		groupPanel.add(new JLabel("Gruppo:"));
		groupPanel.add(groupBox);
		
		//etaField = new JTextField(10);
		giorno=new JTextField(2);
		mese=new JTextField(2);
		anno=new JTextField(4);
		
		JPanel etaPanel = new JPanel();
		etaPanel.add(new JLabel("Data di nascita:"));
		etaPanel.add(giorno);
		etaPanel.add(mese);
		etaPanel.add(anno);
		
		JPanel condizioniPanel =new JPanel();
		condizioniPanel.setBackground(new Color(200, 200, 200));
		//condizioniPanel.setLocation(200,200);
		condizioniPanel.setSize(200,200);
		condizioniField = new JLabel();
		condizioniField.setText("<html>Dono tutto me stesso alla De Felice."
				+ " Per tutto il resto c'è la documentazione</html>");
		
		accettoButton =new JRadioButton("accetto");
		condizioniPanel.add(condizioniField);
		condizioniPanel.add(accettoButton);
		panel.add(usernamePanel);
		panel.add(passwordPanel);
		panel.add(groupPanel);
		panel.add(etaPanel);
		panel.add(condizioniPanel);
		return panel;
	}

	private JPanel createConfirmButton() {
		JPanel panel = new JPanel();
		JButton confirmButton = new JButton("Conferma");

		confirmButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					//NON FUNZIONA LA REGISTRAZIONE A ME MA È INSOLITA LA COSA...
					//RISOLTO: CINEMA ERA NULLO
					cinema.registraCliente(userField.getText(), passwordField.getText(),0, giorno.getText());
					ImageIcon image=new ImageIcon("C:\\Users\\pasqu\\Documents\\mario.jpg");
					JOptionPane.showMessageDialog(null,"<html>UTENTE REGISTRATO SENZA NESSUN PROBLEMA DI NESSUN GENERE. <br> "
							+ "IL MESSAGGIO DEL GIORNO: <b><font color=red>MARIO È UNA MERDA PEGGIO DELLA DE FELICE</b></html>","MARIO MERDA",JOptionPane.INFORMATION_MESSAGE,image);
				} catch (AccountGiaEsistenteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(confirmButton);
		return panel;
	}

}
