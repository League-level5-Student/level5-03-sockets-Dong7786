package _02_Chat_Application;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Client {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start(){
		System.out.println("Client Started");
	
		try {
			
			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();
			UserInterface.connecting = false;
			
		} catch (Exception e) {
			UserInterface.infoText.setText("\n \n Connection Failed! \n \nMake sure your IPAddress and Port Number are Valid!");
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			System.out.println("Client Connected");	
			UserInterface.connecting = false;
			UserInterface.isConnected = true;
			System.out.println("Client Connected, Connecting = " + UserInterface.connecting + ", isConnected = " + UserInterface.isConnected);
			try {
				UserInterface.createMessage((String)is.readObject(), true);
				System.out.println("(Client)Message recieved as: " + is.readObject());
			} catch (Exception e) {
				UserInterface.isConnected = false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(String message) {
		
		try {
			if (os != null) {
				os.writeObject(message);
				os.flush();
				System.out.println("(Client)Message sent as: " + message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
