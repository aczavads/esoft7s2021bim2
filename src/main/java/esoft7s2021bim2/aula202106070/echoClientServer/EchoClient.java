package esoft7s2021bim2.aula202106070.echoClientServer;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class EchoClient {
	
	public static void main(String[] args) {
		EchoClient client = new EchoClient();
		client.doIt();
	}

	private void doIt() {
		try {
			Socket socket = new Socket("localhost", 9099);
			PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
			Scanner fromServer = new Scanner(socket.getInputStream());
			
			//enquanto message não for quit, repita!
			String message = JOptionPane.showInputDialog("");
			
			toServer.println(message);
			if (fromServer.hasNextLine()) { 
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
