package esoft7s2021bim2.aula20210614.pingServer;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PingClient {
	
	public static void main(String[] args) {
		PingClient client = new PingClient();
		client.doIt();
	}

	private void doIt() {
		
		try (Socket socket = new Socket("localhost", 9091)) {
			Scanner fromServer = new Scanner(socket.getInputStream());
			String response = "";
			do {
				response = fromServer.nextLine();
				System.out.println(response);
			} while (!response.equals("<<done>>"));
			System.out.println("Fim.");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
