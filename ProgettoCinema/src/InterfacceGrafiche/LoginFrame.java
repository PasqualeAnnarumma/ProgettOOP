package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Eccezioni.AccountGiaEsistenteException;
import GestoreLogin.Amministratore;
import GestoreLogin.Cinema;
import GestoreLogin.Cliente;

public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Cinema cinema;
	private JTextField userField;
	private JPasswordField pswField;
	private JButton registerButton;
	private JButton loginButton;
	
	public LoginFrame(Cinema c) {
		super("Login");
		cinema = c;
		userField = new JTextField(20);
		pswField = new JPasswordField(20);
		setResizable(false);
		registerButton = new JButton("Registrati");
		loginButton = new JButton("Login");
		JPanel body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		JPanel fieldLogin = createField();
		JPanel buttonLogin = createButtonLogin();
		body.add(fieldLogin, BorderLayout.NORTH);
		body.add(buttonLogin, BorderLayout.CENTER);
		return body;
	}
	
	public JPanel createField() {
		JPanel field = new JPanel();
		field.setLayout(new BorderLayout());
		JPanel line1 = createLine("Username :", userField);
		JPanel line2 = createLine("Password :", pswField);
		field.add(line1, BorderLayout.NORTH);
		field.add(line2, BorderLayout.CENTER);
		return field;
	}
	
	public JPanel createButtonLogin() {
		JPanel buttonLogin = new JPanel();
		
		WindowListener listener = new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			
			public void windowIconified(WindowEvent e) {}
			
			public void windowDeiconified(WindowEvent e) {}
			
			public void windowDeactivated(WindowEvent e) {}
			
			public void windowClosing(WindowEvent e) {}
			
			public void windowClosed(WindowEvent e) {
				userField.setText("");
				pswField.setText("");
				setVisible(true);
			}
			
			public void windowActivated(WindowEvent e) {}
		};
		
		//BOTTONE LOGIN
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cinema.login(userField.getText(), pswField.getText()))
				{
					if(cinema.getUtente() instanceof Cliente)
					{
						FrameUtente frame = new FrameUtente((Cliente)cinema.getUtente(), cinema);
						frame.setVisible(true);
						frame.addWindowListener(listener);
						setVisible(false);
					}
					else if(cinema.getUtente() instanceof Amministratore)
					{
						FrameGestore frame = new FrameGestore((Amministratore)cinema.getUtente(), cinema);
						frame.setVisible(true);
						frame.addWindowListener(listener);
						setVisible(false);
					}
				}
				else
					System.out.println("Credenziali errate");
			}
		});
		
		//BOTTONE REGISTRATI
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cinema.registraCliente(userField.getText(), pswField.getText());
				} catch (AccountGiaEsistenteException e1) {
					System.out.println(e1);
				}
			}
		});
		
		buttonLogin.add(loginButton);
		buttonLogin.add(registerButton);
		return buttonLogin;
	}
	
	public JPanel createLine(String txt, JTextField field) {
		JPanel line = new JPanel();
		JLabel label = new JLabel(txt);
		line.add(label);
		line.add(field);
		return line;
	}	
}
