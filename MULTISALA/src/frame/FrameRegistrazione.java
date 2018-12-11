package frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import eccezioni.AccountGiaPresenteException;
import eccezioni.CampiVuotiException;
import eccezioni.CartaNonValidaException;
import gestione.GestoreCentrale;
import utente.AccountCliente.Gruppo;

public class FrameRegistrazione extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	private JTextField usernameField;
	private JPasswordField passwordField;
	private JComboBox<String> groupBox;
	private JTextField creditCardField;

	private GestoreCentrale gestore;

	public FrameRegistrazione(JFrame parentFrame, GestoreCentrale gestore) {
		super(parentFrame, true);
		this.gestore = gestore;
		setTitle("Cinema - Registrazione");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setResizable(false);

		add(createFields(), BorderLayout.CENTER);
		add(createConfirmButton(), BorderLayout.SOUTH);
	}

	private JPanel createFields() {
		JPanel pane = new JPanel(new GridLayout(4, 1));

		usernameField = new JTextField(10);
		JPanel usernamePane = new JPanel();
		usernamePane.add(new JLabel("Username:"));
		usernamePane.add(usernameField);

		passwordField = new JPasswordField(10);
		JPanel passwordPane = new JPanel();
		passwordPane.add(new JLabel("Password:"));
		passwordPane.add(passwordField);

		groupBox = new JComboBox<String>();
		groupBox.setEditable(true);
		groupBox.addItem("Adulto");
		groupBox.addItem("Under-14");
		groupBox.addItem("Over-60");
		groupBox.setSelectedIndex(0);
		JPanel groupPane = new JPanel();
		groupPane.add(new JLabel("Gruppo:"));
		groupPane.add(groupBox);

		creditCardField = new JTextField(15);
		JPanel creditCardPane = new JPanel();
		creditCardPane.add(new JLabel("Carta di credito:"));
		creditCardPane.add(creditCardField);

		pane.add(usernamePane);
		pane.add(passwordPane);
		pane.add(groupPane);
		pane.add(creditCardPane);
		return pane;
	}

	private JPanel createConfirmButton() {
		JPanel pane = new JPanel();
		JButton confirmButton = new JButton("Conferma");

		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				String gruppoStr = (String) groupBox.getSelectedItem();
				String cc = creditCardField.getText();

				Gruppo gruppo;
				switch (gruppoStr) {
				case "Adulto":
					gruppo = Gruppo.ADULTO;
					break;
				case "Under-14":
					gruppo = Gruppo.UNDER14;
					break;
				case "Over-60":
					gruppo = Gruppo.OVER60;
					break;
				default:
					gruppo = Gruppo.ADULTO;
				}

				try {
					gestore.registraCliente(username, password, gruppo, cc);
					JOptionPane.showMessageDialog(null, "Account creato con successo.", "Successo",
							JOptionPane.INFORMATION_MESSAGE);
					gestore.salvaSistema();
					FrameRegistrazione.this.dispose();
				} catch (CampiVuotiException e1) {
					JOptionPane.showMessageDialog(null, "Inserisci tutti i dati.", "Errore", JOptionPane.ERROR_MESSAGE);
				} catch (CartaNonValidaException e1) {
					JOptionPane.showMessageDialog(FrameRegistrazione.this,
							"Il numero di carta di credito deve essere minimo di sedici cifre.", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (AccountGiaPresenteException e1) {
					JOptionPane.showMessageDialog(FrameRegistrazione.this, "Esiste gi� un account con questo nome.",
							"Errore", JOptionPane.ERROR_MESSAGE);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		pane.add(confirmButton);
		return pane;
	}

}
