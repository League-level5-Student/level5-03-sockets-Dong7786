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
	String IPAddress = "";
	String portNum = "";

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

		startPanel();

	}

	JPanel startPanel;
	JPanel infoPanel;
	JButton startServerButton;
	JButton connectButton;
	public static JTextPane infoText;

	void startPanel() {

		connecting = false;
		startPanel = new JPanel();
		startPanel.setVisible(true);
		infoPanel = new JPanel();
		infoPanel.setVisible(true);

		// ------------Start of infoPanel-----------------------------
		infoText = new JTextPane();
		panel.add(infoPanel);
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		infoText.setParagraphAttributes(attribs, true);
		infoText.setEditable(false);
		infoPanel.setPreferredSize(new Dimension(width, height / 3));

		infoPanel.add(infoText);
		infoText.setBackground(infoPanel.getBackground());
		infoText.setPreferredSize(infoPanel.getPreferredSize());

		infoText.setText(
				"\n \n To start a server click the 'Start Server' button! \n \n To connect to a server click the 'connect' button! ");
		// ------------End of infoPanel------------------------------

		// ------------Start of startPanel-----------------------------

		IPAddressField = new JTextArea();
		IPAddressField.setPreferredSize(new Dimension(width - width / 5, height / 25));

		portNumField = new JTextArea();
		portNumField.setPreferredSize(new Dimension(width - width / 5, height / 25));

		IPAddressField.setVisible(false);
		startPanel.add(IPAddressField);

		portNumField.setVisible(false);
		startPanel.add(portNumField);

		panel.add(startPanel);
		startPanel.setPreferredSize(new Dimension(width, height / 3));

		startServerButton = new JButton("Start Server");
		startServerButton.addActionListener(this);
		connectButton = new JButton("Connect");
		connectButton.addActionListener(this);

		connectButton.setVisible(true);
		startServerButton.setVisible(true);

		startPanel.add(startServerButton);
		startPanel.add(connectButton);

		// ------------End of startPanel-----------------------------

	}

	public static boolean connecting = false;
	JTextArea IPAddressField;
	JTextArea portNumField;
	JButton cancelButton;

	void connectServer(boolean startServer) {
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);

		connecting = true;
		startServerButton.setVisible(false);
		connectButton.setVisible(false);

		if (startServer) {
			// -------------Start of Server Button---------------------------------
			portNumField.setVisible(true);
			startServerButton = new JButton("Start Server");
			startServerButton.addActionListener(this);
			startPanel.add(startServerButton);
			infoText.setText("\n \nEnter a port number and then click the 'Start Server' button!");

			// -------------End of Server Button---------------------------------
		} else {
			// -------------Start of Connect Button---------------------------------
			portNumField.setVisible(true);
			IPAddressField.setVisible(true);
			connectButton = new JButton("Connect");
			connectButton.addActionListener(this);
			startPanel.add(connectButton);
			infoText.setText(
					"\n \nEnter the IPAddress and Port Number of the system you're trying to connect to before hitting the 'Connect' button!");

			// -------------End of Server Button---------------------------------
		}
		startPanel.add(cancelButton);
		cancelButton.setVisible(true);
	}

	JPanel textPanel;
	static JPanel messagePanel;
	JPanel topPanel;

	void setPanels() {

		textPanel = new JPanel();
		textPanel.setVisible(true);
		messagePanel = new JPanel();
		messagePanel.setVisible(true);
		topPanel = new JPanel();
		topPanel.setVisible(true);

		panel.add(topPanel);
		panel.add(messagePanel);
		panel.add(textPanel);

		topPanel.setBackground(Color.green);
		topPanel.setPreferredSize(new Dimension(width, height / 10));

		messagePanel.setBackground(Color.blue);
		messagePanel.setPreferredSize(new Dimension(width, height - height / 4));

		textPanel.setBackground(Color.red);
		textPanel.setPreferredSize(new Dimension(width, height / 10));

		setTopPanel();
		setTextBox();

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
		address.setPreferredSize(new Dimension(width, (int) topPanel.getPreferredSize().getHeight() / 2));

		address.setText("Connected To IPAddress: " + IPAddress + "\nPort Number: " + portNum);

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

		messagePanel.add(text.get(text.size() - 1));

		if (isRecieved) {
			text.get(text.size() - 1).setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		} else {
			text.get(text.size() - 1).setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		}

		text.get(text.size() - 1).setPreferredSize(new Dimension(width, messagePanel.getHeight() / 20));
		text.get(text.size() - 1).setText(str);

	}

	public static void main(String[] args) {
		UserInterface user = new UserInterface();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// --------------------Start of Send Button---------------------------
		if (e.getSource() == sendButton) {
			System.out.println(frame.getSize());

		}

		if (e.getSource() == sendButton) {
			if (isEmpty == false) {
				message = text.getText();
				System.out.println("Set message equal to: " + message);

				createMessage(message, false);
				
				try {
					
					server.sendMessage(message);
					System.out.println("Server Sent Message");
					
				}catch(NullPointerException npe) {
					
					client.sendMessage(message);
					System.out.println("Client Sent Message");
					
				}
				

				isEmpty = true;
				text.setText("Send a Message");

			} else {

			}
		}
		// --------------------End of Send Button---------------------------

		// --------------------Start of Add/Server Button---------------------------
		Thread c = new Thread(() -> {
			
			while (true) {
				System.out.println("Trying, isConnect = " + isConnected);
				if (isConnected == true) {
					
					startPanel.setVisible(false);
					infoPanel.setVisible(false);
					setPanels();
					break;

				}
			}
		});
		if (e.getSource() == connectButton) {
			System.out.println("Trying to Connect");

			if (connecting) {
				IPAddress = IPAddressField.getText();
				portNum = portNumField.getText();

				client = new Client(IPAddressField.getText(), Integer.parseInt(portNumField.getText()));
				Thread ct = new Thread(() -> {
					client.start();
				});
				ct.start();
				c.start();
				infoText.setText("\n \nTrying to connect to IPAddress '" + IPAddressField.getText()
						+ "' with Port Number '" + portNumField.getText() + "'. \n \nWaiting for Connection...");
				IPAddressField.setVisible(false);
				portNumField.setVisible(false);
				connectButton.setVisible(false);
				
			} else {
				connectServer(false);
			}

		}
		if (e.getSource() == startServerButton) {
			if (connecting) {
				server = new Server(Integer.parseInt(portNumField.getText()));
				IPAddress = server.getIPAddress();
				portNum = Integer.toString(server.getPort());
				Thread st = new Thread(() -> {
					server.start();

				});

				st.start();
				c.start();
				infoText.setText("\n \nWaiting for Client... \n \nYour IPAddress: " + server.getIPAddress()
						+ "\nYour Port Number: " + server.getPort() + "\n \n Waiting for Connection...");
				portNumField.setVisible(false);
				startServerButton.setVisible(false);
				
			} else {
				connectServer(true);
			}
		}
		// --------------------End of Add/Server Button-----------------------------
		if (e.getSource() == cancelButton) {

			System.out.println("Cancel");
			startPanel.setVisible(false);
			infoPanel.setVisible(false);
			startPanel();
			cancelButton.setVisible(false);

		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == text && isEmpty) {
			text.setText(null);
			System.out.println("Textbox focus gained");
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == text && text.getText().isEmpty()) {
			text.setText("Send a Message");
			System.out.println("Focus lost with no message: " + text.getText());
			isEmpty = true;
		} else {
			isEmpty = false;
			System.out.println("Focus lost with message: " + text.getText());
		}
	}
}
