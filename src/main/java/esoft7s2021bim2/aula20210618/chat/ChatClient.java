package esoft7s2021bim2.aula20210618.chat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	
	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		client.doIt();
	}

	private void doIt() {
		try {
			Socket socket = new Socket("localhost", 9091);
			
			new Thread() {
				Scanner fromServer = new Scanner(socket.getInputStream());
				public void run() {
					while (!socket.isClosed()) {
						if (fromServer.hasNextLine()) {
							System.out.println("Server sent: " + fromServer.nextLine());
						}
					}
				}
			}.start();
			
			PrintWriter toServer = new PrintWriter(socket.getOutputStream());
			Scanner fromConsole = new Scanner(System.in);
			while (true) {
				System.out.println(">>");
				final String message = fromConsole.nextLine();
				if (message.equals("quit")) {
					break;
				}
				toServer.println(message);				
				toServer.flush();
			}
			fromConsole.close();
			toServer.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
