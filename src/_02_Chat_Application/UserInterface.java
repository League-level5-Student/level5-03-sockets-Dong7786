package _02_Chat_Application;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UserInterface {

	JFrame frame;
	JPanel panel;
	JTextField text;
	

	public static final int width = 500;
	public static final int height = 800;

	UserInterface() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		panel = new JPanel();
			
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(width, height);

		text = new JTextField();
		panel.add(text);
		
		
		text.setSize(width, 200);
		text.setPreferredSize(text.getSize());
		text.setLocation(0, 100);
		text.setText("Enter Text Here");
	
	}

	public static void main(String[] args) {
		UserInterface user = new UserInterface();
	}
}
