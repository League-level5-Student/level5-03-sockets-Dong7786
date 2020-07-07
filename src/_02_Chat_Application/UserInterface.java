package _02_Chat_Application;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class UserInterface implements ActionListener, FocusListener {

	JFrame frame;
	JPanel panel;

	public static boolean isConnected = false;
	
	public static int width = 500;
	public static int height = 800;

	public static String message = "";
	
	Server server;
	Client client;
	
	boolean isEmpty = true;
	UserInterface() {
		frame = new JFrame("Chat App");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		panel = new JPanel();

		frame.add(panel);
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(width, height));
		panel.setPreferredSize(new Dimension(width, height));

		frame.pack();

		startPanel(true);
	
	}
	
	JPanel startPanel;
	JPanel infoPanel;
	JButton startServerButton;
	JButton connectButton;
	void startPanel(boolean visibility) {
		startPanel = new JPanel();
		infoPanel = new JPanel();
		
	//------------Start of infoPanel-----------------------------
		
		
	//------------End of infoPanel------------------------------
		
	//------------Start of startPanel-----------------------------	 
		connectButton.setVisible(true);
		startServerButton.setVisible(true);
		startServerButton = new JButton("Start Server");
		startServerButton.addActionListener(this);
		connectButton = new JButton("Connect");
		connectButton.addActionListener(this);
		
		startPanel.add(startServerButton);
		startPanel.add(connectButton);
	
	//------------End of startPanel-----------------------------
		
	}
	
	JPanel textPanel;
	static JPanel messagePanel;
	JPanel topPanel;
	void setPanels() {
		textPanel = new JPanel();
		messagePanel = new JPanel();
		topPanel = new JPanel();
		
		panel.add(topPanel);
		panel.add(messagePanel);
		panel.add(textPanel);
		
		topPanel.setBackground(Color.green);
		topPanel.setPreferredSize(new Dimension(width, height / 10));
		
		messagePanel.setBackground(Color.blue);
		messagePanel.setPreferredSize(new Dimension(width, height - height / 4));

		textPanel.setBackground(Color.red);
		textPanel.setPreferredSize(new Dimension(width, height / 10));

	}
	
	JButton exitButton;
	JTextPane address;
	void setTopPanel() {
		address = new JTextPane();
		
		exitButton = new JButton();
		exitButton.addActionListener(this);
		
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		address.setParagraphAttributes(attribs, true);
		
		topPanel.add(address);
		address.setBackground(topPanel.getBackground());
		address.setPreferredSize(new Dimension(width, (int)topPanel.getPreferredSize().getHeight() / 2));
		
		address.setText("Connected To IPAddress: " + server.getIPAddress() + "\nPort Number: " + server.getPort());

		topPanel.add(exitButton);		

	}
	
	JTextArea text;
	JButton sendButton;
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
	
	public static void createMessage(String str, boolean isRecieved) {
		ArrayList<JTextArea> text = new ArrayList<JTextArea>();
		text.add(new JTextArea());
		
		messagePanel.add(text.get(text.size() -1));
		
		if(isRecieved) {
			text.get(text.size() -1).setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			
		}else {
			text.get(text.size() -1).setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			
		}
		
		text.get(text.size() -1).setPreferredSize(new Dimension(width, messagePanel.getHeight() / 20));
		text.get(text.size() -1).setText(str);
		
	}
	
	public static void main(String[] args) {
		UserInterface user = new UserInterface();
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	//--------------------Start of Send Button---------------------------
		if(e.getSource() == sendButton) {
			System.out.println(frame.getSize());
			
		}
		
		if(e.getSource() == sendButton) {
			if(isEmpty == false) {
			message = text.getText();
			System.out.println("Set message equal to: " + message);
			
			createMessage(message, false);
			server.sendMessage(message);
			
			isEmpty = true;
			text.setText("Send a Message");
			
			}else{
				
				
			}
		}
	//--------------------End of Send Button---------------------------
		
	//--------------------Start of Add/Server Button---------------------------
		if(e.getSource() == connectButton) {
			Thread clientThread = new Thread(() -> {
				client.start();
			});
			
		}
		if(e.getSource() == startServerButton) {
			Thread serverThread = new Thread(() -> {
				server.start();
			});
			
			
			
		}
	//--------------------End of Add/Server Button-----------------------------
		
	}


	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == text && isEmpty) {
			text.setText(null);
			System.out.println("Textbox focus gained");
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == text && text.getText().isEmpty()) {
			text.setText("Send a Message");
			System.out.println("Focus lost with no message: " + text.getText());
			isEmpty = true;
		}else {
			isEmpty = false;
			System.out.println("Focus lost with message: " + text.getText());
		}
	}
}
