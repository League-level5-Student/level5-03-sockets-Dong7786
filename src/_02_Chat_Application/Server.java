package _02_Chat_Application;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {
	
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}
}
