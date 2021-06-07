package esoft7s2021bim2.aula202106070.echoClientServer;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

public class EchoServer {
	Logger logger = Logger.getLogger(EchoServer.class.getName());
	
	public static void main(String[] args) {
		EchoServer server = new EchoServer();
		server.startup();
	}

	private void startup() {
		logger.info("Starting server...");
		try {
			ServerSocket serverSocket = new ServerSocket(9099);
			logger.info("Server started!");
			logger.info("Server listening on port 9099.");
			while (true) {
				Socket clientSocket = serverSocket.accept();
				logger.info("Client connected!");
				
				Scanner fromCliente = new Scanner(clientSocket.getInputStream());
				PrintWriter toCliente = new PrintWriter(clientSocket.getOutputStream(), true);
				
				if (fromCliente.hasNextLine()) {
					toCliente.println("ECHO: " + fromCliente.nextLine());
				}
				
				toCliente.close();
				fromCliente.close();
				clientSocket.close();
				logger.info("Client disconnected.");
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		logger.info("Server stoped.");
	}
}
