package esoft7s2021bim2.aula202106070.simpleSockets;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

public class AppSimpleServer {
	Logger logger = Logger.getLogger(AppSimpleServer.class.getName());
	
	public static void main(String[] args) {
		AppSimpleServer server = new AppSimpleServer();
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
				Scanner fromCliente = new Scanner(clientSocket.getInputStream());
				PrintWriter toCliente = new PrintWriter(clientSocket.getOutputStream(), true);
				
				if (fromCliente.hasNextLine()) {
					System.out.println("Message from client: ["+fromCliente.nextLine()+"]");
				}
				System.out.println(">>>>>>>>>>>> cliente respondeu.");
				toCliente.println(new Date().toLocaleString());
				toCliente.close();
				fromCliente.close();
				clientSocket.close();
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		logger.info("Server stoped.");
	}
}
