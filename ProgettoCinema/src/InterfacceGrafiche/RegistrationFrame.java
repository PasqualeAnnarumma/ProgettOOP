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
import javax.swing.JPasswordField;
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
	private JLabel condizioniField;
	private JRadioButton accettoButton;
	private JComboBox<String> comboGiorno;
	private JComboBox<String> comboMese;
	private JComboBox<String> comboAnno;
	//private JPanel condizioniPanel;
	private Amministratore gestore;
	private Cinema cinema;
	
	public RegistrationFrame(Cinema cinema) {
		super("Cinema - Registrazione");
		this.cinema = cinema;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,300);
		setResizable(false);
		setLocation(400, 200);
		comboGiorno = new JComboBox<String>();
		comboMese = new JComboBox<String>();
		comboAnno = new JComboBox<String>();
		popolaData();
		add(createFields(), BorderLayout.CENTER);
		add(createConfirmButton(), BorderLayout.SOUTH);
	}

	private JPanel createFields() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel campi = creaCampi();
		
		panel.add(campi, BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel creaCampi() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		
		JPanel userPanel = createUserPanel();
		JPanel passwordPanel = createPswwrodPanel();
		JPanel groupPanel = createGroupPanel();
		JPanel etaPanel = createEtaPanel();
		JPanel condizioniPanel = createCondizioniPanel();
		
		panel.add(userPanel);
		panel.add(passwordPanel);
		panel.add(groupPanel);
		panel.add(etaPanel);
		panel.add(condizioniPanel);
		
		return panel;
	}
	
	private JPanel createUserPanel() {
		JPanel panel = new JPanel();
		userField = new JTextField(10);
		JPanel usernamePanel = new JPanel(new GridLayout(1, 2));
		usernamePanel.add(new JLabel("Username:"));
		usernamePanel.add(userField);
		panel.add(usernamePanel);
		return panel;
	}
	
	private JPanel createPswwrodPanel() {
		JPanel panel = new JPanel();
		passwordField = new JPasswordField(10);
		JPanel passwordPanel = new JPanel(new GridLayout(1, 2));
		passwordPanel.add(new JLabel("Password:"));
		passwordPanel.add(passwordField);
		panel.add(passwordPanel);
		return panel;
	}
	
	private JPanel createGroupPanel() {
		JPanel panel = new JPanel();
		groupBox = new JComboBox<String>();
		groupBox.setEditable(false);
		groupBox.addItem("Nessuno");
		groupBox.addItem("Studente");
		groupBox.addItem("Pensionato");
		groupBox.setSelectedIndex(0);
		JPanel groupPanel = new JPanel(new GridLayout(1, 2));
		groupPanel.add(new JLabel("Gruppo:"));
		groupPanel.add(groupBox);
		panel.add(groupPanel);
		return panel;
	}
	
	private JPanel createEtaPanel() {
		JPanel panel = new JPanel();
		JPanel etaPanel = new JPanel();
		etaPanel.add(new JLabel("Data di nascita:"));
		etaPanel.add(comboGiorno);
		etaPanel.add(comboMese);
		etaPanel.add(comboAnno);
		panel.add(etaPanel);
		return panel;
	}
	
	private JPanel createCondizioniPanel() {
		JPanel panel = new JPanel();
		JPanel condizioniPanel = new JPanel();
		condizioniPanel.setBackground(new Color(200, 200, 200));
		condizioniPanel.setSize(200,200);
		condizioniField = new JLabel();
		condizioniField.setText("Accetto le condizioni di vendita");
		accettoButton =new JRadioButton("accetto");
		condizioniPanel.add(condizioniField);
		condizioniPanel.add(accettoButton);
		panel.add(condizioniPanel);
		return panel;
	}

	private JPanel createConfirmButton() {
		JPanel panel = new JPanel();
		JButton confirmButton = new JButton("Conferma");

		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userField.getText().equals("") || passwordField.getText().equals("") || accettoButton.isSelected() == false)
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ATTENZIONE!", JOptionPane.WARNING_MESSAGE);
				else
				{
					try {
						//NON FUNZIONA LA REGISTRAZIONE A ME MA È INSOLITA LA COSA...
						//RISOLTO: CINEMA ERA NULLO
						String dataNascita = comboGiorno.getSelectedItem() + "/" + comboMese.getSelectedItem() + "/" + comboAnno.getSelectedItem();
						cinema.registraCliente(userField.getText(), passwordField.getText(),0, dataNascita);
						JOptionPane.showMessageDialog(null,"Registrazione effettuata con successo", "Registrazione completata", JOptionPane.INFORMATION_MESSAGE);
						userField.setText("");
						passwordField.setText("");
						accettoButton.setSelected(false);
					} catch (AccountGiaEsistenteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ATTENZIONE!", JOptionPane.ERROR_MESSAGE);
						userField.setText("");
						passwordField.setText("");
						accettoButton.setSelected(false);
					}
				}
			}
		});
		
		panel.add(confirmButton);
		return panel;
	}
	
	public void popolaData() {
		for (int i = 1; i <= 31; i++)
		{
			String s = i + "";
			if (i < 10)
				s = "0" + i;
			comboGiorno.addItem(s);
		}
		
		for (int i = 1; i <= 12; i++)
		{
			String s = i + "";
			if (i < 10)
				s = "0" + i;
			comboMese.addItem(s);
		}
		
		for (int i = 0; i < 300; i++)
			comboAnno.addItem((1900 + i) + "");
	}

}
