import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * 
 * @author MarioELT
 * Oggetto che contiene il frame di visualizzazione dei posti a sedere dell'aereo
 */
public class FrameAereo extends JFrame {

	int mode;
	Aereo aereo;
	
	public FrameAereo(Aereo aereo) {
		
		super("Frame aereo");
		this.mode = 1;
		this.aereo = aereo;
		setSize(800, 400);
		
		JPanel body = createBody();
		
		add(body);
		
	}
	
	/**
	 * 
	 * @return JPanel con il corpo del frame
	 */
	public JPanel createBody() {
		JPanel sala=new JPanel();
		JPanel schermo=new JPanel();
		JPanel panel = new JPanel();
		add(schermo,BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);
		int file = aereo.getFile();
		int num = aereo.getNum();
		
		panel.setLayout(new GridLayout(6, 14));
		
		for (char c = 'A'; c < 'A' + file; c++)
		{
			for (int i = 1; i <= num; i++)
			{
				JPanel p = createPosto((c - 65), i-1);
				
				JLabel label = new JLabel(i + "" + c);
				p.add(label);
				panel.add(p);
			}
		}
		Draw screen=new Draw();
		schermo.paint(screen.getGraphics());
		
		return sala;
	}
	
	/**
	 * 
	 * @param i indice di riga del posto da creare
	 * @param j indice di colonna del posto da creare
	 * @return JPanel rappresentante il posto a sedere di indice [i][j], con il listener associato
	 */
	public JPanel createPosto(int i, int j) {
		
		JPanel panel = new JPanel();
		
		panel.setBorder(new EtchedBorder());
		
		if (aereo.check(i, j))
			panel.setBackground(Color.RED);
		else
			panel.setBackground(Color.GREEN);
		
		class MyListener implements MouseListener {

			public void mouseClicked(MouseEvent e) {
				
				try {
					
					aereo.esegui(i, j, mode);
					if (mode == 1)
						panel.setBackground(Color.RED);
					else
						panel.setBackground(Color.GREEN);
					save("aereo.dat");
					
				} catch (IOException e1) {
					
					System.out.println("Errore di I/O");
					
				} catch (WrongActionException ex) {
					
					System.out.println(ex);
				}
				
			}

			public void mousePressed(MouseEvent e) {}
			
			public void mouseReleased(MouseEvent e) {}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}			
			
		};
		
		panel.addMouseListener(new MyListener());
		
		return panel;
		
	}
	
	/**
	 * 
	 * @param mode modalità di visualizzazione del frame: 1 prenotazione, 0 cancellazione
	 * @throws WrongActionException eccezione argomento non valido
	 */
	public void show(int mode) throws WrongActionException{
		
		if (mode != 0 && mode != 1)
			throw new WrongActionException("Parametro mode non valido!");
		
		
		this.mode = mode;
		setVisible(true);
		
	}
	
	/**
	 * 
	 * @param name nome del file in cui salvare l'oggetto
	 * @throws FileNotFoundException eccezione file non trovato
	 * @throws IOException eccezione di I/O
	 */
	public void save(String name) throws FileNotFoundException, IOException {
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name));
		out.writeObject(aereo);
		out.close();
		
	}
	
	class Draw extends JPanel
	{
		public Draw()
		{
			
		}
		public void paintComponent(Graphics2D g)
		{
			Rectangle2D.Double rec=new Rectangle2D.Double(20,20,getWidth()-20,getHeight()-20);
			g.draw(rec);
		}
	}
}
