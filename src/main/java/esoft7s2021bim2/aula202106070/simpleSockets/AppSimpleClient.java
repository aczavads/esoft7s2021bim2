package esoft7s2021bim2.aula202106070.simpleSockets;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AppSimpleClient {
	
	public static void main(String[] args) {
		AppSimpleClient client = new AppSimpleClient();
		client.doIt();
	}

	private void doIt() {
		try {
			Socket socket = new Socket("localhost", 9099);
			PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
			Scanner fromServer = new Scanner(socket.getInputStream());
			
			toServer.println("Olá servidor, tudo jóia?");
			System.out.println("Reading from server...");
			if (fromServer.hasNextLine()) {
			    System.out.println("p1");
				System.out.println("Resposta do server: " + fromServer.nextLine());
			}
			fromServer.close();
			toServer.close();
			socket.close();						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
