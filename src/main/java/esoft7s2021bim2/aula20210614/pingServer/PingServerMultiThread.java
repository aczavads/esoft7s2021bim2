package esoft7s2021bim2.aula20210614.pingServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PingServerMultiThread {

	public static void main(String[] args) {
		PingServerMultiThread server = new PingServerMultiThread();
		server.startup();
	}

	private void startup() {
		try (ServerSocket socket = new ServerSocket(9091)) {

			while (true) {
				System.out.println("Waiting for new client to connect.");
				Socket clientSocket = socket.accept();
				//aqui, imprimir no console quantos clients ainda estão ativos. isAlive das Threads.
				System.out.println("Client connected!");
				
				Runnable r = () -> {
					PrintWriter toClient;
					try {
						toClient = new PrintWriter(clientSocket.getOutputStream());
						int pingsToSend = 100;
						while (pingsToSend > 0) {
							toClient.println("Ping! " + (100 - pingsToSend + 1));
							toClient.flush();
							pingsToSend--;
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
							}
						}
						toClient.println("<<done>>");
						toClient.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				};
				Thread t = new Thread(r);
				t.start();
				
				
				System.out.println("Client dispatched.");				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
