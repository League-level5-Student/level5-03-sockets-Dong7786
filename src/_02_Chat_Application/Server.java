package _02_Chat_Application;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Server {
	private int port;

	private ServerSocket server;
	private Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public Server(int port) {
		this.port = port;
	}

	public void start(){
		System.out.println("Server Started");
		try {
			server = new ServerSocket(port, 100);

			connection = server.accept();

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			while (connection.isConnected()) {
				
				UserInterface.connecting = false;
				UserInterface.isConnected = true;
				System.out.println("Server Connected, Connecting = " + UserInterface.connecting + ", isConnected = " + UserInterface.isConnected);
				try {
					UserInterface.createMessage((String)is.readObject(), true);
					System.out.println("(Server)Message Recieved as: " + is.readObject());
				}catch(EOFException e) {
					UserInterface.isConnected = false;
					System.out.println("Connection Lost");
					System.exit(0);
					break;
				}
			}

		} catch (Exception e) {
			UserInterface.infoText.setText("\n \nConnection Failed! \n \n Make sure you have a connection and a useable Port Number!");
			
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendMessage(String message) {
		try {
			if (os != null) {
				os.writeObject(message);
				os.flush();
				System.out.println("(Server)Message sent as: " + message);
			}
		} catch (IOException e) {
			
		}
	}
}
