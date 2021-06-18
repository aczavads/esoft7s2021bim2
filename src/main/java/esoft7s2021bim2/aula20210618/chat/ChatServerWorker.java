package esoft7s2021bim2.aula20210618.chat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatServerWorker extends Thread {
	private Socket socketClient;
	private ChatServer chatServer;
	private PrintWriter toClient = null;

	public ChatServerWorker(Socket socketClient, ChatServer chatServer) {
		this.socketClient = socketClient;
		this.chatServer = chatServer;
	}

	@Override
	public void run() {
		try {
			toClient = new PrintWriter(socketClient.getOutputStream());
			
			Scanner fromClient = new Scanner(socketClient.getInputStream());
			while (true) {
				if (fromClient.hasNextLine()) {
					final String message = fromClient.nextLine();
					if (message.equals("quit")) {
						break;
					}
					System.out.println("ChatServerWorker message received ==> " + message);
					chatServer.broadcastMessage(/*@username: +*/message, this);
				}
			}
			fromClient.close();
			toClient.close();
			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void sendMessageToClient(String messageToSend) {
		System.out.println("ChatServerWorker messageToSendToClient ==> " + messageToSend);
		try {
			toClient.println(messageToSend);
			toClient.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
