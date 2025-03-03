package singleThreadedServer;

import java.net.*;
import java.io.*;

public class Client {
	
	public void run() throws UnknownHostException, IOException{
		int port = 8011;
		InetAddress address = InetAddress.getByName("localhost");
		Socket socket = new Socket(address, port);
		PrintWriter toServer = new PrintWriter(socket.getOutputStream());
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		toServer.println("Hello from Client");
		String line = fromServer.readLine();
		System.out.println("Response from Server is: "+line);
		socket.close();
	}

	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		try {
			Client client = new Client();
			client.run();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
