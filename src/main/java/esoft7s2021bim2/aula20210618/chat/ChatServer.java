package esoft7s2021bim2.aula20210618.chat;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	private List<ChatServerWorker> workers = new ArrayList<>();
	
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		server.listen();
	}
	
	public synchronized void broadcastMessage(String messageToSend, ChatServerWorker sender) {
		for (ChatServerWorker worker : workers) {
			if (!worker.equals(sender)) {
				worker.sendMessageToClient(messageToSend);
			}
		}
	}

	private void listen() {
		try {
			ServerSocket socket = new ServerSocket(9091);
			while (true) {
				ChatServerWorker worker = new ChatServerWorker(socket.accept(), this);
				workers.add(worker);
				worker.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
