package frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import multisala.Posto;
import multisala.Posto.Stato;
import multisala.Sala;

public class FrameSala extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel[] posti;
	JLabel prec, label;
	String posto;
	boolean x = false;
	ImageIcon iconDisponibile, iconPrenotato, iconIndisponibile, iconAcquistato, iconSelezionato;
	
	public FrameSala(Sala s, String modalita){
		JPanel panelSala = new JPanel();
		char c = 'A';
		panelSala.setLayout(new GridLayout(s.getNumeroFile()+1,s.getPostiPerFila()+1)); 
		posti = new JLabel[s.getNumeroPosti()];			
		iconDisponibile = createIcon("disponibile");
		iconPrenotato = createIcon("prenotato");
		iconIndisponibile = createIcon("indisponibile");
		iconAcquistato = createIcon("acquistato");
		iconSelezionato = createIcon("selezionato");
		ArrayList<Posto> list=s.getPosti();
		
		class SelezionePostoListener implements MouseListener{
			public void mouseClicked(MouseEvent e) {
				JLabel label = (JLabel) e.getComponent();
				label.setIcon(iconSelezionato);
				posto = label.getName();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		}
		
		MouseListener ml = new SelezionePostoListener();
		
		panelSala.add(new JLabel("    "));
		for (int j=0; j<s.getPostiPerFila(); j++)
			panelSala.add(new JLabel("    " + (j+1)));
		
		for (int i=0; i< s.getNumeroPosti(); i++){
			ImageIcon temp=(list.get(i).getStato()==Stato.DISPONIBILE) ? iconDisponibile : (list.get(i).getStato()==Stato.INDISPONIBILE) ?
					iconIndisponibile : (list.get(i).getStato()==Stato.PRENOTATO) ? iconPrenotato : iconAcquistato;
			posti[i] = new JLabel(temp);
			posti[i].setName(list.get(i).getIdPosto());
			posti[i].addMouseListener(ml);
			if (i%s.getPostiPerFila()==0)
				panelSala.add(new JLabel(c++ + ""));
			panelSala.add(posti[i]);
		}
		
		JButton invia = new JButton("Effettua l'operazione");
		
		class InviaPostoListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.out.println("Il biglietto riguardo il posto " + posto + " è stato venduto!"); //cambiare
				}
		}
		ActionListener inviaL = new InviaPostoListener();
		invia.addActionListener(inviaL);
		add(panelSala);
		add(invia);
		
	}
	
	public ImageIcon createIcon(String disponibilita){
		ImageIcon icon = new ImageIcon("src\\icons\\posto_" + disponibilita + ".png");
		Image image = icon.getImage(); 
		Image newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);   
		icon = new ImageIcon(newimg); 
		return icon;
	}

}

/*
class SelezionePostoListener implements MouseListener{
public void mouseClicked(MouseEvent e) {
	JLabel label = (JLabel) e.getComponent();
	int index = Integer.parseInt(label.getName());
	 switch(modalita) {
	    case "Prenotato":
	    	label.setIcon(iconPrenotato);
	    	list.get(index).setStato(Stato.PRENOTATO);
	    	break;
	    case "Acquistato":
	    	label.setIcon(iconAcquistato); 
	    	list.get(index).setStato(Stato.VENDUTO);
	    	break;
	    case "Indisponibile":
	    	label.setIcon(iconIndisponibile);
	    	list.get(index).setStato(Stato.INDISPONIBILE);
	    	break;
	    case "Disponibile":
	    	label.setIcon(iconDisponibile);
	    	list.get(index).setStato(Stato.DISPONIBILE);
	    	break;
	  }
	
}
*/