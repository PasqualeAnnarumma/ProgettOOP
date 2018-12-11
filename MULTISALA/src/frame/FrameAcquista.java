package frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;

import gestione.GestoreCentrale;
import multisala.Sala;

public class FrameAcquista extends JFrame {
	private static final long serialVersionUID = 1L;

	public FrameAcquista(FrameCliente frameCliente,Sala s) {
		setSize(600, 500);
		add(new FrameSala(s,"Acquistato"));			//da modificare in base alle esigenze
		setLocationRelativeTo(frameCliente);
		frameCliente.setVisible(false);
		createCloseOperation(frameCliente);
	}

	private void createCloseOperation(FrameCliente frameCliente) {
		FrameAcquista.this.addWindowListener(new WindowListener() {

			@Override
			public void windowClosed(WindowEvent e) {
				try {
					GestoreCentrale gestore = frameCliente.getGestore();
					gestore.salvaSistema();
					frameCliente.setVisible(true);
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
}
