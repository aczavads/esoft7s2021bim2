package esoft7s2021bim2.aula20210611.listFilesServer;

import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

public class ListFilesServer {
	Logger logger = Logger.getLogger(ListFilesServer.class.getName());
	
	public static void main(String[] args) {
		ListFilesServer server = new ListFilesServer();
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

				String command = fromCliente.nextLine();
				if (command.startsWith("ls")) {
					//ls c:/windows
					PrintWriter toCliente = new PrintWriter(clientSocket.getOutputStream(), true);
					//File diretório = new File("c:/windows");
					String dirName = command.split(" ")[1];
					File diretório = new File(dirName);
					File[] auxDiretórios = diretório.listFiles();
					for (File file : auxDiretórios) {
						toCliente.println(file.getCanonicalPath());
						toCliente.flush();
					}					
					toCliente.close();					
				} else if (command.startsWith("size")) {
					//size c:/windows
					//size d:/
				}
				
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
