import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.BoxView;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException
	{
		
		JFrame frame = new JFrame();
		frame.setTitle("Cifrario");
		frame.setSize(500, 300);
		frame.setLocation(500, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFrame frame2 = new JFrame();
		frame.setTitle("input");
		frame.setSize(30, 20);
		frame.setLocation(500, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("Cifra messaggio");
		JButton decript = new JButton("Decifra");
		
		String key;
		key = JOptionPane.showInputDialog("Dammi la chiave di criptazione:");
		//String r = getStringInput(chiave);
		
		JLabel label = new JLabel("Message");
		JLabel chiave = new JLabel("Chiave:");
		JLabel lab = new JLabel("Message");
		
		JTextField field = new JTextField("mario", 10);
		JLabel chiavef = new JLabel(key);
		
		
		JPanel panel = new JPanel();
		panel.add(chiave);
		panel.add(chiavef);
		panel.add(field);
		panel.add(button);
		panel.add(label);
		panel.add(decript);
		panel.add(lab);
		
		
		frame.add(panel);
		frame.setVisible(true);
		
		//CONTROLLO SE ESISTE IL FILE
		File file = new File("message.dat");
		if (!file.exists())
		{
			System.out.println("Il file non esiste, lo creo");
			label.setText("NULL");
			String a="alfabeto";
			PrintWriter writer = new PrintWriter(file);
			writer.write(a);
			
			writer.close();
		}
		else
		{
			FileReader read = new FileReader(file);
			Scanner in = new Scanner(read);
			String name = in.nextLine();
			label.setText(name);
			in.close();
		}
		
		//PULSANTE CODIFICA
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Cifrario c = new Cifrario(chiavef.getText());
				String s2 = c.codifica(field.getText());
				label.setText(s2);
				
				try {
					PrintWriter out = new PrintWriter(file);
					out.println(s2);
					
					out.close();
					
				} catch (FileNotFoundException ex) {
					
					System.out.println("File non trovato");
					
				}
			}
		});
		
		//PULSANTE DECIFRA
		decript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cifrario c = new Cifrario(chiavef.getText());
				lab.setText(c.deCodifica(field.getText()));
				
			}
		});
	}

}