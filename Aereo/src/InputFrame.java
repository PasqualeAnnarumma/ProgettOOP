import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author MarioELT
 * InputFrame crea un frame con due JRadioButton che permettono di prenotare o cancellare
 * una prenotazione.
 */
public class InputFrame extends JFrame {
	
	int mode;
	Aereo aereo;
	
	/**
	 * 
	 * @param aereo Oggetto Aereo contenente tutte le informazioni sui posti dell'aereo
	 * 				e sui posti disponibili
	 */
	public InputFrame(Aereo aereo) {
		super("Selezionare la modalità");
		mode = 1;
		this.aereo = aereo;
		setSize(300, 150);
		setLocation(500, 200);
		
		JPanel body = createBody();
		
		add(body);
	}
	
	/**
	 * 
	 * @return JPanel contenente il corpo del frame
	 */
	public JPanel createBody() {
		
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		
		JPanel modePanel = createModePanel();
		JPanel buttonPanel = createButtonPanel();
		
		body.add(modePanel, BorderLayout.NORTH);
		body.add(buttonPanel, BorderLayout.CENTER);
		
		return body;
		
	}
	
	/**
	 * 
	 * @return JPanel contenente il JradioButton
	 */
	public JPanel createModePanel() {
		
		JPanel panel = new JPanel();
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Seleziona modalità"));
		
		JRadioButton prenotazione = createRadio("Prenotazione");
		JRadioButton cancellazione = createRadio("Cancellazione");
		
		ButtonGroup group = new ButtonGroup();
		group.add(prenotazione);
		group.add(cancellazione);
		prenotazione.setSelected(true);
		
		panel.add(prenotazione);
		panel.add(cancellazione);
		
		return panel;
		
	}
	
	/**
	 * 
	 * @return JPanel contenente il tasto "OK"
	 */
	public JPanel createButtonPanel() {
		
		JPanel panel = new JPanel();
		
		JButton button = new JButton("OK");
		
		class MyListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			FrameAereo a = new FrameAereo(aereo);
			a.show(mode);
			
		}
		
		};
		
		button.addActionListener(new MyListener());
		
		panel.add(button);
		
		return panel;
		
	}
	
	/**
	 * 
	 * @param s Nome del JradioButton
	 * @return JRadioButton con l'actionListener associato
	 */
	public JRadioButton createRadio(String s) {
		
		JRadioButton radio = new JRadioButton(s);
		
		radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radio.getText().compareTo("Prenotazione") == 0)
					mode = 1;
				else
					mode = 0;
			}
		});
		
		return radio;
		
	}

}
