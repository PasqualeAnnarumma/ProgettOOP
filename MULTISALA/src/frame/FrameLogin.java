package frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eccezioni.AccountNonPresenteException;
import eccezioni.CampiVuotiException;
import eccezioni.PasswordErrataException;
import gestione.GestoreCentrale;
import utente.Account;
import utente.AccountCliente;

public class FrameLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 150;

	private JTextField usernameField;
	private JPasswordField passwordField;

	private GestoreCentrale gestore;

	public FrameLogin() throws FileNotFoundException, ClassNotFoundException, IOException {
		setTitle("Cinema - Login");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setResizable(false);
		
		gestore = new GestoreCentrale();

		add(createFields(), BorderLayout.CENTER);
		add(createButtons(), BorderLayout.SOUTH);
		createCloseOperation();
	}

	private JPanel createFields() {
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);

		JPanel gridPane = new JPanel(new GridLayout(2, 1));

		JPanel innerUsernamePane = new JPanel();
		JPanel innerPasswordPane = new JPanel();
		innerUsernamePane.add(new JLabel("Username:"));
		innerUsernamePane.add(usernameField);
		innerPasswordPane.add(new JLabel("Password"));
		innerPasswordPane.add(passwordField);

		gridPane.add(innerUsernamePane);
		gridPane.add(innerPasswordPane);

		JPanel outerPane = new JPanel();
		outerPane.add(gridPane);

		return outerPane;
	}

	private JPanel createButtons() {
		JButton loginButton = new JButton("Login");
		JButton registerButton = new JButton("Registrati");

		// login con account
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Account logged = gestore.autentica(usernameField.getText(),
							String.valueOf(passwordField.getPassword()));
					switch (logged.getLivelloAutorizzazione()) {
					case GESTORE:
						JFrame frameGestore = new FrameGestore(FrameLogin.this, gestore.getSistema().getMultisala());
						frameGestore.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frameGestore.setLocationRelativeTo(FrameLogin.this);
						frameGestore.setVisible(true);
						break;
					case CLIENTE:
						JFrame frameCliente = new FrameCliente(FrameLogin.this, (AccountCliente) logged);
						frameCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frameCliente.setLocationRelativeTo(FrameLogin.this);
						frameCliente.setVisible(true);
						break;
					}
					FrameLogin.this.setVisible(false);
				} catch (AccountNonPresenteException e1) {
					JOptionPane.showMessageDialog(null, "Account non presente.", "Errore", JOptionPane.ERROR_MESSAGE);
				} catch (PasswordErrataException e1) {
					JOptionPane.showMessageDialog(null, "Password sbagliata.", "Errore", JOptionPane.ERROR_MESSAGE);
				} catch (CampiVuotiException e1) {
					JOptionPane.showMessageDialog(null, "Inserisci i dati.", "Errore", JOptionPane.ERROR_MESSAGE);
				}finally {
					usernameField.setText("");
					passwordField.setText("");
				}

			}
		});

		// registrazione account
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog frameRegistrazione = new FrameRegistrazione(FrameLogin.this, gestore);
				frameRegistrazione.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frameRegistrazione.setLocationRelativeTo(FrameLogin.this);
				frameRegistrazione.setVisible(true);
			}
		});

		JPanel pane = new JPanel();
		pane.add(loginButton);
		pane.add(registerButton);
		return pane;
	}
	
	private void createCloseOperation() {
		FrameLogin.this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					gestore.salvaSistema();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		return gestore;
	}
}
