package viewer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import frame.FrameLogin;

public class Viewer {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		JFrame frame = new FrameLogin();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
