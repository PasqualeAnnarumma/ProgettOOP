import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		File file = new File("aereo.dat");
		Aereo aereo;
		
		if (file.exists())
		{
			System.out.println("Il file esiste, lo leggo");
			ObjectInputStream obj = new ObjectInputStream(new FileInputStream(file));
			aereo = (Aereo) obj.readObject();
			obj.close();
		}
		else
		{
			System.out.println("Il file non esiste, lo creo");
			aereo = new Aereo(6, 14);
			aereo.esegui(1, 7, 1);
			aereo.esegui(5, 9, 1);
			
			System.out.println("Posti totali sull'aereo " + aereo.getTotalPosti());
			ArrayList<String> posti = aereo.postiOccupati();
			System.out.println("Lista posti liberi");
			
			int i = 0;
			for (String s : posti)
			{
				if (i % 14 == 0) System.out.println("");
				i++;
				System.out.print(s + " ");
			}
			
			ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));
			obj.writeObject(aereo);
			obj.close();
		}
		
		
		InputFrame input = new InputFrame(aereo);
		input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input.setVisible(true);
	}

}
