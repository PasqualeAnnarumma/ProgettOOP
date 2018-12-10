import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Tester {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane body = createBody();
		frame.add(body);
		
		frame.setVisible(true);
	}
	
	public static JScrollPane createBody() {
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(20, 1));
		JScrollPane scroll = new JScrollPane(body);
		JPanel line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		line = createLine();
		body.add(line);
		return scroll;
		
	}
	
	public static JPanel createLine() {
		JPanel line = new JPanel();
		line.setLayout(new GridLayout(1, 3));
		JLabel nome = new JLabel("Film");
		JLabel ora =  new JLabel("20:30");
		JLabel data = new JLabel("20/01/2019");
		line.add(nome);
		line.add(ora);
		line.add(data);
		line.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) {}
			
			public void mouseExited(MouseEvent e) {
				line.setBackground(null);
			}
			
			public void mouseEntered(MouseEvent e) {
				line.setBackground(Color.BLUE);
			}
			
			public void mouseClicked(MouseEvent e) {
				System.out.println("Bisogno merda!");
			}
		});
		return line;
	}
}
