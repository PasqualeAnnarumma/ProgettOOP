package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import GestoreLogin.Amministratore;
import cinema.Cinema;
import GestoreLogin.Cliente;

/**
 * LoginFrame è il frame che si occupa dell'input per il login
 * @author MarioELT
 *
 */
public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Cinema cinema;
	private JTextField userField;
	private JPasswordField pswField;
	private JButton registerButton;
	private JButton loginButton;
	
	/**
	 * Crea il frame
	 * @param c oggetto cinema del sistema
	 */
	public LoginFrame(Cinema c) {
		super("Login");
		cinema = c;
		Image img = Toolkit.getDefaultToolkit().getImage("src//iconeFinestra//login.png");
		setIconImage(img);
		userField = new JTextField(20);
		pswField = new JPasswordField(20);
		setResizable(false);
		registerButton = new JButton("Registrati");
		loginButton = new JButton("Login");
		JPanel body = createBody();
		add(body);
	}
	
	/**
	 * Crea il corpo centrale del frame
	 * @return pannello centrale del frame
	 */
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		JPanel fieldLogin = createField();
		JPanel buttonLogin = createButtonLogin();
		body.add(fieldLogin, BorderLayout.NORTH);
		body.add(buttonLogin, BorderLayout.CENTER);
		return body;
	}
	
	/**
	 * Crea il pannello contenente l'username e la password per il login
	 * @return pannello user e password
	 */
	public JPanel createField() {
		JPanel field = new JPanel();
		field.setLayout(new BorderLayout());
		JPanel line1 = createLine("Username :", userField);
		JPanel line2 = createLine("Password :", pswField);
		field.add(line1, BorderLayout.NORTH);
		field.add(line2, BorderLayout.CENTER);
		return field;
	}
	
	/**
	 * Crea il pannello con il pulsante di login
	 * @return pannello con il pulsante di login
	 */
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
				String psw = String.valueOf(pswField.getPassword());
				if(cinema.login(userField.getText(), psw))
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
						FrameGestore frame = new FrameGestore(cinema);
						frame.setVisible(true);
						frame.addWindowListener(listener);
						setVisible(false);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Credenziali errate", "Errore login!", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		//BOTTONE REGISTRATI
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrationFrame frame = new RegistrationFrame(cinema);
				frame.setVisible(true);
				frame.addWindowListener(listener);
				setVisible(false);
			}
		});
		
		buttonLogin.add(loginButton);
		buttonLogin.add(registerButton);
		return buttonLogin;
	}
	
	/**
	 * Crea il pannello con la stringa e il JTetField specificati
	 * @param txt stringa della label
	 * @param field textField da inserire nel pannello
	 * @return pannello con stringa e JTextField
	 */
	public JPanel createLine(String txt, JTextField field) {
		JPanel line = new JPanel();
		JLabel label = new JLabel(txt);
		line.add(label);
		line.add(field);
		return line;
	}	
}
