package InterfacceGrafiche;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import cinema.Cinema;
import GestoreProgrammazione.Film;

/**
 * FrameFilm rappresenta l'interfaccia grafica per l'aggiunta dei film
 * @author MarioELT
 *
 */
public class FrameFilm extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Cinema cinema;
	private JTextField nomeFilm;
	private JTextField nomeProduttore;
	private JComboBox<Integer> comboOre;
	private JComboBox<Integer> comboMinuti;
	private JTextField immagineText;
	private JFileChooser chooser;
	
	/**
	 * Costruisce il frame
	 * @param cin oggetto cinema del sistema
	 */
	public FrameFilm(Cinema cin) {
		super("Aggiungi film");
		cinema = cin;
		setLocation(500, 100);
		setSize(300, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel body = createBody();
		add(body, BorderLayout.WEST);
		pack();
	}
	
	/**
	 * Crea il pannello per il corpo centrale
	 * @return pannello corpo centrale
	 */
	public JPanel createBody() {
		JPanel body = new JPanel(new GridLayout(5, 1));
		JPanel panelName = createPanelName();
		JPanel panelDurata = createPanelDurata();
		JPanel panelProduttore = createPanelProduttore();
		JPanel panelImage = createImagePanel();
		
		JButton aggiungi = new JButton("Aggiungi");
		
		//BOTTONE AGGIUNGI
		aggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String durata = comboOre.getSelectedItem() + ":" + comboMinuti.getSelectedItem();
				String percorso = chooser.getSelectedFile().getAbsolutePath();
				File origine = new File(percorso);
				File destinazione = new File("src\\copertine\\" + chooser.getSelectedFile().getName());
				
				try {
					BufferedImage inputImage = ImageIO.read(origine);
					BufferedImage outputImage = new BufferedImage(50, 71, Image.SCALE_SMOOTH);
					Graphics2D g2 = outputImage.createGraphics();
					g2.drawImage(inputImage, 0, 0, 50, 71, null);
					g2.dispose();
					ImageIO.write(outputImage, "jpg", destinazione);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Impossibile convertire l'immagine", "Errore!", JOptionPane.ERROR_MESSAGE);
				}
				
				String nomeFile = chooser.getSelectedFile().getName();
				int lunghezza = nomeFile.length();
				nomeFile = nomeFile.substring(0, lunghezza-4);
				Film film = new Film(nomeFilm.getText(), durata, nomeProduttore.getText(), nomeFile);
				cinema.aggiungiFilm(film);
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(aggiungi);
		
		body.add(panelName);
		body.add(panelProduttore);
		body.add(panelImage);
		body.add(panelDurata);
		body.add(buttonPanel);
		return body;
	}
	
	/**
	 * Crea il pannello per l'inserimento del nome del film
	 * @return pannello inserimento film
	 */
	public JPanel createPanelName() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel label = new JLabel("Nome film : ");
		nomeFilm = new JTextField(20);
		JPanel name = new JPanel();
		name.add(nomeFilm);
		panel.add(label);
		panel.add(name);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'inserimento della durata
	 * @return panello inserimento durata
	 */
	public JPanel createPanelDurata() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel label = new JLabel("Durata : ");
		comboOre = new JComboBox<Integer>();
		comboMinuti = new JComboBox<Integer>();
		for (int i = 1; i <= 5; i++)
			comboOre.addItem(i);
		
		for (int i = 0; i < 60; i++)
			comboMinuti.addItem(i);
		
		JPanel p = new JPanel();
		p.add(comboOre);
		p.add(comboMinuti);
		panel.add(label);
		panel.add(p);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'inserimento del produttore
	 * @return pannello inserimento produttore
	 */
	public JPanel createPanelProduttore() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel label = new JLabel("Nome Produttore : ");
		nomeProduttore = new JTextField(20);
		JPanel prod = new JPanel();
		prod.add(nomeProduttore);
		panel.add(label);
		panel.add(prod);
		return panel;
	}
	
	/**
	 * Crea il pannello per l'inserimento dell'immagine
	 * @return pannello inserimento immagine
	 */
	public JPanel createImagePanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel label = new JLabel("Immagine : ");
		immagineText = new JTextField(18);
		immagineText.setEditable(false);
		JPanel panelImg = new JPanel();
		JButton button = new JButton("...");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Immagini", "jpg");
				chooser.setFileFilter(filtro);
				chooser.showOpenDialog(null);
				File selected = chooser.getSelectedFile();
				immagineText.setText(selected.getName());
			}
		});
		
		panelImg.add(immagineText);
		panelImg.add(button);
		panel.add(panelImg);
		panel.add(label);
		panel.add(panelImg);
		return panel;
	}
	
	/**
	 * Copia un file
	 * @param sourceFile file da copiare
	 * @param destFile destinazione file
	 * @throws IOException se ci sono errori di IO
	 * @throws FileNotFoundException se il file non viene trovato
	 */
	public static void copyFile(File sourceFile, File destFile) throws IOException, FileNotFoundException{
        InputStream in = null;
        OutputStream out = null;
        in = new FileInputStream(sourceFile);
        out = new FileOutputStream(destFile);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0)
        	out.write(buffer, 0, length);
        
        in.close();
        out.close();
	}
}
