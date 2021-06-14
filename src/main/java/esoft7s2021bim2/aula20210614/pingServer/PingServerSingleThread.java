package esoft7s2021bim2.aula20210614.pingServer;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PingServerSingleThread {
	
	public static void main(String[] args) {
		PingServerSingleThread server = new PingServerSingleThread();
		server.startup();
	}

	private void startup() {
		try (ServerSocket socket = new ServerSocket(9091)) {
			
			while (true) {
				Socket clientSocket = socket.accept();
				PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
				int pingsToSend = 100;
				while (pingsToSend > 0) {
					toClient.println("Ping! " + (100-pingsToSend+1));
					toClient.flush();
					pingsToSend--;
					try {
						System.out.println(".");
						Thread.sleep(1000);
					} catch (Exception e) {
					}
				}
				toClient.println("<<done>>");
				toClient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
