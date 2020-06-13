package _02_Chat_Application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserInterface implements ActionListener, FocusListener, HierarchyBoundsListener {

	JFrame frame;
	JPanel panel;
	JTextArea text;

	JPanel textPanel;
	JPanel messagePanel;

	JButton sendButton;
	public static int width = 500;
	public static int height = 800;

	
	UserInterface() {
		frame = new JFrame("Chat App");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		panel = new JPanel();

		frame.add(panel);
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(width, height));
		panel.setPreferredSize(new Dimension(width, height));

		setPanels();

		setTextBox();

		frame.pack();
		frame.addHierarchyBoundsListener(this);
	}

	void setPanels() {
		textPanel = new JPanel();
		messagePanel = new JPanel();

		panel.add(messagePanel);
		panel.add(textPanel);

		messagePanel.setBackground(Color.blue);
		messagePanel.setPreferredSize(new Dimension(width, height - height / 4));

		textPanel.setBackground(Color.red);
		textPanel.setPreferredSize(new Dimension(width, height / 10));

	}

	void setTextBox() {
		text = new JTextArea();
		textPanel.add(text);
		sendButton = new JButton("Send");
		textPanel.add(sendButton);

		text.setPreferredSize(new Dimension(width - sendButton.getWidth() - 10, height / 20));
		text.setText("Test");
		text.addFocusListener(this);
		text.setText("Send a Message");
		
		sendButton.setBackground(Color.blue);
		sendButton.addActionListener(this);
		
	}

	public static void main(String[] args) {
		UserInterface user = new UserInterface();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton) {
			System.out.println(frame.getSize());
		}
		
	}

	boolean isEmpty = true;
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == text && isEmpty) {
			text.setText(null);
			System.out.println("gained");
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == text && text.getText().isEmpty()) {
			text.setText("Send a Message");
			System.out.println("lost true");
			isEmpty = true;
		}else {
			isEmpty = false;
			System.out.println("lost false " + text.getText());
		}
		
		
		
	}

	@Override
	public void ancestorMoved(HierarchyEvent e) {
		if(e.getSource() == frame) {
			System.out.println("frame moved");
		}
		
	}

	@Override
	public void ancestorResized(HierarchyEvent e) {
		if(e.getSource() == frame) {
			System.out.println("frame resized");
			
		}
		
	}



}
